package org.bradders.casiocfx9800g;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.bradders.casiocfx9800g.node.ADivMultdiv;
import org.bradders.casiocfx9800g.node.AExpressionAtom;
import org.bradders.casiocfx9800g.node.AFactorialPostfixop;
import org.bradders.casiocfx9800g.node.AFunc1Func;
import org.bradders.casiocfx9800g.node.AFunc2Func;
import org.bradders.casiocfx9800g.node.AFuncNoArgsFunc;
import org.bradders.casiocfx9800g.node.AInputAtom;
import org.bradders.casiocfx9800g.node.AMinusExpression;
import org.bradders.casiocfx9800g.node.AMultMultdiv;
import org.bradders.casiocfx9800g.node.AMultMultgroup;
import org.bradders.casiocfx9800g.node.ANegatePrefixop;
import org.bradders.casiocfx9800g.node.ANumberAtom;
import org.bradders.casiocfx9800g.node.APairFrac;
import org.bradders.casiocfx9800g.node.APlusExpression;
import org.bradders.casiocfx9800g.node.APowerPow;
import org.bradders.casiocfx9800g.node.ASequenceAtomList;
import org.bradders.casiocfx9800g.node.ASequenceExpressionList;
import org.bradders.casiocfx9800g.node.ASingleAtomList;
import org.bradders.casiocfx9800g.node.ASingleExpression;
import org.bradders.casiocfx9800g.node.ASingleExpressionList;
import org.bradders.casiocfx9800g.node.ASingleFrac;
import org.bradders.casiocfx9800g.node.ASingleFunc;
import org.bradders.casiocfx9800g.node.ASingleMultdiv;
import org.bradders.casiocfx9800g.node.ASingleMultgroup;
import org.bradders.casiocfx9800g.node.ASinglePostfixop;
import org.bradders.casiocfx9800g.node.ASinglePow;
import org.bradders.casiocfx9800g.node.ASinglePrefixop;
import org.bradders.casiocfx9800g.node.ATripleFrac;
import org.bradders.casiocfx9800g.node.AVarAtom;
import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.PAtom;
import org.bradders.casiocfx9800g.node.PAtomList;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.PExpressionList;
import org.bradders.casiocfx9800g.node.PFrac;
import org.bradders.casiocfx9800g.node.PFunc;
import org.bradders.casiocfx9800g.node.PMultdiv;
import org.bradders.casiocfx9800g.node.PMultgroup;
import org.bradders.casiocfx9800g.node.PPostfixop;
import org.bradders.casiocfx9800g.node.PPow;
import org.bradders.casiocfx9800g.node.PPrefixop;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.node.TQuotedText;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.Printer;

/**
 * This class evaluates nodes in the AST.
 * 
 * It would be nicer if this were OO, but SableCC enforces a visitor pattern.
 * 
 * All intermediate values are BigDecimals with precision STORED_PRECISION.
 * To display a value, use "formatForDisplay" which uses DISPLAY_PRECISION
 */
public class Evaluator
{
   private final RuntimeContext context;
   private final UserInterface userInterface;
   private final Random rnd = new Random();
   
   /**
    * See CFX-9800G.pdf p365
    * 
    * The manual says that 15 s.f. are used internally, but see RoundingTest
    */
   public static final MathContext STORED_PRECISION = new MathContext(12, RoundingMode.HALF_UP);
   public static final MathContext DISPLAY_PRECISION = new MathContext(10, RoundingMode.HALF_UP);

   public Evaluator(RuntimeContext context, UserInterface userInterface)
   {
      this.context = context;
      this.userInterface = userInterface;
   }
   
   public String formatForDisplay(BigDecimal value)
   {
      value = value.round(DISPLAY_PRECISION).stripTrailingZeros();
      if (value.scale() <= 0) {
         // Scale <= 0 => value is (unscaledValue × 10^(-scale))
         if (-value.scale() + value.precision() <= 10) {
            return value.toPlainString();
         }
      }
      return value.toString();
   }

   public static int getInt(TNumberLiteral numberLiteral, Node location)
   {
      try {
         return Integer.parseInt(numberLiteral.getText());
      } catch (Exception e) {
         throw new CompileException(String.format(
               "Expecting an integer, found '%s' at %s",
               numberLiteral,
               Printer.nodeToString(location)));
      }
   }

