package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FractionTest extends EvaluationTestBase
{
   @Test
   public void testFractions() throws Exception
   {
      assertThat(outputOfProgram("1|3#"), equalTo("0.3333333333"));
      assertThat(outputOfProgram("1|3->A:A+1#"), equalTo("1.333333333"));
      assertThat(outputOfProgram("1|3->A:A+1|7|8#"), equalTo("2.208333333"));
   }
   
   /**
    * TODO: if/when the emulator supports actual fractions, instead of just
    * converting them to doubles, then this test should pass
   @Test
   public void testRealFractions() throws Exception
   {
      assertProgramPrints("1|3->A:A+1#", "4|3");
      assertProgramPrints("1|3->A:A+1#", "4|3");
      assertProgramPrints("1|3->A:A+1|7|8#", "77|24");
   }
    */
   
   @Test
   public void testFracPrecendence() throws Exception
   {
      assertThat(evaluate("1|3!"), equalTo("0.1666666667"));
      assertThat(evaluate("1|3!"), equalTo(evaluate("1|(3!)")));
      try {
         evaluate("(1|3)!");
         fail("expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("factorial of non-integer"));
      }
      
      assertThat(evaluate("3|2^2"), equalTo("0.75"));
      assertThat(evaluate("(3|2)^2"), equalTo("2.25"));
      
      // Frac is higher precedence than func
      assertThat(evaluate("sin 1|2"), equalTo(evaluate("sin (1|2)")));
      assertThat(evaluate("sin 1|2"), not(equalTo(evaluate("(sin 1)|2"))));
   }
   
   @Test
   public void testFracVariables() throws Exception
   {
      context.setVariableValue("A", 2);
      context.setVariableValue("B", 3);
      context.setVariableValue("C", 4);

      assertThat(evaluate("A|B|C"), equalTo("2.75"));
      assertThat(evaluate("A|2B"), equalTo("3"));
      assertThat(evaluate("A|(2B)"), equalTo("0.3333333333"));
      assertThat(evaluate("(A+1)|(B+1)|(C+1)"), equalTo("3.8"));
   }
}
