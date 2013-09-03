package org.bradders.casiocfx9800g;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bradders.casiocfx9800g.node.PStatement;
import org.bradders.casiocfx9800g.node.Start;

public class CompiledFile
{
   public Start ast;
   public List<PStatement> statements = new ArrayList<PStatement>();
   public Map<Integer, Integer> statementIdxByLabelNumber = new HashMap<Integer, Integer>();
}
