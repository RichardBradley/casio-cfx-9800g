package org.bradders.casiocfx9800g.ui;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.bradders.casiocfx9800g.Evaluator;
import org.bradders.casiocfx9800g.ui.ConsoleUserInterface.PointBigDecimal;
import org.junit.Test;

public class ConsoleUserInterfaceTest
{
   @Test
   public void testEachBitmapPixelIsCoveredByIterateGraphXValues()
   {
      ConsoleUserInterface ui = new ConsoleUserInterface();
      ui.range(bd(1), bd(1.62), bd(10), bd(1), bd(1.62), bd(10));
      
      int x = 0;
      for (BigDecimal graphX : ui.iterateGraphXValues())
      {
         assertThat(
               ui.mapPointToBitmapCoords(new PointBigDecimal(graphX, BigDecimal.ZERO)).x,
               equalTo(x));
         x++;
      }
      assertThat(x, equalTo(ConsoleUserInterface.WIDTH_PIXELS));
   }
   
   @Test
   public void testDoubleRounding()
   {
      assertThat((int)0.9999999999985, equalTo(0));
   }
   
   private static BigDecimal bd(double val)
   {
      return new BigDecimal(val, Evaluator.STORED_PRECISION);
   }
}
