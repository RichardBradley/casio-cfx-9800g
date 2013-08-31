package org.bradders.casiocfx9800g.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConsoleUserInterface implements UserInterface
{
   private static final int CONSOLE_WIDTH = 21;
   private static final String FORMAT_STR = "%" + CONSOLE_WIDTH + "s";
   private static final int WIDTH_PIXELS = 96;
   private static final int HEIGHT_PIXELS = 64;
   private static final int IMAGE_SCALE = 4;
   
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
   private Graphics2D graphics;
   private Point2D.Double prevPoint;
   private Point2D.Double prevPrevPoint;
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
         System.in.read();
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
      Point2D.Double point = mapPointToBitmapCoords(requestedPoint);
      
      // there is no "draw point", instead do a zero-length line
      graphics.draw(new Line2D.Double(point, point));
      
      // store the drawn point, in case "line()" is called
      prevPrevPoint = prevPoint;
      prevPoint = point;

      afterImageChange();
   }
   
   private void afterImageChange()
   {
      frame.repaint();
   }

   /**
    * Maps a point in graph space to bitmap space
    */
   private Point2D.Double mapPointToBitmapCoords(
         Point2D.Double point)
   {
      return new Point2D.Double(
            (point.x - xMin) / (xMax - xMin) * WIDTH_PIXELS,
            (yMax - point.y) / (yMax - yMin) * HEIGHT_PIXELS);
   }
}
