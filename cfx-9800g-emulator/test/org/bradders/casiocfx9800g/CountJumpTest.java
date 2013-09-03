package org.bradders.casiocfx9800g;

import java.io.StringReader;

import org.bradders.casiocfx9800g.ui.UserInterface;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.junit.Test;

public class CountJumpTest
{
   private Mockery mockery = new Mockery();
   private RuntimeContext context = new RuntimeContext();
   
   /**
    * This example is given on p307 of CFX-9800G.pdf
    * @throws Exception 
    */
   @Test
   public void testAverage10NumsExampleProg() throws Exception
   {
      String prog = "10->A:0->C:Lbl 1:?->B:B+C->C:Dsz A:Goto 1:C/10#\n";
      final UserInterface userInterface = mockery.mock(UserInterface.class);
      
      final States inputSeq = mockery.states("inputSeq").startsAs("1");
      mockery.checking(new Expectations() {{
         allowing(userInterface).readValue(); will(returnValue(1.0)); when(inputSeq.is("1")); then(inputSeq.is("2"));
         allowing(userInterface).readValue(); will(returnValue(2.0)); when(inputSeq.is("2")); then(inputSeq.is("3"));
         allowing(userInterface).readValue(); will(returnValue(3.0)); when(inputSeq.is("3")); then(inputSeq.is("4"));
         allowing(userInterface).readValue(); will(returnValue(4.0)); when(inputSeq.is("4")); then(inputSeq.is("5"));
         allowing(userInterface).readValue(); will(returnValue(5.0)); when(inputSeq.is("5")); then(inputSeq.is("6"));
         allowing(userInterface).readValue(); will(returnValue(6.0)); when(inputSeq.is("6")); then(inputSeq.is("7"));
         allowing(userInterface).readValue(); will(returnValue(7.0)); when(inputSeq.is("7")); then(inputSeq.is("8"));
         allowing(userInterface).readValue(); will(returnValue(8.0)); when(inputSeq.is("8")); then(inputSeq.is("9"));
         allowing(userInterface).readValue(); will(returnValue(9.0)); when(inputSeq.is("9")); then(inputSeq.is("10"));
         allowing(userInterface).readValue(); will(returnValue(10.0)); when(inputSeq.is("10")); then(inputSeq.is("11"));

         oneOf(userInterface).printResult(5.5);
         allowing(userInterface).printLine(with(any(String.class)));
      }});
      
      Compiler compiler = new Compiler();
      CompiledFile file = compiler.compile(new StringReader(prog));
      StatementRunner runner = new StatementRunner(context, compiler, userInterface);
      runner.run(file);
      
      mockery.assertIsSatisfied();
   }
   

}
