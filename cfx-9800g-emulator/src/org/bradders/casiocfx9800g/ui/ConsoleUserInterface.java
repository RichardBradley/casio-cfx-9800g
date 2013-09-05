package org.bradders.casiocfx9800g.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConsoleUserInterface implements UserInterface
{
   private static final int CONSOLE_WIDTH = 21;
   private static final String FORMAT_STR = "%" + CONSOLE_WIDTH + "s";
   static final int WIDTH_PIXELS = 96;
   static final int HEIGHT_PIXELS = 64;
   private static final int IMAGE_SCALE = 4;
   
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
   private Graphics2D graphics;
   private WritableRaster imageRaster;
   private Point prevPoint;
   private Point prevPrevPoint;
   private double xMin;
   private double xMax;
   private double yMin;
   private double yMax;
   private JFrame frame;
   
   @Override
   public void printLine(String string)
   {
      System.out.println(string);
   }

   @Override
   public void printResult(double value)
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
   public double readValue()
   {
      try {
         while (true) {
            System.out.println("?");
            String line = in.readLine();
            try {
               return Double.parseDouble(line);
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
         double xMin, double xMax, double xScale,
         double yMin, double yMax, double yScale)
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
   public void plot(CalcColour colour, double x, double y)
   {
      if (graphics == null) {
         throw new RuntimeException("You may not call 'Plot' without first calling 'Range'");
      }
      graphics.setColor(colour.getColor());
      
      Point2D.Double requestedPoint = new Point2D.Double(x, y);
      Point point = mapPointToBitmapCoords(requestedPoint);
      
      // there is no "draw point", instead do a zero-length line
      graphics.drawLine(point.x, point.y, point.x, point.y);
      
      // store the drawn point, in case "line()" is called
      prevPrevPoint = prevPoint;
      prevPoint = point;

      afterImageChange();
   }

   @Override
   public void graphDot(double dotX, double dotY, GraphShading shading)
   {
      graphics.setColor(CalcColour.BLACK.getColor());
      Point2D.Double requestedPoint = new Point2D.Double(dotX, dotY);
      Point point = mapPointToBitmapCoords(requestedPoint);
      
      // there is no "draw point", instead do a zero-length line
      graphics.draw(new Line2D.Double(point, point));

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
   }

   @Override
   public Iterable<Double> iterateGraphXValues()
   {
      return new Iterable<Double>() {
         @Override
         public Iterator<Double> iterator()
         {
            return new Iterator<Double>() {
               int idx = 0;
               
               @Override
               public void remove()
               {
                  throw new RuntimeException("Not supported");
               }
               
               @Override
               public Double next()
               {
                  double val = xMin + idx * (xMax - xMin) / WIDTH_PIXELS;
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
   
   private void afterImageChange()
   {
      frame.repaint();
   }

   /**
    * Maps a point in graph space to bitmap space
    */
   Point mapPointToBitmapCoords(
         Point2D.Double point)
   {
      return new Point(
            (int)Math.round(((point.x - xMin) / (xMax - xMin) * WIDTH_PIXELS)),
            (int)Math.round(((yMax - point.y) / (yMax - yMin) * HEIGHT_PIXELS)));
   }
}
