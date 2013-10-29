package org.bradders.casiocfx9800g;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.bradders.casiocfx9800g.lexer.Lexer;
import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.parser.Parser;

public class Compiler
{
   Map<URL, CompiledFile> loadedFilesByURL = new HashMap<URL, CompiledFile>();
   private UrlResolverWithBaseDir urlResolver = new UrlResolverWithBaseDir();

   /**
    * Loads a file from a relative path.
    * 
    */
   public CompiledFile loadFileRelative(String filename) throws Exception
   {
      URL file = urlResolver.resolveFilename(filename);
      return loadFile(file);
   }
   
   public CompiledFile loadFile(URL file) throws Exception
   {
      if (!loadedFilesByURL.containsKey(file)) {
         Reader programText = openFile(file);
         CompiledFile out = compile(programText);
         out.setFile(file);
         loadedFilesByURL.put(file, out);
      }
      return loadedFilesByURL.get(file);
   }

   protected Reader openFile(URL file)
         throws IOException
   {
      InputStream urlStream = new BufferedInputStream(file.openStream());
      InputStreamReader reader = new InputStreamReader(urlStream, Charsets.UTF_8);
      return reader;
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

   public String getFilename(URL url)
   {
      return urlResolver.getFilename(url);
   }

   public void setBaseDir(URL url)
   {
      urlResolver.setBaseDir(url);
   }
}
