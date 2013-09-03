package org.bradders.casiocfx9800g;

import java.util.HashMap;
import java.util.Map;

import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.util.Printer;

/**
 * The state of a running program under interpretation.
 */
public class RuntimeContext
{
   public Map<String, Double> variableValues = new HashMap<String, Double>();
   
   public double getVariableValue(String name, Node location)
   {
      Double entry = variableValues.get(name);
      if (entry == null) {
         throw new RuntimeException("Use of uninitialised variable at " +
               Printer.nodeToString(location));
      }
      return entry;
   }

   public void setVariableValue(String name, double value)
   {
      variableValues.put(name, value);
   }

   public void memoryClear()
   {
      variableValues.clear();
   }
}
