package org.bradders.casiocfx9800g.ui;

public interface UserInterface
{
   void printLine(String string);

   void printResult(double value);

   double readValue();

   /**
    * Draws a line between the last two Plot points
    */
   void line(CalcColour colour);

   /**
    * Initialise the drawing surface.
    */
   void range(
         double xMin, double xMax, double xScale,
         double yMin, double yMax, double yScale);

   /**
    * Draws a dot at the given coords
    * 
    * @param colour
    * @param x
    * @param y
    */
   void plot(CalcColour colour, double x, double y);
}
