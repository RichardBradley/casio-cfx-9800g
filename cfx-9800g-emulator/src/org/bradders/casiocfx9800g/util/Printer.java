package org.bradders.casiocfx9800g.util;

import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.Token;

public class Printer
{
   /**
    * @return a string with the Node and its location in the file
    */
   public static String nodeToString(Node node)
   {
      Token token = NodeToToken.getFirstTokenForNode(node);
      
      if (token != null) {
         return String.format("'%s' (at line %s:%s)", node, token.getLine(), token.getPos());
      }
      return node.toString(); 
   }
}
