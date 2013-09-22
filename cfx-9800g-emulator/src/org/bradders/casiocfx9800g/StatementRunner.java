package org.bradders.casiocfx9800g;

import java.math.BigDecimal;
import java.util.List;

import org.bradders.casiocfx9800g.node.AAssignStatement;
import org.bradders.casiocfx9800g.node.ACommentStatement;
import org.bradders.casiocfx9800g.node.ACountJumpStatement;
import org.bradders.casiocfx9800g.node.AGotoStatement;
import org.bradders.casiocfx9800g.node.AGraphStatement;
import org.bradders.casiocfx9800g.node.AIfStatement;
import org.bradders.casiocfx9800g.node.ALabelStatement;
import org.bradders.casiocfx9800g.node.APrinttextStatement;
import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASubArgsStatement;
import org.bradders.casiocfx9800g.node.ASubNoargsStatement;
import org.bradders.casiocfx9800g.node.ASubStatement;
import org.bradders.casiocfx9800g.node.PStatement;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.ui.CalcColour;
import org.bradders.casiocfx9800g.ui.GraphShading;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.Printer;

public class StatementRunner
{
   private final RuntimeContext context;
   private final Compiler compiler;
   private final UserInterface userInterface;
   private final Evaluator evaluator;
   public boolean traceExecution = false;
   /**
    * True if the current graph already has at least one inequality graph drawn
    */
   private boolean inequalityIsIntersect = false;

   public StatementRunner(RuntimeContext context, Compiler compiler, UserInterface userInterface)
   {
      this.context = context;
      this.compiler = compiler;
      this.userInterface = userInterface;
      evaluator = new Evaluator(context, userInterface);
   }

