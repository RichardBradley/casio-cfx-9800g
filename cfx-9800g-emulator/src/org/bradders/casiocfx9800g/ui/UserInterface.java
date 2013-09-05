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
    * Draws a dot at the given coords.
    * The location of the dot is recorded for the 'line' function
    * 
    * @param colour
    * @param x
    * @param y
    */
   void plot(CalcColour colour, double x, double y);
   
   /**
    * Draws a black dot at the given location, for a graph line.
    * The location is not used for 'line'.
    * 
    * If shading is given, then the line is part of an inequality graph.
    * See comments on GraphShading
    */
   void graphDot(double x, double y, GraphShading shading);

   /**
    * @return the value of X for each horizontal pixel column in the current range
    */
   Iterable<Double> iterateGraphXValues();
}
