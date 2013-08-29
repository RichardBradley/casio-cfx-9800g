package org.bradders.casiocfx9800g.util;

import org.bradders.casiocfx9800g.analysis.DepthFirstAdapter;
import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.Token;

public class NodeToToken extends DepthFirstAdapter
{
   /**
    * Returns the first token which makes up the given Node, or null if one
    * could not be found.
    */
   public static Token getFirstTokenForNode(Node node)
   {
      NodeToToken inst = new NodeToToken();
      try
      {
         node.apply(inst);
         return null;
      }
      catch (TokenFoundException e)
      {
         return e.token;
      }
   }
   
   @Override
   public void defaultIn(Node node)
   {
      if (node instanceof Token) {
         throw new TokenFoundException((Token) node);
      }
      super.defaultIn(node);
   }
   
   @Override
   public void defaultCase(Node node)
   {
      if (node instanceof Token) {
         throw new TokenFoundException((Token) node);
      }
      super.defaultCase(node);
   }
   
   @SuppressWarnings("serial")
   private static class TokenFoundException extends RuntimeException
   {
      public TokenFoundException(Token token)
      {
         this.token = token;
      }

      public final Token token;
   }
}
