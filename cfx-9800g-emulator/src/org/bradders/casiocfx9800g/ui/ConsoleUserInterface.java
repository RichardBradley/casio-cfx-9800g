package org.bradders.casiocfx9800g.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.bradders.casiocfx9800g.Evaluator;

public class ConsoleUserInterface implements UserInterface
{
   private static final int CONSOLE_WIDTH = 21;
   private static final String FORMAT_STR = "%" + CONSOLE_WIDTH + "s";
   // On the calc, the top row and left col of pixels are not used in graph mode
   // (I don't know why).
   //
   // Note that the fx-9860G is 128x64
   static final int WIDTH_PIXELS = 96 - 1;
   static final int HEIGHT_PIXELS = 64 - 1;
   static final BigDecimal WIDTH_PIXELS_BD = new BigDecimal(WIDTH_PIXELS);
   static final BigDecimal WIDTH_PIXELS_MINUS_ONE = new BigDecimal(WIDTH_PIXELS - 1);
   static final BigDecimal HEIGHT_PIXELS_BD = new BigDecimal(HEIGHT_PIXELS);
   static final BigDecimal HEIGHT_PIXELS_MINUS_ONE = new BigDecimal(HEIGHT_PIXELS - 1);
   private static final int IMAGE_SCALE = 4;
   
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
   private Graphics2D graphics;
   private WritableRaster imageRaster;
   private Point prevPoint;
   private Point prevPrevPoint;
   private BigDecimal xMin;
   private BigDecimal xMax;
   private BigDecimal yMin;
   private BigDecimal yMax;
   private JFrame frame;
   
   @Override
   public void printLine(String string)
   {
      System.out.println(string);
   }

