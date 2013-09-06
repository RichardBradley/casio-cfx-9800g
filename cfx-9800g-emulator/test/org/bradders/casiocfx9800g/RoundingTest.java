package org.bradders.casiocfx9800g;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class RoundingTest extends EvaluationTestBase
{
   @Test
   public void testRounding() throws Exception
   {
      assertThat(evaluate("10*(Frac (213*0.1))"), equalTo("3"));
   }
   
   /**
    * The calc uses a decimal floating point number storage.
    * It displays 10 s.f., but stores 15 s.f.
    * See CFX-9800G.pdf p365
    */
   @Test
   public void testHiddenDigits() throws Exception
   {
      String prog = "15->I\n" +
      		"0.5->A\n" +
      		"Lbl 1\n" +
      		"A*0.1->A\n" +
      		"1+A#\n" +
      		"(1+A)=1=>\"EQ\"\n" +
      		"Dsz I:Goto 1\n";
      
      String expectedOutput = 
         "1.05\n" +
         "1.005\n" +
         "1.0005\n" +
         "1.00005\n" +
         "1.000005\n" +
         "1.0000005\n" +
         "1.00000005\n" +
   		 "1.000000005\n" +
   		 // the display is rounded to 10 s.f., using ROUND_HALF_UP
         "1.000000001\n" +
         // the value is displayed with 10 s.f., so appears as 1
         // but it is stored with 15 s.f., so is not equal to 1
   		 "1\n" +
         "1\n" +
         // this value is now equal to 1
         "1\n" +
         "EQ\n" +
         "1\n" +
         "EQ\n" +
         "1\n" +
         "EQ\n" +
         "1\n" +
         "EQ";
      
      assertThat(outputOfProgram(prog), equalTo(expectedOutput));
   }
   
   /**
    * This test demonstrates that the casio uses HALF_UP, not e.g. ROUND_HALF_EVEN
    */
   @Test
   public void testRound() throws Exception
   {
      assertThat(evaluate("1+5e-10"), equalTo("1.000000001"));
      
      BigDecimal val = BigDecimal.ONE.add(new BigDecimal("5e-10"), Evaluator.STORED_PRECISION);
      assertThat(
            val.round(new MathContext(10, RoundingMode.HALF_EVEN)).stripTrailingZeros().toString(),
            equalTo("1"));
      assertThat(
            val.round(new MathContext(10, RoundingMode.HALF_UP)).toString(),
            equalTo("1.000000001"));
   }
   
   /**
    * See http://stackoverflow.com/questions/4134047/java-bigdecimal-round-to-the-nearest-whole-value
    */
   @Test
   public void testBigDecimal()
   {
      // "scale" is not the same as precision.
      // setScale(0) will round to the nearest int.
      assertThat(new BigDecimal("123.12").setScale(0, BigDecimal.ROUND_HALF_UP).toString(), equalTo("123"));
      assertThat(new BigDecimal("123.50").setScale(0, BigDecimal.ROUND_HALF_UP).toString(), equalTo("124"));
      assertThat(new BigDecimal("123.72").setScale(0, BigDecimal.ROUND_HALF_UP).toString(), equalTo("124"));
   }
   
   /**
    * The following examples have been tested on the calc emulator
    */
   @Test
   public void testFormatForDisplay() throws Exception
   {
      assertThat(evaluate("123456789"), equalTo("123456789"));
      assertThat(evaluate("1234567890"), equalTo("1234567890"));
      assertThat(evaluate("1234567890.1"), equalTo("1234567890"));
      assertThat(evaluate("12345678901"), equalTo("1.23456789E+10"));
      assertThat(evaluate("12340000000"), equalTo("1.234E+10"));
      assertThat(evaluate("1234e-11"), equalTo("1.234E-8")); // Note, calc shows 1.234E-08
      assertThat(evaluate("1234e-6"), equalTo("0.001234")); // Note, calc shows 1.234E-03
      assertThat(evaluate("1234e-5"), equalTo("0.01234"));
      assertThat(evaluate("1234.567891"), equalTo("1234.567891"));
      assertThat(evaluate("1.234567891"), equalTo("1.234567891"));
      assertThat(evaluate("0.01234567891"), equalTo("0.01234567891"));
      assertThat(evaluate("0.001234567891"), equalTo("0.001234567891")); // Note, calc shows 1.234567891E-3
      assertThat(evaluate("1.000"), equalTo("1"));
      
      BigDecimal[] array = {  
            new BigDecimal("1.50000"),  
            new BigDecimal("10.00000"),  
            new BigDecimal("1.234567890"),  
            new BigDecimal("1.23e35"),  
        };  
        for (BigDecimal bd: array)  
        {  
            BigDecimal stripped = bd.stripTrailingZeros();  
            System.out.printf("%s, %s, %s%n", bd, stripped, stripped.toPlainString());  
        }  
   }
}
