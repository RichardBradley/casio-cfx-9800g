package org.bradders.casiocfx9800g.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScaledImageJLabel extends JLabel
{
   protected BufferedImage image;

   public ScaledImageJLabel(BufferedImage image)
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
