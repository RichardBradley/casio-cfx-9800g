package org.bradders.casiocfx9800g;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PushbackReader;

import org.bradders.casiocfx9800g.lexer.Lexer;
import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.parser.Parser;

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
      
      Lexer lexer = new Lexer(new PushbackReader(new BufferedReader(new FileReader(args[0])), 1024));
      Parser parser = new Parser(lexer);
      Start ast = parser.parse();

      RuntimeContext context = new RuntimeContext();

      // apply compile-time checks
      ast.apply(new CompileTimeAnalyser());
      
      // run the program
      // qq
   }
}
