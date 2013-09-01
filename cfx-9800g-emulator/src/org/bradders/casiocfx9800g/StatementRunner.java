package org.bradders.casiocfx9800g;

import java.util.List;

import org.bradders.casiocfx9800g.node.AAssignStatement;
import org.bradders.casiocfx9800g.node.ACountJumpStatement;
import org.bradders.casiocfx9800g.node.AGotoStatement;
import org.bradders.casiocfx9800g.node.AIfStatement;
import org.bradders.casiocfx9800g.node.ALabelStatement;
import org.bradders.casiocfx9800g.node.APrinttextStatement;
import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASubArgsStatement;
import org.bradders.casiocfx9800g.node.ASubNoargsStatement;
import org.bradders.casiocfx9800g.node.PStatement;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.ui.CalcColour;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.Printer;

public class StatementRunner
{
   private final RuntimeContext context;
   private final UserInterface userInterface;
   private final Evaluator evaluator;
   public boolean traceExecution = false;

   public StatementRunner(RuntimeContext context, UserInterface userInterface)
   {
      this.context = context;
      this.userInterface = userInterface;
      evaluator = new Evaluator(context, userInterface);
   }

   /**
    * Runs the whole program
    */
   public void run()
   {
      int statementPtr = 0;
      while (true)
      {
         PStatement statement = context.statements.get(statementPtr);
         
         Integer nextStatement = run(statement);

         if (traceExecution) {
            System.out.print(statementPtr);
            System.out.print(" : ");
            System.out.print(Printer.nodeToString(statement));
            System.out.print(" ");
            System.out.print(context.variableValues);
            System.out.println();
         }

         if (null == nextStatement) {
            statementPtr++;
            if (statementPtr >= context.statements.size()) {
               break;
            }
         } else {
            statementPtr = nextStatement;
         }
      }
      userInterface.printLine("(terminated)");
   }

   /**
    * Runs the given statement.
    *
    * <code>
        statement =
                  {printtext} quoted_text |
                  {assign} expression assign_arrow variable_name print_result? |
                  {printval} expression print_result? |
                  {label} label number_literal |
                  {goto} goto number_literal |
                  {sub_noargs} sub_noargs_name |
                  {sub_args} sub_args_name space atom_list |
                  {if} [left]:expression comparison_op [right]:expression then_arrow statement |
                  {count_jump} count_jump_op variable_name statement_separator statement;
      </code>
    * @param statement
    * @return the index of the next statement which should be executed, or null
    *         to proceed at the next in sequence  
    */
   private Integer run(PStatement statement)
   {
      if (statement instanceof APrinttextStatement) {
         run((APrinttextStatement)statement);
      } else if (statement instanceof AAssignStatement) {
         run((AAssignStatement)statement);
      } else if (statement instanceof APrintvalStatement) {
         run((APrintvalStatement)statement);
      } else if (statement instanceof ALabelStatement) {
         // nothing to do
      } else if (statement instanceof AGotoStatement) {
         return run((AGotoStatement)statement);
      } else if (statement instanceof ASubNoargsStatement) {
         run((ASubNoargsStatement)statement);
      } else if (statement instanceof ASubArgsStatement) {
         run((ASubArgsStatement)statement);
      } else if (statement instanceof AIfStatement) {
         return run((AIfStatement)statement);
      } else if (statement instanceof ACountJumpStatement) {
         return run((ACountJumpStatement)statement);
      } else {
         throw new CompileException(String.format(
               "Unexpected type: %s at %s",
               statement.getClass(),
               Printer.nodeToString(statement)));
      }
      return null;
   }

   private void run(APrinttextStatement statement)
   {
      String string = Evaluator.getString(statement.getQuotedText(), statement);
      userInterface.printLine(string);
   }

   private void run(AAssignStatement statement)
   {
      if (statement.getQuotedText() != null) {
         String string = Evaluator.getString(statement.getQuotedText(), statement);
         userInterface.printLine(string);
      }

      double value = evaluator.evaluate(statement.getExpression());
      context.setVariableValue(statement.getVariableName().getText(), value);
      if (null != statement.getPrintResult()) {
         userInterface.printResult(value);
      }
   }

