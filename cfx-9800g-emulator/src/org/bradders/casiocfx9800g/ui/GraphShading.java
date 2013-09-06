package org.bradders.casiocfx9800g.ui;

public enum GraphShading
{
   /**
    * Do no shading
    */
   NONE,
   /**
    * Shade all pixels below the given dot in orange
    */
   LESS_THAN_FIRST,
   /**
    * Remove any orange pixels above the given dot
    */
   LESS_THAN_INTERSECT,
   /**
    * Shade all pixels above the given dot in orange 
    */
   GREATER_THAN_FIRST,
   /**
    * Remove any orange pixels below the given dot
    */
   GREATER_THAN_INTERSECT;
}