   @Override
   public void printResult(String value)
   {
      System.out.println(String.format(FORMAT_STR, value));
      System.out.print(String.format(FORMAT_STR, "- Disp -"));
      try {
         in.readLine();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public BigDecimal readValue()
   {
      try {
         while (true) {
            System.out.println("?");
            String line = in.readLine();
            try {
               return new BigDecimal(line, Evaluator.STORED_PRECISION);
            } catch (Exception e) {
               System.out.println("Bad input, must be number: " + e.getMessage());
            }
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void range(
         BigDecimal xMin, BigDecimal xMax, BigDecimal xScale,
         BigDecimal yMin, BigDecimal yMax, BigDecimal yScale)
   {
      BufferedImage image = new BufferedImage(
            WIDTH_PIXELS,
            HEIGHT_PIXELS,
            BufferedImage.TYPE_INT_ARGB);
      imageRaster = image.getRaster();
      
      graphics = image.createGraphics();
      graphics.setColor(CalcColour.BACKGROUND.getColor());
      graphics.fillRect(0, 0, WIDTH_PIXELS, HEIGHT_PIXELS);
      graphics.setColor(CalcColour.BLACK.getColor());
      
      // We can't use graphics.scale / graphics.translate since it messes with the line
      // widths, and we need exactly 1px lines for that retro look
      this.xMin = xMin;
      this.xMax = xMax;
      this.yMin = yMin;
      this.yMax = yMax;
      
      addImageToFrameAndShow(image);
   }

   protected void addImageToFrameAndShow(BufferedImage image)
   {
      frame = new JFrame("cfx-9800g-emulator");
      
      JLabel label = new ScaledImageJLabel(image);
      label.setPreferredSize(new Dimension(WIDTH_PIXELS * IMAGE_SCALE, HEIGHT_PIXELS * IMAGE_SCALE));
      
      frame.add(label);
      frame.pack();
      frame.setVisible(true);
      
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   
   @Override
   public void line(CalcColour colour)
   {
      if (graphics == null) {
         throw new RuntimeException("You may not call 'Plot' without first calling 'Range'");
      }
      if (prevPoint == null || prevPrevPoint == null) {
         throw new RuntimeException("You may not call 'Line' without first calling 'Plot' twice");
      }
      graphics.setColor(colour.getColor());
      
      graphics.draw(new Line2D.Double(prevPoint, prevPrevPoint));
      
      afterImageChange();
   }

   @Override
   public void plot(CalcColour colour, BigDecimal x, BigDecimal y)
   {
      if (graphics == null) {
         throw new RuntimeException("You may not call 'Plot' without first calling 'Range'");
      }
      graphics.setColor(colour.getColor());
      
      PointBigDecimal requestedPoint = new PointBigDecimal(x, y);
      Point point = mapPointToBitmapCoords(requestedPoint);
      
      // there is no "draw point", instead do a zero-length line
      graphics.drawLine(point.x, point.y, point.x, point.y);
      
      // store the drawn point, in case "line()" is called
      prevPrevPoint = prevPoint;
      prevPoint = point;

      afterImageChange();
   }

   @Override
   public void graphDot(BigDecimal dotX, BigDecimal dotY, GraphShading shading)
   {
      graphics.setColor(CalcColour.BLACK.getColor());
      PointBigDecimal requestedPoint = new PointBigDecimal(dotX, dotY);
      Point point = mapPointToBitmapCoords(requestedPoint);
      
      // there is no "draw point", instead do a zero-length line
      graphics.draw(new Line2D.Double(point, point));
      afterImageChange();

      if (shading == GraphShading.NONE) {
         return;
      }
      
      Color orange = CalcColour.ORANGE.getColor();
      int orangeR = orange.getRed();
      int orangeG = orange.getGreen();
      int orangeB = orange.getBlue();
      Color background = CalcColour.BACKGROUND.getColor();

      switch (shading)
      {
         case GREATER_THAN_FIRST:
            // Shade all pixels above the given dot in orange
            graphics.setColor(orange);
            graphics.drawLine(point.x, point.y-1, point.x, -1);
            break;
         case LESS_THAN_FIRST:
            // Shade all pixels below the given dot in orange
            graphics.setColor(orange);
            graphics.drawLine(point.x, point.y+1, point.x, HEIGHT_PIXELS);
            break;
         case GREATER_THAN_INTERSECT: 
         {
            // Remove any orange pixels below the given dot
            graphics.setColor(background);
            int[] pixelData = new int[4];
            for (int y = Math.max(0, point.y + 1); y < HEIGHT_PIXELS; y++) {
               imageRaster.getPixel(point.x, y, pixelData);
               if (pixelData[0] == orangeR
                     && pixelData[1] == orangeG
                     && pixelData[2] == orangeB)
               {
                  graphics.drawLine(point.x, y, point.x, y);
               }
            }
         }
            break;
         case LESS_THAN_INTERSECT:
         {
            // Remove any orange pixels above the given dot
            graphics.setColor(background);
            int[] pixelData = new int[4];
            for (int y = Math.min(point.y - 1, HEIGHT_PIXELS - 1); y >= 0; y--) {
               imageRaster.getPixel(point.x, y, pixelData);
               if (pixelData[0] == orangeR
                     && pixelData[1] == orangeG
                     && pixelData[2] == orangeB)
               {
                  graphics.drawLine(point.x, y, point.x, y);
               }
            }
         }
            break;
         default: throw new RuntimeException("Unexpected case: " + shading);
      }
      afterImageChange();
   }

   @Override
   public Iterable<BigDecimal> iterateGraphXValues()
   {
      return new Iterable<BigDecimal>() {
         @Override
         public Iterator<BigDecimal> iterator()
         {
            return new Iterator<BigDecimal>() {
               int idx = 0;
               
               @Override
               public void remove()
               {
                  throw new RuntimeException("Not supported");
               }
               
               @Override
               public BigDecimal next()
               {
                  // val = xMin + idx * (xMax - xMin) / WIDTH_PIXELS;
                  BigDecimal widthG = xMax.subtract(xMin, Evaluator.STORED_PRECISION);
                  BigDecimal unitsPerPixel = widthG.divide(WIDTH_PIXELS_BD, Evaluator.STORED_PRECISION);
                  BigDecimal offset = unitsPerPixel.multiply(new BigDecimal(idx + 0.5), Evaluator.STORED_PRECISION);
                  BigDecimal val = offset.add(xMin, Evaluator.STORED_PRECISION);
                  
                  idx ++;
                  return val;
               }
               
               @Override
               public boolean hasNext()
               {
                  return idx < WIDTH_PIXELS;
               }
            };
         }
      };
   }
   
   @Override
   public void clearScreen()
   {
      graphics.setColor(CalcColour.BACKGROUND.getColor());
      graphics.fillRect(0, 0, WIDTH_PIXELS, HEIGHT_PIXELS);
      afterImageChange();
   }
   
   private void afterImageChange()
   {
      frame.repaint();
   }

   /**
    * Maps a point in graph space to bitmap space
    */
   Point mapPointToBitmapCoords(
         PointBigDecimal point)
   {
      // x = (int)Math.round(((point.x - xMin) / (xMax - xMin) * WIDTH_PIXELS))
      BigDecimal xWidthG = xMax.subtract(xMin, Evaluator.STORED_PRECISION);
      BigDecimal xOffG = point.x.subtract(xMin, Evaluator.STORED_PRECISION);
      BigDecimal xB = xOffG.divide(xWidthG, Evaluator.STORED_PRECISION).multiply(WIDTH_PIXELS_MINUS_ONE, Evaluator.STORED_PRECISION);

      // y = (int)Math.round(((yMax - point.y) / (yMax - yMin) * HEIGHT_PIXELS)
      BigDecimal yHeightG = yMax.subtract(yMin, Evaluator.STORED_PRECISION);
      BigDecimal yOffG = yMax.subtract(point.y, Evaluator.STORED_PRECISION);
      BigDecimal yB = yOffG.divide(yHeightG, Evaluator.STORED_PRECISION).multiply(HEIGHT_PIXELS_MINUS_ONE, Evaluator.STORED_PRECISION);
      
      return new Point(
            // "scale" is not the same as precision.
            // setScale(0) will round to the nearest int.
            xB.setScale(0, RoundingMode.HALF_UP).intValueExact(),
            yB.setScale(0, RoundingMode.HALF_UP).intValueExact());
   }
   
   static class PointBigDecimal
   {
      BigDecimal x;
      BigDecimal y;
      
      public PointBigDecimal(BigDecimal x, BigDecimal y)
      {
         this.x = x;
         this.y = y;
      }
   }
}
