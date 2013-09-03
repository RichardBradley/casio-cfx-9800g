package org.bradders.casiocfx9800g;

import java.io.File;

import org.bradders.casiocfx9800g.ui.ConsoleUserInterface;

/**
 * The entry class for the Casio CFX 9800G emulator
 */
public class Main
{
   /**
    * Could become a command line arg
    */
   public static boolean TRACE_EXECUTION = false;

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
      
      File fileLocation = new File(args[0]); 
      Compiler compiler = new Compiler();
      compiler.setBaseDir(fileLocation.getAbsoluteFile().getParentFile());
      CompiledFile file = compiler.loadFile(fileLocation.getAbsolutePath());
      RuntimeContext context = new RuntimeContext();
      ConsoleUserInterface ui = new ConsoleUserInterface();      
      StatementRunner runner = new StatementRunner(context, compiler, ui);
      if (TRACE_EXECUTION) {
         runner.traceExecution = true;
      }
      runner.run(file);
      ui.printLine("(terminated)");
   }
}
