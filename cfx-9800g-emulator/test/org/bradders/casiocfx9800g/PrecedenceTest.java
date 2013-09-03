package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PrecedenceTest extends EvaluationTestBase
{
   /**
    * All the following have been tested on the fx-9960G emulator
    */
   @Test
   public void testPrecedence() throws Exception
   {
      context.setVariableValue("A", 2.0);
      context.setVariableValue("B", 3.0);
      context.setVariableValue("C", 4.0);
      context.setVariableValue("D", 4.0);
      context.setVariableValue("E", 6.0);
      
      // expression(add) is lower precedence than multdiv
      assertThat(evaluate("A+B*C"), equalTo(evaluate("A+(B*C)")));
      assertThat(evaluate("A+B*C"), not(equalTo(evaluate("(A+B)*C"))));

      // multdiv is lower precedence than func
      assertThat(evaluate("sin A*B"), equalTo(evaluate("(sin A)*B")));
      assertThat(evaluate("sin A*B"), not(equalTo(evaluate("sin (A*B)"))));

      // multdiv is lower precedence than multgroup
      assertThat(evaluate("A/BC"), equalTo(evaluate("A/(BC)")));
      assertThat(evaluate("A/BC"), not(equalTo(evaluate("(A/B)C"))));

      // func is lower precedence than multgroup
      assertThat(evaluate("sin AB"), equalTo(evaluate("sin (AB)")));
      assertThat(evaluate("sin AB"), not(equalTo(evaluate("(sin A)B"))));

      // multgroup is lower precedence than pow
      assertThat(evaluate("AB^C"), equalTo(evaluate("A(B^C)")));
      assertThat(evaluate("AB^C"), not(equalTo(evaluate("(AB)^C"))));
      assertThat(evaluate("A^BC"), equalTo(evaluate("(A^B)C")));
      assertThat(evaluate("A^BC"), not(equalTo(evaluate("A^(B^C)"))));

      // multgroup is lower precedence than frac
      assertThat(evaluate("A|BC"), equalTo(evaluate("(A|B)C")));
      assertThat(evaluate("A|BC"), not(equalTo(evaluate("A|(BC)"))));

      // multgroup is lower precedence than postfixop
      assertThat(evaluate("AB!"), equalTo(evaluate("A(B!)")));
      assertThat(evaluate("AB!"), not(equalTo(evaluate("(AB)!"))));

      // frac is lower precedence than postfixop
      assertThat(evaluate("E|B!"), equalTo(evaluate("E|(B!)")));
      assertThat(evaluate("E|B!"), not(equalTo(evaluate("(E|B)!"))));
      
      // frac is lower precedence than pow
      assertThat(evaluate("A|B^2"), equalTo(evaluate("A|(B^2)")));
      assertThat(evaluate("A|B^2"), not(equalTo(evaluate("(A|B)^2"))));
      
      // pow is lower precedence than postfixop
      assertThat(evaluate("A^B!"), equalTo(evaluate("A^(B!)")));
      assertThat(evaluate("A^B!"), not(equalTo(evaluate("(A^B)!"))));

   }
}
