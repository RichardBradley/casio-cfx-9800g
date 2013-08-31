package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import java.io.StringReader;

import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.Start;
import org.junit.Test;

public class FunctionTest
{
   private RuntimeContext context = new RuntimeContext();

   @Test
   public void testFrac() throws Exception
   {
      assertThat(evaluate("Frac 2.99"), closeTo(0.99, 1e-6));
      assertThat(evaluate("Frac (-2.99)"), closeTo(-0.99, 1e-6));
   }

   @Test
   public void testInt() throws Exception
   {
      assertThat(evaluate("Int 9.9"), closeTo(9, 1e-6));
      assertThat(evaluate("Int (-9.9)"), closeTo(-9, 1e-6));
   }
   
   private double evaluate(String expressionStr) throws Exception {
      expressionStr = expressionStr + "#\n";
      
      Start ast = Main.compile(context , new StringReader(expressionStr));
      ASingleProgram prog = (ASingleProgram)ast.getPProgram();
      APrintvalStatement stm = (APrintvalStatement) prog.getStatement();
      PExpression expression = stm.getExpression();
      
      Evaluator evaluator = new Evaluator(context, null);
      return evaluator.evaluate(expression);
   }
}
