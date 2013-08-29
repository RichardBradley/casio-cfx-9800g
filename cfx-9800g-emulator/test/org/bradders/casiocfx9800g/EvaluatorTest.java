package org.bradders.casiocfx9800g;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.StringReader;

import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.Start;
import org.junit.Test;

public class EvaluatorTest
{
   @Test
   public void testSimpleAddition() throws Exception
   {
      assertThat(evaluate("1+1"), equalTo(2.0));
   }

   private static double evaluate(String expressionStr) throws Exception {
      expressionStr = expressionStr + "#\n";
      
      RuntimeContext ctx = new RuntimeContext();
      Start ast = Main.compile(ctx , new StringReader(expressionStr));
      ASingleProgram prog = (ASingleProgram)ast.getPProgram();
      APrintvalStatement stm = (APrintvalStatement) prog.getStatement();
      PExpression expression = stm.getExpression();
      
      Evaluator evaluator = new Evaluator(ctx);
      return evaluator.evaluate(expression);
   }
}
