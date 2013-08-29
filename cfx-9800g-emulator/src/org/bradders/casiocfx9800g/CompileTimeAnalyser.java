package org.bradders.casiocfx9800g;

import java.util.ArrayList;
import java.util.List;

import org.bradders.casiocfx9800g.analysis.DepthFirstAdapter;
import org.bradders.casiocfx9800g.node.AGotoStatement;
import org.bradders.casiocfx9800g.node.ALabelStatement;
import org.bradders.casiocfx9800g.node.ASequenceProgram;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.util.Printer;

/**
 * This Analyser prepares the AST for running.
 *
 * The list of statements is extracted, Goto / Labels are prepared and
 * various static checks are made.  
 */
public class CompileTimeAnalyser extends DepthFirstAdapter
{
   private final RuntimeContext context;
   private final List<AGotoStatement> gotos = new ArrayList<AGotoStatement>();
   
   public CompileTimeAnalyser(RuntimeContext context)
   {
      this.context = context;
   }
   
   @Override
   public void outStart(Start node)
   {
      super.outStart(node);
      
      for (AGotoStatement gotoStatement : gotos) {
         int labelNumber = Evaluator.getInt(gotoStatement.getNumberLiteral(), gotoStatement);
         if (!context.statementIdxByLabelNumber.containsKey(labelNumber)) {
            throw new CompileException(String.format(
                  "%s references an unrecognised label",
                  Printer.nodeToString(gotoStatement)));
         }
      }
   }

   @Override
   public void caseASingleProgram(ASingleProgram node)
   {
      context.statements.add(node.getStatement());
      
      super.caseASingleProgram(node);
   }
   
   @Override
   public void caseASequenceProgram(ASequenceProgram node)
   {
      context.statements.add(node.getStatement());
      
      super.caseASequenceProgram(node);
   }
   
   @Override
   public void caseALabelStatement(ALabelStatement statement)
   {
      int labelNumber = Evaluator.getInt(statement.getNumberLiteral(), statement);
      int statementIdx = context.statements.indexOf(statement);
      if (statementIdx < 0) {
         throw new RuntimeException("Could not find parent statement: " + statement);
      }
      Integer previousStatementIdx = context.statementIdxByLabelNumber.put(labelNumber, statementIdx);
      if (null != previousStatementIdx) {
         throw new RuntimeException(String.format(
               "Duplicate label: '%s' is used at %s and %s",
               labelNumber,
               Printer.nodeToString(context.statements.get(previousStatementIdx)),
               Printer.nodeToString(statement)));
      }
      
      super.caseALabelStatement(statement);
   }
   
   @Override
   public void caseAGotoStatement(AGotoStatement node)
   {
      // We analyse the gotos in one pass at the end, in case they forward-reference a label
      gotos.add(node);
      
      super.caseAGotoStatement(node);
   }
}
