package org.bradders.casiocfx9800g.util;

import org.bradders.casiocfx9800g.analysis.DepthFirstAdapter;
import org.bradders.casiocfx9800g.node.Node;

public class ParseTreePrinterAdapter extends DepthFirstAdapter
{
   private int depth = 0;

   @Override
   public void defaultIn(Node node)
   {
      super.defaultIn(node);
      depth++;
   }
   
   @Override
   public void defaultOut(Node node)
   {
      super.defaultOut(node);
      depth--;
   }
   
   @Override
   public void defaultCase(Node node)
   {
      super.defaultCase(node);
      for (int i=0; i<depth; i++) {
         System.out.print(' ');
      }
      System.out.println(node);
   }
}
