package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FunctionTest extends EvaluationTestBase
{
   @Test
   public void testFrac() throws Exception
   {
      assertThat(evaluate("Frac 2.99"), equalTo("0.99"));
      assertThat(evaluate("Frac (-2.99)"), equalTo("-0.99"));
   }

   @Test
   public void testInt() throws Exception
   {
      assertThat(evaluate("Int 9.9"), equalTo("9"));
      assertThat(evaluate("Int (-9.9)"), equalTo("-9"));
   }
}
