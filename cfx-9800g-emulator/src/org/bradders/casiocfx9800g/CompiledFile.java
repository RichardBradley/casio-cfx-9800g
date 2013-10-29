package org.bradders.casiocfx9800g;

import java.net.URL;
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
   private URL file;
   
   public URL getFile()
   {
      return file;
   }
   public void setFile(URL file)
   {
      this.file = file;
   }
}
