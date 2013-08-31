package org.bradders.casiocfx9800g;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.bradders.casiocfx9800g.node.AAtomFunc;
import org.bradders.casiocfx9800g.node.ADivTerm;
import org.bradders.casiocfx9800g.node.AExpressionAtom;
import org.bradders.casiocfx9800g.node.AFactorTerm;
import org.bradders.casiocfx9800g.node.AFactorialPostfixop;
import org.bradders.casiocfx9800g.node.AFunc1Func;
import org.bradders.casiocfx9800g.node.AFunc2Func;
import org.bradders.casiocfx9800g.node.AFuncPostfixop;
import org.bradders.casiocfx9800g.node.AInputAtom;
import org.bradders.casiocfx9800g.node.AMinusExpression;
import org.bradders.casiocfx9800g.node.AMultTerm;
import org.bradders.casiocfx9800g.node.AMultgroupFactor;
import org.bradders.casiocfx9800g.node.ANegateExpression;
import org.bradders.casiocfx9800g.node.ANumberAtom;
import org.bradders.casiocfx9800g.node.APlusExpression;
import org.bradders.casiocfx9800g.node.APostfixopMultgroup;
import org.bradders.casiocfx9800g.node.APowerMultgroup;
import org.bradders.casiocfx9800g.node.ASequenceAtomList;
import org.bradders.casiocfx9800g.node.ASequenceExpressionList;
import org.bradders.casiocfx9800g.node.ASingleAtomList;
import org.bradders.casiocfx9800g.node.ASingleExpressionList;
import org.bradders.casiocfx9800g.node.ASingleFactor;
import org.bradders.casiocfx9800g.node.ATermExpression;
import org.bradders.casiocfx9800g.node.AVarAtom;
import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.PAtom;
import org.bradders.casiocfx9800g.node.PAtomList;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.PExpressionList;
import org.bradders.casiocfx9800g.node.PFactor;
import org.bradders.casiocfx9800g.node.PFunc;
import org.bradders.casiocfx9800g.node.PMultgroup;
import org.bradders.casiocfx9800g.node.PPostfixop;
import org.bradders.casiocfx9800g.node.PTerm;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.node.TQuotedText;
import org.bradders.casiocfx9800g.ui.UserInterface;
import org.bradders.casiocfx9800g.util.Printer;

/**
 * This class evaluates nodes in the AST.
 * 
 * It would be nicer if this were OO, but SableCC enforces a visitor pattern
 */
public class Evaluator
{
   private final RuntimeContext context;
   private final UserInterface userInterface;

