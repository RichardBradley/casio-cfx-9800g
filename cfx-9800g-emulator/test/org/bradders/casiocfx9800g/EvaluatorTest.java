package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluatorTest extends EvaluationTestBase
{
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
      assertThat(evaluate("B^(C^D)"), equalTo(2.4178516392292583e+24));
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
   
   @Test
   public void testSubPrecedence() throws Exception
   {
      assertThat(evaluate("5-2-3"), equalTo(0.0));
   }
   
   @Test
   public void testBangFuncPrecedence() throws Exception
   {
      assertThat(evaluate("sin 2!"), equalTo(evaluate("sin (2!)")));
      try {
         evaluate("(sin 2)!");
         fail("expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("factorial of non-integer"));
      }
   }
   
   @Test
   public void testBangBang() throws Exception
   {
      assertThat(evaluate("3!!"), equalTo(720.0));
   }
   
   @Test
   public void testPowerFuncPrecedence() throws Exception
   {
      assertThat(evaluate("sin 2^2"), equalTo(evaluate("sin (2^2)")));
      assertThat(evaluate("sin 2^2"), not(equalTo(evaluate("(sin 2)^2"))));
   }
   
   @Test
   public void testNumberLiterals() throws Exception
   {
      assertThat(evaluate("1"), equalTo(1.0));
      assertThat(evaluate("1.0"), equalTo(1.0));
      assertThat(evaluate("-1"), equalTo(-1.0));
      assertThat(evaluate("-1.0"), equalTo(-1.0));
      assertThat(evaluate("1e1"), equalTo(1e1));
      assertThat(evaluate("1e100"), equalTo(1e100));
      assertThat(evaluate("1e-1"), equalTo(1e-1));
      assertThat(evaluate("1e-100"), equalTo(1e-100));
      assertThat(evaluate("1.2e1"), equalTo(1.2e1));
      assertThat(evaluate("1.2e100"), equalTo(1.2e100));
      assertThat(evaluate("1.2e-1"), equalTo(1.2e-1));
      assertThat(evaluate("1.2e-100"), equalTo(1.2e-100));
   }
}
