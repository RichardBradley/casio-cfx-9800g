package org.bradders.casiocfx9800g;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.io.StringReader;

import org.junit.Test;

public class CompileTimeAnalyserTest
{
   @Test
   public void testDuplicateLabelIsError()
   {
      try {
         Main.compile(new RuntimeContext(), new StringReader(
               "1+1#:Lbl 1:1+1#:Lbl 2:1+1#:Lbl 1\n"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("Duplicate label"));
      }
   }

   @Test
   public void testFloatLabelIsError()
   {
      try {
         Main.compile(new RuntimeContext(), new StringReader(
               "Lbl 1.5:1+1#\n"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("Expecting an integer"));
      }
   }

   @Test
   public void testDanglingGotoIsError()
   {
      try {
         Main.compile(new RuntimeContext(), new StringReader(
               "1+1#\nGoto 4\n"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("unrecognised label"));
      }
   }
}