   public Evaluator(RuntimeContext context, UserInterface userInterface)
   {
      this.context = context;
      this.userInterface = userInterface;
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
                  {term} term |
                  {plus} term plus expression |
                  {minus} term minus expression |
                  {negate} minus expression;
      </code>
    */
   public double evaluate(PExpression expression)
   {
      if (expression instanceof ATermExpression) {
         return evaluate(((ATermExpression) expression).getTerm());
      }
      if (expression instanceof APlusExpression) {
         return evaluate((APlusExpression) expression);
      }
      if (expression instanceof AMinusExpression) {
         return evaluate((AMinusExpression) expression);
      }
      if (expression instanceof ANegateExpression) {
         return evaluate((ANegateExpression) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public double evaluate(APlusExpression expression)
   {
      return evaluate(expression.getTerm())
            + evaluate(expression.getExpression());
   }

   public double evaluate(AMinusExpression expression)
   {
      return evaluate(expression.getTerm())
            - evaluate(expression.getExpression());
   }

   public double evaluate(ANegateExpression expression)
   {
      return -evaluate(expression.getExpression());
   }

   /**
    * Evaluates a term:
    * <code>
          term =
                {factor} factor |
                {mult} term mult factor |
                {div} term div factor;
      </code>
    */
   public double evaluate(PTerm expression)
   {
      if (expression instanceof AFactorTerm) {
         return evaluate(((AFactorTerm) expression).getFactor());
      }
      if (expression instanceof AMultTerm) {
         return evaluate((AMultTerm) expression);
      }
      if (expression instanceof ADivTerm) {
         return evaluate((ADivTerm) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public double evaluate(AMultTerm expression)
   {
      return evaluate(expression.getTerm()) * evaluate(expression.getFactor());
   }

   public double evaluate(ADivTerm expression)
   {
      return evaluate(expression.getTerm()) / evaluate(expression.getFactor());
   }

   /**
    * Evaluates a factor:
    * <code>
        factor =
              {single} multgroup |
              {multgroup} factor multgroup;
      </code>
    */
   public double evaluate(PFactor expression)
   {
      if (expression instanceof ASingleFactor) {
         return evaluate(((ASingleFactor) expression).getMultgroup());
      }
      if (expression instanceof AMultgroupFactor) {
         return evaluate((AMultgroupFactor) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public double evaluate(AMultgroupFactor expression)
   {
      return evaluate(expression.getFactor())
            * evaluate(expression.getMultgroup());
   }

   /**
    * Evaluates a multgroup:
    * <code>
        multgroup =
          {postfixop} postfixop |
          {power} multgroup pow postfixop;
      </code>
    */
   public double evaluate(PMultgroup expression)
   {
      if (expression instanceof APostfixopMultgroup) {
         return evaluate(((APostfixopMultgroup) expression).getPostfixop());
      }
      if (expression instanceof APowerMultgroup) {
         return evaluate((APowerMultgroup) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public double evaluate(APowerMultgroup expression)
   {
      return Math.pow(
            evaluate(expression.getMultgroup()),
            evaluate(expression.getPostfixop()));
   }

   /**
    * Evaluates a postfixop:
    * <code>
    postfixop =
          {func} func |
          {factorial} atom bang;
      </code>
    */
   public double evaluate(PPostfixop expression)
   {
      if (expression instanceof AFuncPostfixop) {
         return evaluate(((AFuncPostfixop) expression).getFunc());
      }
      if (expression instanceof AFactorialPostfixop) {
         return evaluate((AFactorialPostfixop) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public double evaluate(AFactorialPostfixop expression)
   {
      double value = evaluate(expression.getAtom());
      int valInt = (int)value;
      if (valInt != value) {
         throw new RuntimeException(String.format(
               "Cannot compute factorial of non-integer value '%s' at %s",
               value,
               Printer.nodeToString(expression)));
      }
      return ArithmeticUtils.factorialDouble(valInt);
   }

   /**
    * Evaluates a func:
    * <code>
    func =
         {atom} atom |
         {func1} function_name atom |
         {func2} function_name lparen expression comma expression_list rparen;
      </code>
    */
   public double evaluate(PFunc expression)
   {
      if (expression instanceof AAtomFunc) {
         return evaluate(((AAtomFunc) expression).getAtom());
      }
      if (expression instanceof AFunc1Func) {
         return evaluate((AFunc1Func) expression);
      }
      if (expression instanceof AFunc2Func) {
         return evaluate((AFunc2Func) expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public double evaluate(AFunc1Func expression)
   {
      List<Double> args = new ArrayList<Double>();
      args.add(evaluate(expression.getAtom()));
      
      return evaluateFunc(
            expression.getFunctionName().getText(),
            args,
            expression);
   }
   
   public double evaluate(AFunc2Func expression)
   {
      double firstArg = evaluate(expression.getExpression());
      List<Double> otherArgs = evaluate(expression.getExpressionList());
      
      otherArgs.add(0, firstArg);
      
      return evaluateFunc(
            expression.getFunctionName().getText(),
            otherArgs,
            expression);
   }

   private double evaluateFunc(
         String funcName,
         List<Double> args,
         Node location)
   {
      if (funcName.equals("e^")) {
         assertArgumentCount(args, 1, location);
         return Math.exp(args.get(0));
      }
      if (funcName.equals("sqrt")) {
         assertArgumentCount(args, 1, location);
         return Math.sqrt(args.get(0));
      }
      if (funcName.equals("Frac ")) {
         assertArgumentCount(args, 1, location);
         double arg = args.get(0);
         if (arg < 0) {
            return arg - Math.ceil(arg);
         }
         return arg - Math.floor(arg);
      }
      if (funcName.equals("Int ")) {
         assertArgumentCount(args, 1, location);
         double arg = args.get(0);
         if (arg < 0) {
            return Math.ceil(arg);
         }
         return Math.floor(arg);
      }
      if (funcName.equals("Abs ")) {
         assertArgumentCount(args, 1, location);
         return Math.abs(args.get(0));
      }
      throw new CompileException(String.format(
            "Unrecognised function: '%s' at %s",
            funcName,
            Printer.nodeToString(location)));
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
   public double evaluate(PAtom expression)
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

   public double evaluate(AVarAtom expression)
   {
      return context.getVariableValue(
            expression.getVariableName().getText(),
            expression);
   }

   public double evaluate(ANumberAtom expression)
   {
      try {
         return Double.parseDouble(expression.getNumberLiteral().getText());
      } catch (Exception e) {
         throw new CompileException(
               e.getMessage() +
               " at " +
               Printer.nodeToString(expression));
      }
   }

   public double evaluate(AInputAtom expression)
   {
      return userInterface.readValue();
   }

   private List<Double> evaluate(PExpressionList list)
   {
      List<Double> acc = new ArrayList<Double>();
      
      while (true) {
         if (list instanceof ASingleExpressionList) {
            double value = evaluate(((ASingleExpressionList)list).getExpression());
            acc.add(value);
            return acc;
         } else if (list instanceof ASequenceExpressionList) {
            double value = evaluate(((ASequenceExpressionList)list).getExpression());
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
   
   public List<Double> evaluate(PAtomList list)
   {
      List<Double> acc = new ArrayList<Double>();
      
      while (true) {
         if (list instanceof ASingleAtomList) {
            double value = evaluate(((ASingleAtomList)list).getAtom());
            acc.add(value);
            return acc;
         } else if (list instanceof ASequenceAtomList) {
            double value = evaluate(((ASequenceAtomList)list).getAtom());
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

   public void assertArgumentCount(List<Double> args, int expectedCount, Node location)
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
