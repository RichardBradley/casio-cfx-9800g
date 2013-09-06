package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SubroutineTest extends EvaluationTestBase
{
   @Test
   public void testLabelsAreNotSharedBetweenSubroutines() throws Exception
   {
      try
      {
         String main = "Lbl 1\nProg \"sub\"";
         fileContentsByFileName.put("sub", "Goto 1");
         outputOfProgram(main);

         fail("expected exception");
      }
      catch (Exception e)
      {
         assertThat(e.getMessage(), containsString("unrecognised label"));
         assertThat(e.getMessage(), containsString("\"sub\""));
      }
   }
   
   @Test
   public void testVariablesAreSharedBetweenSubroutines() throws Exception
   {
      String main = "99->A:Prog \"sub\":A#";
      fileContentsByFileName.put("sub", "A#:A+1->A");
      
      assertThat(outputOfProgram(main), equalTo("99\n100"));
   }
}
