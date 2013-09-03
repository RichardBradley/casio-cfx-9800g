package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.closeTo;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.bradders.casiocfx9800g.node.APrintvalStatement;
import org.bradders.casiocfx9800g.node.ASingleProgram;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.jmock.Expectations;
import org.jmock.Mockery;

public abstract class EvaluationTestBase
{
   protected RuntimeContext context = new RuntimeContext();
   protected Compiler compiler = new MockFileCompiler();
   protected Map<String, String> fileContentsByFileName = new HashMap<String, String>();
   
   /**
    * Compiles and evaluates a single expression
    */
   protected double evaluate(String expressionStr) throws Exception 
   {
      expressionStr = expressionStr + "#\n";
      
      CompiledFile file = compiler.compile(new StringReader(expressionStr));
      ASingleProgram prog = (ASingleProgram)file.ast.getPProgram();
      APrintvalStatement stm = (APrintvalStatement) prog.getStatement();
      PExpression expression = stm.getExpression();
      
      Evaluator evaluator = new Evaluator(context, null);
      return evaluator.evaluate(expression);
   }
   
   /**
    * Compiles and runs the given program, and asserts that it prints
    * only one value, equal to that given. 
    */
   protected void assertProgramPrints(String program, final double... values) throws Exception
   {
      Mockery mockery = new Mockery();
      RuntimeContext context = new RuntimeContext();
      final UserInterface userInterface = mockery.mock(UserInterface.class);
      
      mockery.checking(new Expectations() {{
         for (double value : values) {
           oneOf(userInterface).printResult(with(closeTo(value, 1e-6)));
         }
         allowing(userInterface).printLine(with(any(String.class)));
      }});
      
      CompiledFile file = compiler.compile(new StringReader(program));
      StatementRunner runner = new StatementRunner(context, compiler, userInterface);
      runner.run(file);
      
      mockery.assertIsSatisfied();
   }
   
   private class MockFileCompiler extends Compiler
   {
      @Override
      protected Reader openFile(String filename) throws FileNotFoundException
      {
         String fileContents = fileContentsByFileName.get(filename);
         if (fileContents == null) {
            throw new FileNotFoundException(filename);
         }
         return new StringReader(fileContents);
      }
   }
}
