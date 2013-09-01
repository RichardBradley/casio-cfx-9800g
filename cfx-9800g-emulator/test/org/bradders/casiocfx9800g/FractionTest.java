package org.bradders.casiocfx9800g;

import java.io.StringReader;

import org.bradders.casiocfx9800g.node.Start;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.ParseTreePrinterAdapter;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FractionTest
{
   @Test
   public void testFractions() throws Exception
   {
      assertProgramPrints("1|3#", 0.333333);
      assertProgramPrints("1|3->A:A+1#", 1.333333);
      assertProgramPrints("1|3->A:A+1#", 1.333333);
      assertProgramPrints("1|3->A:A+1|7|8#", 2.2083333);
   }
   
   /**
    * TODO: if/when the emulator supports actual fractions, instead of just
    * converting them to doubles, then this test should pass
   @Test
   public void testRealFractions() throws Exception
   {
      assertProgramPrints("1|3->A:A+1#", "4|3");
      assertProgramPrints("1|3->A:A+1#", "4|3");
      assertProgramPrints("1|3->A:A+1|7|8#", "77|24");
   }
    */
   
   private void assertProgramPrints(String program, final double value) throws Exception
   {
      Mockery mockery = new Mockery();
      RuntimeContext context = new RuntimeContext();
      final UserInterface userInterface = mockery.mock(UserInterface.class);
      
      mockery.checking(new Expectations() {{
         oneOf(userInterface).printResult(with(closeTo(value, 1e-6)));
         allowing(userInterface).printLine(with(any(String.class)));
      }});
      
      Main.compile(context , new StringReader(program));
      StatementRunner runner = new StatementRunner(context, userInterface);
      runner.run();
      
      mockery.assertIsSatisfied();
   }
}