   public static String getString(TQuotedText textLiteral, Node location)
   {
      String text = textLiteral.getText();
      if (text.charAt(0) != '"' || text.charAt(text.length() - 1) != '"') {
         throw new CompileException(String.format(
               "Expecting a string in quotes, found '%s' at %s", 
               textLiteral,
               Printer.nodeToString(location)));
      }
      return text.substring(1, text.length() - 1);
   }

   /**
    * Evaluates an expression:
    * <code>
        expression =
                  {single} [single]:multdiv |
                  {plus} [left]:expression plus [right]:multdiv |
                  {minus} [left]:expression minus [right]:multdiv;
      </code>
    */
   public BigDecimal evaluate(PExpression expression)
   {
      if (expression instanceof ASingleExpression) {
         return evaluate(((ASingleExpression) expression).getSingle());
      }
      if (expression instanceof APlusExpression) {
         return evaluate((APlusExpression) expression);
      }
      if (expression instanceof AMinusExpression) {
         return evaluate((AMinusExpression) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public BigDecimal evaluate(APlusExpression expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      return left.add(right, STORED_PRECISION);
   }

   public BigDecimal evaluate(AMinusExpression expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      return left.subtract(right, STORED_PRECISION);
   }

   /**
    * Evaluates a multdiv:
    * <code>
       multdiv =
             {single} [single]:prefixop |
             {mult} [left]:multdiv mult [right]:prefixop |
             {div} [left]:multdiv div [right]:prefixop;
      </code>
    */
   public BigDecimal evaluate(PMultdiv expression)
   {
      if (expression instanceof ASingleMultdiv) {
         return evaluate(((ASingleMultdiv) expression).getSingle());
      }
      if (expression instanceof AMultMultdiv) {
         return evaluate((AMultMultdiv) expression);
      }
      if (expression instanceof ADivMultdiv) {
         return evaluate((ADivMultdiv) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public BigDecimal evaluate(AMultMultdiv expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      return left.multiply(right, STORED_PRECISION);
   }

   public BigDecimal evaluate(ADivMultdiv expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      return left.divide(right, STORED_PRECISION);
   }
   
   /**
    * <code>
        prefixop =
          {single} [single]:func |
          {negate} minus func;
      </code>
    */
   public BigDecimal evaluate(PPrefixop expression)
   {
      if (expression instanceof ASinglePrefixop) {
         return evaluate(((ASinglePrefixop) expression).getSingle());
      }
      if (expression instanceof ANegatePrefixop) {
         return evaluate((ANegatePrefixop) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public BigDecimal evaluate(ANegatePrefixop expression)
   {
      return evaluate(expression.getFunc()).negate();
   }

   /**
    * Evaluates a func:
    * <code>
          func =
                {single} [single]:multgroup |
                {func1} function_name multgroup |
                {func2} function_name lparen expression comma expression_list rparen |
                {func_no_args} function_noargs_name;
      </code>
    */
   public BigDecimal evaluate(PFunc expression)
   {
      if (expression instanceof ASingleFunc) {
         return evaluate(((ASingleFunc) expression).getSingle());
      }
      if (expression instanceof AFunc1Func) {
         return evaluate((AFunc1Func) expression);
      }
      if (expression instanceof AFunc2Func) {
         return evaluate((AFunc2Func) expression);
      }
      if (expression instanceof AFuncNoArgsFunc) {
         return evaluate((AFuncNoArgsFunc) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public BigDecimal evaluate(AFunc1Func expression)
   {
      List<BigDecimal> args = new ArrayList<BigDecimal>();
      args.add(evaluate(expression.getMultgroup()));
      
      return evaluateFunc(
            expression.getFunctionName().getText(),
            args,
            expression);
   }
   
   public BigDecimal evaluate(AFunc2Func expression)
   {
      BigDecimal firstArg = evaluate(expression.getExpression());
      List<BigDecimal> otherArgs = evaluate(expression.getExpressionList());
      
      otherArgs.add(0, firstArg);
      
      return evaluateFunc(
            expression.getFunctionName().getText(),
            otherArgs,
            expression);
   }
   
   public BigDecimal evaluate(AFuncNoArgsFunc expression)
   {
      String funcName = expression.getFunctionNoargsName().getText();
      if (funcName.equals("Ran#")) {
         return new BigDecimal(rnd.nextDouble(), STORED_PRECISION);
      }
      throw new CompileException(String.format(
            "Unrecognised function: '%s' at %s",
            funcName,
            Printer.nodeToString(expression)));
   }

   private BigDecimal evaluateFunc(
         String funcName,
         List<BigDecimal> args,
         Node location)
   {
      if (funcName.equals("e^")) {
         assertArgumentCount(args, 1, location);
         return new BigDecimal(Math.exp(args.get(0).doubleValue()), STORED_PRECISION);
      }
      if (funcName.equals("sqrt")) {
         assertArgumentCount(args, 1, location);
         return new BigDecimal(Math.sqrt(args.get(0).doubleValue()), STORED_PRECISION);
      }
      if (funcName.equals("sin ")) {
         assertArgumentCount(args, 1, location);
         return new BigDecimal(Math.sin(args.get(0).doubleValue()), STORED_PRECISION);
      }
      if (funcName.equals("cos ")) {
         assertArgumentCount(args, 1, location);
         return new BigDecimal(Math.cos(args.get(0).doubleValue()), STORED_PRECISION);
      }
      if (funcName.equals("tan ")) {
         assertArgumentCount(args, 1, location);
         return new BigDecimal(Math.tan(args.get(0).doubleValue()), STORED_PRECISION);
      }
      if (funcName.equals("Frac ")) {
         assertArgumentCount(args, 1, location);
         return args.get(0).remainder(BigDecimal.ONE, STORED_PRECISION);
      }
      if (funcName.equals("Int ")) {
         assertArgumentCount(args, 1, location);
         return args.get(0).divideToIntegralValue(BigDecimal.ONE, STORED_PRECISION);
      }
      if (funcName.equals("Abs ")) {
         assertArgumentCount(args, 1, location);
         return args.get(0).abs();
      }
      throw new CompileException(String.format(
            "Unrecognised function: '%s' at %s",
            funcName,
            Printer.nodeToString(location)));
   }

   /**
    * Evaluates a multgroup:
    * <code>
        multgroup =
              {single} [single]:frac |
              {mult} [left]:multgroup [right]:frac;
      </code>
    */
   public BigDecimal evaluate(PMultgroup expression)
   {
      if (expression instanceof ASingleMultgroup) {
         return evaluate(((ASingleMultgroup) expression).getSingle());
      }
      if (expression instanceof AMultMultgroup) {
         return evaluate((AMultMultgroup) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public BigDecimal evaluate(AMultMultgroup expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      return left.multiply(right, STORED_PRECISION);
   }
   

   /**
    * Evaluates a frac:
    * <code>
        frac =
              {single} [single]:P.pow |
              {pair} [numerator]:P.pow fraction_sep [denominator]:P.pow |
              {triple} [units]:P.pow [first_sep]:fraction_sep [numerator]:P.pow [second_sep]:fraction_sep [denominator]:P.pow;
      </code>

    * TODO: we could extend the notion of value to include exact fractions
    * which are auto-promoted to doubles, just as the Casio does.
    * (In fact, does the Casio also have exact ints which are promoted to 
    * doubles?)
    * ... but for the programs which we are trying to emulate for now we can
    * make do with doubles everywhere (which simplifies the emulator).         
    */
   public BigDecimal evaluate(PFrac expression)
   {
      if (expression instanceof ASingleFrac) {
         return evaluate(((ASingleFrac) expression).getSingle());
      }
      if (expression instanceof APairFrac) {
         return evaluate((APairFrac) expression);
      }
      if (expression instanceof ATripleFrac) {
         return evaluate((ATripleFrac) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public BigDecimal evaluate(APairFrac expression)
   {
      BigDecimal numerator = evaluate(expression.getNumerator());
      BigDecimal denominator = evaluate(expression.getDenominator());
      return numerator.divide(denominator, STORED_PRECISION);
   }
   
   public BigDecimal evaluate(ATripleFrac expression)
   {
      BigDecimal units = evaluate(expression.getUnits());
      BigDecimal numerator = evaluate(expression.getNumerator());
      BigDecimal denominator = evaluate(expression.getDenominator());
      return units.add(
            numerator.divide(denominator, STORED_PRECISION),
            STORED_PRECISION);
   }
   
   /**
    * <code>
        pow =
          {single} [single]:postfixop |
          {power} [left]:P.pow [op]:T.pow [right]:postfixop;
      </code>
    */
   public BigDecimal evaluate(PPow expression)
   {
      if (expression instanceof ASinglePow) {
         return evaluate(((ASinglePow) expression).getSingle());
      }
      if (expression instanceof APowerPow) {
         return evaluate((APowerPow) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public BigDecimal evaluate(APowerPow expression)
   {
      BigDecimal left = evaluate(expression.getLeft());
      BigDecimal right = evaluate(expression.getRight());
      try {
         return left.pow(right.intValueExact(), STORED_PRECISION);
      } catch (ArithmeticException e) {
         return new BigDecimal(
               Math.pow(evaluate(expression.getLeft()).doubleValue(),
                     evaluate(expression.getRight()).doubleValue()),
               STORED_PRECISION);
      }
   }

   /**
    * Evaluates a postfixop:
    * <code>
    postfixop =
          {single} [single]:atom |
          {factorial} postfixop bang;
      </code>
    */
   public BigDecimal evaluate(PPostfixop expression)
   {
      if (expression instanceof ASinglePostfixop) {
         return evaluate(((ASinglePostfixop) expression).getSingle());
      }
      if (expression instanceof AFactorialPostfixop) {
         return evaluate((AFactorialPostfixop) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public BigDecimal evaluate(AFactorialPostfixop expression)
   {
      BigDecimal value = evaluate(expression.getPostfixop());
      int valInt;
      try {
         valInt = value.intValueExact();
      } catch (ArithmeticException e) {
         throw new RuntimeException(String.format(
               "Cannot compute factorial of non-integer value '%s' at %s",
               value,
               Printer.nodeToString(expression)));
      }
      return new BigDecimal(ArithmeticUtils.factorial(valInt), STORED_PRECISION);
   }
   
   /**
    * Evaluates an atom:
    * <code>
    atom =
           {var} variable_name |
           {number} number_literal |
           {input} input_prompt |
           {expression} lparen expression rparen;
      </code>
    */
   public BigDecimal evaluate(PAtom expression)
   {
      if (expression instanceof AVarAtom) {
         return evaluate((AVarAtom) expression);
      }
      if (expression instanceof ANumberAtom) {
         return evaluate((ANumberAtom) expression);
      }
      if (expression instanceof AInputAtom) {
         return evaluate((AInputAtom) expression);
      }
      if (expression instanceof AExpressionAtom) {
         return evaluate(((AExpressionAtom) expression).getExpression());
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public BigDecimal evaluate(AVarAtom expression)
   {
      return context.getVariableValue(
            expression.getVariableName().getText(),
            expression);
   }

   public BigDecimal evaluate(ANumberAtom expression)
   {
      return evaluate(expression.getNumberLiteral());
   }
   
   public BigDecimal evaluate(TNumberLiteral number)
   {
      try {
         return new BigDecimal(number.getText(), STORED_PRECISION);
      } catch (Exception e) {
         throw new CompileException(
               e.getMessage() +
               " at " +
               Printer.nodeToString(number));
      }
   }
   
   public BigDecimal evaluate(AInputAtom expression)
   {
      return userInterface.readValue();
   }
   
   public List<BigDecimal> evaluate(PExpressionList list)
   {
      List<BigDecimal> acc = new ArrayList<BigDecimal>();
      
      while (true) {
         if (list instanceof ASingleExpressionList) {
            BigDecimal value = evaluate(((ASingleExpressionList)list).getExpression());
            acc.add(value);
            return acc;
         } else if (list instanceof ASequenceExpressionList) {
            BigDecimal value = evaluate(((ASequenceExpressionList)list).getExpression());
            acc.add(value);
            list = ((ASequenceExpressionList) list).getExpressionList();
         } else {
            throw new CompileException(String.format(
                  "Unexpected type: %s at %s",
                  list.getClass(),
                  Printer.nodeToString(list)));
         }
      }
   }
   
   public List<BigDecimal> evaluate(PAtomList list)
   {
      List<BigDecimal> acc = new ArrayList<BigDecimal>();
      
      while (true) {
         if (list instanceof ASingleAtomList) {
            BigDecimal value = evaluate(((ASingleAtomList)list).getAtom());
            acc.add(value);
            return acc;
         } else if (list instanceof ASequenceAtomList) {
            BigDecimal value = evaluate(((ASequenceAtomList)list).getAtom());
            acc.add(value);
            list = ((ASequenceAtomList) list).getAtomList();
         } else {
            throw new CompileException(String.format(
                  "Unexpected type: %s at %s",
                  list.getClass(),
                  Printer.nodeToString(list)));
         }
      }
   }

   public void assertArgumentCount(List<BigDecimal> args, int expectedCount, Node location)
   {
      if (args.size() != expectedCount) {
         throw new CompileException(String.format(
               "Wrong number of args to function, expected %s found %s at %s",
               expectedCount,
               args.size(),
               Printer.nodeToString(location)));
      }
   }
}
