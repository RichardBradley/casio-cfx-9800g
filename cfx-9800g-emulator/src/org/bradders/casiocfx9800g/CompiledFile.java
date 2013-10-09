package org.bradders.casiocfx9800g;

import java.io.File;
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
   private File file;
   
   public File getFile()
   {
      return file;
   }
   public void setFile(File file)
   {
      this.file = file;
   }
}
