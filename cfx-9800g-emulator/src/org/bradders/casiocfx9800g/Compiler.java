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
         File file = resolveFilename(filename);
         Reader programText = openFile(file);
         CompiledFile out = compile(programText);
         out.setFile(file);
         loadedFilesByName.put(filename, out);
      }
      return loadedFilesByName.get(filename);
   }
   
   protected File resolveFilename(String filename)
   {
      File file = new File(filename);
      if (!file.isAbsolute()) {
         file = new File(baseDir, filename);
      }
      return file;
   }

   protected Reader openFile(File file)
         throws FileNotFoundException
   {
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
