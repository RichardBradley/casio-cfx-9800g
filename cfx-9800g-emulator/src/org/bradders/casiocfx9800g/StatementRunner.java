package org.bradders.casiocfx9800g;

import org.bradders.casiocfx9800g.node.AAssignStatement;
import org.bradders.casiocfx9800g.node.AGotoStatement;
import org.bradders.casiocfx9800g.node.ALabelStatement;
import org.bradders.casiocfx9800g.node.APrinttextStatement;
import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.PStatement;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.Printer;

public class StatementRunner
{
   private final RuntimeContext context;
   private final UserInterface userInterface;
   private final Evaluator evaluator;

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
         if (statement instanceof APrinttextStatement) {
            run((APrinttextStatement)statement);
         } else if (statement instanceof AAssignStatement) {
            run((AAssignStatement)statement);
         } else if (statement instanceof APrintvalStatement) {
            run((APrintvalStatement)statement);
         } else if (statement instanceof ALabelStatement) {
            // nothing to do
         } else if (statement instanceof AGotoStatement) {
            TNumberLiteral label = ((AGotoStatement)statement).getNumberLiteral();
            int labelNumber = Evaluator.getInt(label, statement);
            statementPtr = context.statementIdxByLabelNumber.get(labelNumber);
            continue;
         } else {
            throw new CompileException(String.format(
                  "Unexpected type: %s at %s",
                  statement.getClass(),
                  Printer.nodeToString(statement)));
         }
         
         statementPtr++;
         if (statementPtr >= context.statements.size()) {
            break;
         }
      }
   }

   private void run(APrinttextStatement statement)
   {
      String string = Evaluator.getString(statement.getQuotedText(), statement);
      userInterface.printLine(string);
   }
   
   private void run(AAssignStatement statement)
   {
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
}
