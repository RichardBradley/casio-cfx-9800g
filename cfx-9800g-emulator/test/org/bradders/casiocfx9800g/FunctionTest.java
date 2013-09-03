package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FunctionTest extends EvaluationTestBase
{
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
}
