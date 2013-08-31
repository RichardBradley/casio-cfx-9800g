package org.bradders.casiocfx9800g.ui;

import java.awt.Color;

public enum CalcColour
{
   BACKGROUND,
   BLACK,
   ORANGE,
   GREEN;
   
   private Color color;
   
   public int getARGB()
   {
      switch (this) {
      case BACKGROUND: return 0x647D50;
      case BLACK: return 0x312329;
      case ORANGE: return 0x674516;
      case GREEN: return 0x00422A;
      default: throw new RuntimeException("Unexpected case: " + this);
      }
   }
   
   public Color getColor()
   {
      if (color == null) {
         color = new Color(getARGB());
      }
      return color;
   }
}
