package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.StringReader;

import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.Start;
import org.junit.Test;

public class EvaluatorTest
{
   private RuntimeContext context = new RuntimeContext();
   
   @Test
   public void testSimpleAddition() throws Exception
   {
      assertThat(evaluate("1+1"), equalTo(2.0));
   }
   
   @Test
   public void testPowerPrecedence() throws Exception
   {
      context.setVariableValue("A", 1);
      context.setVariableValue("B", 2);
      context.setVariableValue("C", 3);
      context.setVariableValue("D", 4);


      assertThat(evaluate("AB^CD"), equalTo(32.0));
      assertThat(evaluate("A(B^C)D"), equalTo(32.0));
      assertThat(evaluate("(AB)^CD"), equalTo(32.0));
      assertThat(evaluate("BB^CD"), equalTo(64.0));
      assertThat(evaluate("(BB)^CD"), equalTo(256.0));
      assertThat(evaluate("(BB)^(CD)"), equalTo(16777216.0));

      assertThat(evaluate("B^C^D"), equalTo(4096.0));
      assertThat(evaluate("(B^C)^D"), equalTo(4096.0));
      assertThat(evaluate("B^(C^D)"), closeTo(2.41785163e+24, 1e-6));
   }
   
   @Test
   public void testMultDivPrecedence() throws Exception
   {
      context.setVariableValue("A", 1);
      context.setVariableValue("B", 2);
      context.setVariableValue("C", 3);
      context.setVariableValue("D", 4);
      
      assertThat(evaluate("BCD"), equalTo(24.0));
      assertThat(evaluate("B*CD"), equalTo(24.0));
      assertThat(evaluate("BC*D"), equalTo(24.0));
      assertThat(evaluate("B/C/D"), equalTo(0.16666666666666666));
      assertThat(evaluate("B*C/D"), equalTo(1.5));
      assertThat(evaluate("BC/D"), equalTo(1.5));
      assertThat(evaluate("B/C*D"), equalTo(2.66666666666666666));
      assertThat(evaluate("B/CD"), equalTo(0.16666666666666666));
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
