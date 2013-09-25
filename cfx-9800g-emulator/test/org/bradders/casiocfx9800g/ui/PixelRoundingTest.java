package org.bradders.casiocfx9800g.ui;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.awt.Point;

import org.bradders.casiocfx9800g.EvaluationTestBase;
import org.bradders.casiocfx9800g.ui.GraphImageJLabel.PointBigDecimal;
import org.junit.Test;

public class PixelRoundingTest extends EvaluationTestBase
{
   ConsoleUserInterface ui = new ConsoleUserInterfaceNoFrame();
   
   @Test
   public void testMapCoordsToPixel()
   {
      // To test this on the fx-9860G emluator, replace "96" with "128" as
      // the screen is wider.
      int width = 96;
      int height = 64;
      ui.range(bd(1), bd(width-1), bd(100), bd(1), bd(height-1), bd(10));

      assertThat(mapPoint(1,1), equalTo(new Point(0, height-2)));
      assertThat(mapPoint(width-1,height-1), equalTo(new Point(width-2, 0)));
      
      for (int x = 1; x <= width - 1; x++) {
         for (int y = 1; y <= height - 1; y++) {
            assertThat(mapPoint(x,y), equalTo(new Point(x-1, height-y-1)));
         }
      }
   }
   
   private Point mapPoint(int x, int y)
   {
      return ui.graph.mapPointToBitmapCoords(new PointBigDecimal(bd(x), bd(y)));
   }
}
