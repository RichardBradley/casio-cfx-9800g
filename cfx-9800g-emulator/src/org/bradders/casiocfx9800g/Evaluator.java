package org.bradders.casiocfx9800g;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.bradders.casiocfx9800g.node.AAtomMultgroup;
import org.bradders.casiocfx9800g.node.ADivTerm;
import org.bradders.casiocfx9800g.node.AExpressionAtom;
import org.bradders.casiocfx9800g.node.AFactorTerm;
import org.bradders.casiocfx9800g.node.AFactorialAtom;
import org.bradders.casiocfx9800g.node.AFuncAtom;
import org.bradders.casiocfx9800g.node.AInputAtom;
import org.bradders.casiocfx9800g.node.AMinusExpression;
import org.bradders.casiocfx9800g.node.AMultTerm;
import org.bradders.casiocfx9800g.node.AMultgroupFactor;
import org.bradders.casiocfx9800g.node.ANegateExpression;
import org.bradders.casiocfx9800g.node.ANumberAtom;
import org.bradders.casiocfx9800g.node.APlusExpression;
import org.bradders.casiocfx9800g.node.APowerMultgroup;
import org.bradders.casiocfx9800g.node.ASequenceArgumentList;
import org.bradders.casiocfx9800g.node.ASingleArgumentList;
import org.bradders.casiocfx9800g.node.ASingleFactor;
import org.bradders.casiocfx9800g.node.ATermExpression;
import org.bradders.casiocfx9800g.node.AVarAtom;
import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.PArgumentList;
import org.bradders.casiocfx9800g.node.PAtom;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.PFactor;
import org.bradders.casiocfx9800g.node.PMultgroup;
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

   public double evaluate(PMultgroup expression)
   {
      if (expression instanceof AAtomMultgroup) {
         return evaluate(((AAtomMultgroup) expression).getAtom());
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
            evaluate(expression.getAtom()));
   }

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
      if (expression instanceof AFuncAtom) {
         return evaluate((AFuncAtom) expression);
      }
      if (expression instanceof AFactorialAtom) {
         return evaluate((AFactorialAtom) expression);
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

   public double evaluate(AFuncAtom expression)
   {
      List<Double> args = evaluate(expression.getArgumentList());
      String funcName = expression.getFunctionName().getText();
      if (funcName.equals("e^")) {
         assertArgumentCount(args, 1, expression);
         return Math.exp(args.get(0));
      }
      throw new CompileException(String.format(
            "Unrecognised function: '%s' at %s",
            funcName,
            Printer.nodeToString(expression)));
   }
   
   private List<Double> evaluate(PArgumentList argumentList)
   {
      List<Double> acc = new ArrayList<Double>();
      
      while (true) {
         if (argumentList instanceof ASingleArgumentList) {
            double value = evaluate(((ASingleArgumentList)argumentList).getExpression());
            acc.add(value);
            return acc;
         } else if (argumentList instanceof ASequenceArgumentList) {
            double value = evaluate(((ASingleArgumentList)argumentList).getExpression());
            acc.add(value);
            argumentList = ((ASequenceArgumentList) argumentList).getArgumentList();
         } else {
            throw new CompileException(String.format(
                  "Unexpected type: %s at %s",
                  argumentList.getClass(),
                  Printer.nodeToString(argumentList)));
         }
      }
   }

   private void assertArgumentCount(List<Double> args, int expectedCount, Node location)
   {
      if (args.size() != expectedCount) {
         throw new CompileException(String.format(
               "Wrong number of args to function, expected %s found %s at %s",
               expectedCount,
               args.size(),
               Printer.nodeToString(location)));
      }
   }

   public double evaluate(AFactorialAtom expression)
   {
      double value = evaluate(expression.getAtom());
      int valInt = (int)value;
      if (valInt != value) {
         throw new RuntimeException(String.format(
               "Cannot compute factorial of non-integer value '%s' at %s",
               value,
               Printer.nodeToString(expression)));
      }
      return CombinatoricsUtils.factorialDouble(valInt);
   }
}
