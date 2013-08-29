package org.bradders.casiocfx9800g;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PushbackReader;
import java.io.Reader;

import org.bradders.casiocfx9800g.lexer.Lexer;
import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.parser.Parser;
import org.bradders.casiocfx9800g.util.ParseTreePrinterAdapter;

/**
 * The entry class for the Casio CFX 9800G emulator
 */
public class Main
{
   /**
    * The entry method.
    * @throws Exception 
    */
   public static void main(String[] args) throws Exception
   {
      if (args.length != 1)
      {
         System.err.println("Expecting exactly one argument.");
         System.err.println("Usage: java -jar casio-cfx-9800g.jar <filename>");
         System.err.println();
         System.err.println("Where 'filename' is the path to a text file containing the program to");
         System.err.println("be run.");
         System.exit(1);
         return;
      }
      
      BufferedReader file = new BufferedReader(new FileReader(args[0]));
      
      RuntimeContext context = new RuntimeContext();

      Start ast = compile(context, file);
      
      ast.apply(new ParseTreePrinterAdapter());
   }

   public static Start compile(RuntimeContext context, Reader programText)
      throws Exception
   {
      Lexer lexer = new Lexer(new PushbackReader(programText, 1024));
      Parser parser = new Parser(lexer);
      Start ast = parser.parse();

      // apply compile-time checks
      ast.apply(new CompileTimeAnalyser(context));
      return ast;
   }
}
