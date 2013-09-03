package org.bradders.casiocfx9800g;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.bradders.casiocfx9800g.lexer.Lexer;
import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.parser.Parser;

public class Compiler
{
   Map<String, CompiledFile> loadedFilesByName = new HashMap<String, CompiledFile>();
   private File baseDir;

   public CompiledFile loadFile(String filename) throws Exception
   {
      if (!loadedFilesByName.containsKey(filename)) {
         Reader programText = openFile(filename);
         CompiledFile out = compile(programText);
         loadedFilesByName.put(filename, out);
      }
      return loadedFilesByName.get(filename);
   }

   protected Reader openFile(String filename)
         throws FileNotFoundException
   {
      File file = new File(filename);
      if (!file.isAbsolute()) {
         file = new File(baseDir, filename);
      }
      
      return new BufferedReader(new FileReader(file));
   }

   public CompiledFile compile(Reader programText)
         throws Exception
   {
      Lexer lexer = new Lexer(new PushbackReader(programText, 1024));
      Parser parser = new Parser(lexer);
      Start ast = parser.parse();

      // apply compile-time checks
      CompiledFile file = new CompiledFile();
      file.ast = ast;
      ast.apply(new CompileTimeAnalyser(file));
      return file;
   }

   public void setBaseDir(File baseDir)
   {
      this.baseDir = baseDir;
   }
}