   /**
    * Runs the whole program
    */
   public void run(CompiledFile file)
   {
      int statementPtr = 0;
      while (true)
      {
         PStatement statement = file.statements.get(statementPtr);
         
         Integer nextStatementLabel = run(statement);

         if (traceExecution) {
            System.out.print(statementPtr);
            System.out.print(" : ");
            System.out.print(Printer.nodeToString(statement));
            System.out.print(" ");
            System.out.print(context.variableValues);
            System.out.println();
         }

         if (null == nextStatementLabel) {
            statementPtr++;
            if (statementPtr >= file.statements.size()) {
               break;
            }
         } else {
            Integer statementIdx = file.statementIdxByLabelNumber.get(nextStatementLabel);
            if (statementIdx == null) {
               throw new RuntimeException("Bad goto label: " + Printer.nodeToString(statement));
            }
            statementPtr = statementIdx;
         }
      }
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
                  {count_jump} count_jump_op variable_name statement_separator statement |
                  {sub} prog space quoted_text |
                  {graph} graph space variable_name comparison_op expression;
      </code>
    * @param statement
    * @return the label of the next statement which should be executed, or null
    *         to proceed at the next in sequence  
    */
   private Integer run(PStatement statement)
   {
      if (statement instanceof ACommentStatement) {
         // nothing to do
      } else if (statement instanceof APrinttextStatement) {
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
      } else if (statement instanceof ASubStatement) {
         run((ASubStatement)statement);
      } else if (statement instanceof AGraphStatement) {
         run((AGraphStatement)statement);
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

      BigDecimal value = evaluator.evaluate(statement.getExpression());
      context.setVariableValue(statement.getVariableName().getText(), value);
      if (null != statement.getPrintResult()) {
         userInterface.printResult(evaluator.formatForDisplay(value));
      }
   }

   private void run(APrintvalStatement statement)
   {
      BigDecimal value = evaluator.evaluate(statement.getExpression());
      userInterface.printResult(evaluator.formatForDisplay(value));
   }

   private int run(AGotoStatement statement)
   {
      TNumberLiteral label = ((AGotoStatement)statement).getNumberLiteral();
      int labelNumber = Evaluator.getInt(label, statement);
      return labelNumber;
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
      } else if (subName.equals("Cls")) {
         userInterface.clearScreen();
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
      List<BigDecimal> args = evaluator.evaluate(statement.getExpressionList());
      
      if (subName.equals("Range")) {
         evaluator.assertArgumentCount(args, 6, statement);
         userInterface.range(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5));
         inequalityIsIntersect = false;
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
      BigDecimal leftValue = evaluator.evaluate(statement.getLeft());
      BigDecimal rightValue = evaluator.evaluate(statement.getRight());
      String op = statement.getComparisonOp().getText();
      
      int cmp = leftValue.compareTo(rightValue);
      
      boolean result;
      if (op.equals("=")) {
         result = cmp == 0;
      } else if (op.equals("!=")) {
         result = cmp != 0;
      } else if (op.equals(">")) {
         result = cmp > 0;
      } else if (op.equals(">=")) {
         result = cmp >= 0;
      } else if (op.equals("<")) {
         result = cmp < 0;
      } else if (op.equals("<=")) {
         result = cmp <= 0;
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
      BigDecimal variableValue = context.getVariableValue(varName, statement.getVariableName());
      
      if (op.equals("Isz")) {
         variableValue = variableValue.add(BigDecimal.ONE, Evaluator.STORED_PRECISION);
      } else if (op.equals("Dsz")) {
         variableValue = variableValue.subtract(BigDecimal.ONE, Evaluator.STORED_PRECISION);
      } else {
         throw new CompileException(String.format(
               "Unrecognised count jump operator: '%s' at %s",
               op,
               Printer.nodeToString(statement)));
      }
      
      context.setVariableValue(varName, variableValue);
      if (variableValue.compareTo(BigDecimal.ZERO) != 0) {
         return run(statement.getStatement());
      } else {
         return null;
      }
   }
   
   private void run(ASubStatement statement)
   {
      String filename = Evaluator.getString(statement.getQuotedText(), statement);
      
      try
      {
         CompiledFile file = compiler.loadFile(filename);
         run(file);
      }
      catch (Exception e)
      {
         throw new RuntimeException(String.format(
               "Error from subroutine included at %s: %s",
               Printer.nodeToString(statement),
               e.getMessage()),
               e);
      }
   }
   
   /**
    * Draws a line or inequality graph.
    * See Chapter 8 of CFX-9800G.pdf
    * 
    * We currently only support rectangular graphs
    */
   private void run(AGraphStatement statement)
   {
      final String msg = "Graphs must be of the form 'Graph Y=f(x)' or 'Graph Y>=f(x)'. ";
      
      // The graph is in the form
      // Graph Y [op] f(X)
      if (!statement.getVariableName().getText().equals("Y")) {
         throw new CompileException(String.format(
               msg + "The left hand side was not 'Y' at %s",
               Printer.nodeToString(statement)));
      }
      
      String op = statement.getComparisonOp().getText();
      GraphShading shadingMode;
      if (op.equals("=")) {
         shadingMode = GraphShading.NONE;
      } else if (op.equals("<") || op.equals("<=")) {
         if (inequalityIsIntersect) {
            shadingMode = GraphShading.LESS_THAN_INTERSECT;
         } else {
            shadingMode = GraphShading.LESS_THAN_FIRST;
            inequalityIsIntersect = true;
         }
      } else if (op.equals(">") || op.equals(">=")) {
         if (inequalityIsIntersect) {
            shadingMode = GraphShading.GREATER_THAN_INTERSECT;
         } else {
            shadingMode = GraphShading.GREATER_THAN_FIRST;
            inequalityIsIntersect = true;
         }
      } else {
         throw new CompileException(String.format(
               "Unrecognised comparison operator: '%s' at %s",
               op,
               Printer.nodeToString(statement)));
      }
      
      // note that we do not preserve the prior value of X: drawing the graph
      // overwrites this value on the Casio
      for (BigDecimal x : userInterface.iterateGraphXValues())
      {
         context.setVariableValue("X", x);
         BigDecimal y = evaluator.evaluate(statement.getExpression());
         // TODO: the graphing alg used here will draw only one dot per vertical column,
         // whereas the alg used by the calc will draw a proper line, especially where the
         // graph is steep. We should redo this. Read up on line drawing algs.
         userInterface.graphDot(x, y, shadingMode);
      }
   }
}
