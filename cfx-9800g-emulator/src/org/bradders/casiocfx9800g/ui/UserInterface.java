package org.bradders.casiocfx9800g.ui;

import java.math.BigDecimal;

public interface UserInterface
{
   void printLine(String string);

   void printResult(String value);

   /**
    * Reads in a number from the user.
    * The value should use Evaluator.STORED_PRECISION
    */
   BigDecimal readValue();

   /**
    * Draws a line between the last two Plot points
    */
   void line(CalcColour colour);

   /**
    * Initialise the drawing surface.
    */
   void range(
         BigDecimal xMin, BigDecimal xMax, BigDecimal xScale,
         BigDecimal yMin, BigDecimal yMax, BigDecimal yScale);

   /**
    * Draws a dot at the given coords.
    * The location of the dot is recorded for the 'line' function
    * 
    * @param colour
    * @param x
    * @param y
    */
   void plot(CalcColour colour, BigDecimal x, BigDecimal y);
   
   /**
    * Draws a black dot at the given location, for a graph line.
    * The location is not used for 'line'.
    * 
    * If shading is given, then the line is part of an inequality graph.
    * See comments on GraphShading
    */
   void graphDot(BigDecimal x, BigDecimal y, GraphShading shading);

   /**
    * @return the value of X for each horizontal pixel column in the current range
    */
   Iterable<BigDecimal> iterateGraphXValues();

   void clearScreen();
}