   private void run(APrintvalStatement statement)
   {
      double value = evaluator.evaluate(statement.getExpression());
      userInterface.printResult(value);
   }

   private int run(AGotoStatement statement)
   {
      TNumberLiteral label = ((AGotoStatement)statement).getNumberLiteral();
      int labelNumber = Evaluator.getInt(label, statement);
      Integer statementIdx = context.statementIdxByLabelNumber.get(labelNumber);
      if (statementIdx == null) {
         throw new RuntimeException("Bad goto label: " + Printer.nodeToString(statement));
      }
      return statementIdx;
   }

   private void run(ASubNoargsStatement statement)
   {
      String subName = statement.getSubNoargsName().getText();
      if (subName.equals("Line")) {
         userInterface.line(CalcColour.BLACK);
      } else if (subName.equals("Orange Line")) {
         userInterface.line(CalcColour.ORANGE);
      } else if (subName.equals("Green Line")) {
         userInterface.line(CalcColour.GREEN);
      } else if (subName.equals("Mcl")) {
         context.memoryClear();
      } else {
         throw new CompileException(String.format(
               "Unrecognised sub: '%s' at %s",
               subName,
               Printer.nodeToString(statement)));
      }
   }
   
   private void run(ASubArgsStatement statement)
   {
      String subName = statement.getSubArgsName().getText();
      List<Double> args = evaluator.evaluate(statement.getAtomList());
      
      if (subName.equals("Range")) {
         evaluator.assertArgumentCount(args, 6, statement);
         userInterface.range(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5));
      } else if (subName.equals("Plot")) {
         evaluator.assertArgumentCount(args, 2, statement);
         userInterface.plot(CalcColour.BLACK, args.get(0), args.get(1));
      } else if (subName.equals("Orange Plot")) {
         evaluator.assertArgumentCount(args, 2, statement);
         userInterface.plot(CalcColour.ORANGE, args.get(0), args.get(1));
      } else if (subName.equals("Green Plot")) {
         evaluator.assertArgumentCount(args, 2, statement);
         userInterface.plot(CalcColour.GREEN, args.get(0), args.get(1));
      } else {
         throw new CompileException(String.format(
               "Unrecognised sub: '%s' at %s",
               subName,
               Printer.nodeToString(statement)));
      }
   }
   
   private Integer run(AIfStatement statement)
   {
      double leftValue = evaluator.evaluate(statement.getLeft());
      double rightValue = evaluator.evaluate(statement.getRight());
      String op = statement.getComparisonOp().getText();
      boolean result;
      if (op.equals("=")) {
         result = leftValue == rightValue;
      } else if (op.equals("!=")) {
         result = leftValue != rightValue;
      } else if (op.equals(">")) {
         result = leftValue > rightValue;
      } else if (op.equals(">=")) {
         result = leftValue >= rightValue;
      } else if (op.equals("<")) {
         result = leftValue < rightValue;
      } else if (op.equals("<=")) {
         result = leftValue <= rightValue;
      } else {
         throw new CompileException(String.format(
               "Unrecognised comparison operator: '%s' at %s",
               op,
               Printer.nodeToString(statement)));
      }

      if (result) {
         return run(statement.getStatement());
      } else {
         return null;
      }
   }

   private Integer run(ACountJumpStatement statement)
   {
      String op = statement.getCountJumpOp().getText();
      String varName = statement.getVariableName().getText();
      double variableValue = context.getVariableValue(varName, statement.getVariableName());
      
      if (op.equals("Isz")) {
         variableValue += 1;
      } else if (op.equals("Dsz")) {
         variableValue -= 1;
      } else {
         throw new CompileException(String.format(
               "Unrecognised count jump operator: '%s' at %s",
               op,
               Printer.nodeToString(statement)));
      }
      
      context.setVariableValue(varName, variableValue);
      if (variableValue != 0.0) {
         return run(statement.getStatement());
      } else {
         return null;
      }
   }
}
