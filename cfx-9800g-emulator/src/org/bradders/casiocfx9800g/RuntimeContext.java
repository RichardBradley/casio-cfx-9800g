package org.bradders.casiocfx9800g;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bradders.casiocfx9800g.node.AVarAtom;
import org.bradders.casiocfx9800g.node.PStatement;

/**
 * The state of a running program under interpretation.
 */
public class RuntimeContext
{
   public List<PStatement> statements = new ArrayList<PStatement>();
   public Map<Integer,Integer> statementIdxByLabelNumber = new HashMap<Integer, Integer>();
   
   public double getVariableValue(String text, AVarAtom expression)
   {
      throw new RuntimeException("qq TODO");
   }

}
