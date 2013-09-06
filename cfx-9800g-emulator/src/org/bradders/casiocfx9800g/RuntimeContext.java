package org.bradders.casiocfx9800g;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.util.Printer;

/**
 * The state of a running program under interpretation.
 */
public class RuntimeContext
{
   public Map<String, BigDecimal> variableValues = new HashMap<String, BigDecimal>();
   
   public BigDecimal getVariableValue(String name, Node location)
   {
      BigDecimal entry = variableValues.get(name);
      if (entry == null) {
         throw new RuntimeException("Use of uninitialised variable at " +
               Printer.nodeToString(location));
      }
      return entry;
   }

   public void setVariableValue(String name, BigDecimal value)
   {
      variableValues.put(name, value);
   }

   public void setVariableValue(String name, int value)
   {
      variableValues.put(name, new BigDecimal(value, Evaluator.STORED_PRECISION));
   }
   
   public void memoryClear()
   {
      variableValues.clear();
   }
}
