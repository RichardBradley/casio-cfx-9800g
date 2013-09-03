package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;

public class CompileTimeAnalyserTest
{
   private Compiler compiler = new Compiler();
   
   @Test
   public void testDuplicateLabelIsError()
   {
      try {
         compiler.compile(new StringReader(
               "1+1#:Lbl 1:1+1#:Lbl 2:1+1#:Lbl 1"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("Duplicate label"));
      }
   }

   @Test
   public void testFloatLabelIsError()
   {
      try {
         compiler.compile(new StringReader(
               "Lbl 1.5:1+1#"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("Expecting an integer"));
      }
   }

   @Test
   public void testDanglingGotoIsError()
   {
      try {
         compiler.compile(new StringReader(
               "1+1#\nGoto 4"));
         fail("Expected exception");
      } catch (Exception e) {
         assertThat(e.getMessage(), containsString("unrecognised label"));
      }
   }
}
