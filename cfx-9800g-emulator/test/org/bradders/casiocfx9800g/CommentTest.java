package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.bradders.casiocfx9800g.parser.ParserException;
import org.junit.Test;

public class CommentTest extends EvaluationTestBase
{
   @Test
   public void TestComments() throws Exception
   {
      assertThat(
            outputOfProgram(
                  "// comment here\n" +
                  "1#\n" +
                  "// comment"),
            equalTo("1"));
   
      assertThat(
            outputOfProgram(
                  "1#\n" +
                  "// comments extend to end of line:2#\n" +
                  "3#"),
            equalTo("1\n3"));
   }
   
   @Test
   public void TestInvalidComments() throws Exception
   {
      try {
         outputOfProgram("1#// comments must be on a line by themselves");
         fail("expected Exception");
      } catch (ParserException e) {
         assertThat(e.getMessage(), containsString("[1,3] expecting: EOF"));
      }
   }
}
