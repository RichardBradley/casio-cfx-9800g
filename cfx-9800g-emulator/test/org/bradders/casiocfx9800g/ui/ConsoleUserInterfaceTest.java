package org.bradders.casiocfx9800g.ui;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.awt.geom.Point2D;

import org.junit.Test;

public class ConsoleUserInterfaceTest
{
   @Test
   public void testEachBitmapPixelIsCoveredByIterateGraphXValues()
   {
      ConsoleUserInterface ui = new ConsoleUserInterface();
      ui.range(1, 1.62, 10, 1, 1.62, 10);
      
      int x = 0;
      for (double graphX : ui.iterateGraphXValues())
      {
         assertThat(ui.mapPointToBitmapCoords(new Point2D.Double(graphX, 0.0)).x, equalTo(x));
         x++;
      }
      assertThat(x, equalTo(ConsoleUserInterface.WIDTH_PIXELS));
   }
   
   @Test
   public void testDoubleRounding()
   {
      assertThat((int)0.9999999999985, equalTo(0));
   }
}
