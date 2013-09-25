package org.bradders.casiocfx9800g.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.bradders.casiocfx9800g.Evaluator;

public class ConsoleUserInterface implements UserInterface
{
   private static final int CONSOLE_WIDTH = 21;
   private static final String FORMAT_STR = "%" + CONSOLE_WIDTH + "s";
   
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
   private JFrame frame;
   GraphImageJLabel graph;
   
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
      graph = new GraphImageJLabel();
      graph.range(xMin, xMax, xScale, yMin, yMax, yScale);
      addImageToFrameAndShow(graph);
   }

   protected void addImageToFrameAndShow(JLabel label)
   {
      frame = new JFrame("cfx-9800g-emulator");
      
      frame.add(label);
      frame.pack();
      frame.setVisible(true);
      
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   

   @Override
   public void line(CalcColour colour)
   {
      graph.line(colour);
   }

   @Override
   public void plot(CalcColour colour, BigDecimal x, BigDecimal y)
   {
      graph.plot(colour, x, y);
   }

   @Override
   public void graphDot(BigDecimal dotX, BigDecimal dotY, GraphShading shading)
   {
      graph.graphDot(dotX, dotY, shading);
   }

   @Override
   public Iterable<BigDecimal> iterateGraphXValues()
   {
      return graph.iterateGraphXValues();
   }

   @Override
   public void clearScreen()
   {
      graph.clearScreen();
   }
}
