package org.bradders.casiocfx9800g;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.ui.CalcColour;
import org.bradders.casiocfx9800g.ui.GraphShading;
import org.bradders.casiocfx9800g.ui.UserInterface;

public abstract class EvaluationTestBase
{
   protected RuntimeContext context = new RuntimeContext();
   protected Evaluator evaluator = new Evaluator(context, null);
   protected Compiler compiler = new MockFileCompiler();
   protected Map<String, String> fileContentsByFileName = new HashMap<String, String>();
   
   /**
    * Compiles and evaluates a single expression
    */
   protected String evaluate(String expressionStr) throws Exception 
   {
      expressionStr = expressionStr + "#\n";
      
      CompiledFile file = compiler.compile(new StringReader(expressionStr));
      ASingleProgram prog = (ASingleProgram)file.ast.getPProgram();
      APrintvalStatement stm = (APrintvalStatement) prog.getStatement();
      PExpression expression = stm.getExpression();
      
      BigDecimal val = evaluator.evaluate(expression);
      return Evaluator.formatForDisplay(val);
   }
   
   /**
    * Compiles and runs the given program, and records the output in a string.
    * The output format is simplified, in that the values are not right aligned,
    * and "- Disp -" is not added after each val.
    */
   protected String outputOfProgram(String program) throws Exception
   {
      RuntimeContext context = new RuntimeContext();
      OutputCapturingUserInterface ui = new OutputCapturingUserInterface();
      CompiledFile file = compiler.compile(new StringReader(program));
      StatementRunner runner = new StatementRunner(context, compiler, ui);
      runner.run(file);
      
      return join(ui.printedValues);
   }
   
   private static String join(Iterable<String> vals)
   {
      String sep = "";
      StringBuilder acc = new StringBuilder();
      for (String val : vals) {
         acc.append(sep);
         acc.append(val);
         sep = "\n";
      }
      return acc.toString();
   }
   
   private class MockFileCompiler extends Compiler
   {
      @Override
      protected Reader openFile(File file) throws FileNotFoundException
      {
         String fileContents = fileContentsByFileName.get(file.toString());
         if (fileContents == null) {
            throw new FileNotFoundException(file.toString());
         }
         return new StringReader(fileContents);
      }
   }
   
   protected static BigDecimal bd(double val)
   {
      return new BigDecimal(val, Evaluator.STORED_PRECISION);
   }
   
   protected static BigDecimal bd(int val)
   {
      return new BigDecimal(val, Evaluator.STORED_PRECISION);
   }
   
   private static class OutputCapturingUserInterface implements UserInterface
   {
      public List<String> printedValues = new ArrayList<String>();

      @Override
      public void printResult(String value)
      {
         printedValues.add(value);
      }

      @Override
      public void printLine(String string)
      {
         printedValues.add(string);
      }

      @Override
      public BigDecimal readValue()
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public void line(CalcColour colour)
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public void range(
            BigDecimal xMin, BigDecimal xMax, BigDecimal xScale,
            BigDecimal yMin, BigDecimal yMax, BigDecimal yScale)
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public void plot(CalcColour colour, BigDecimal x, BigDecimal y)
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public void graphDot(BigDecimal x, BigDecimal y, GraphShading shading)
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public Iterable<BigDecimal> iterateGraphXValues()
      {
         throw new RuntimeException("Not supported");
      }

      @Override
      public void clearScreen()
      {
         throw new RuntimeException("Not supported");
      }
   }
}
