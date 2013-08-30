package org.bradders.casiocfx9800g.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInterface implements UserInterface
{
   private static final int CONSOLE_WIDTH = 21;
   private static final String FORMAT_STR = "%" + CONSOLE_WIDTH + "s";
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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
}
