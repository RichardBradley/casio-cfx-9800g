package org.bradders.casiocfx9800g.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScaledImageJLabel extends JLabel
{
   private Image image;

   public ScaledImageJLabel(Image image)
   {
      super();
      this.image = image;
   }
   
   @Override
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
   }
}
