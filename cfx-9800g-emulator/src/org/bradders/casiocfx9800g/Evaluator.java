package org.bradders.casiocfx9800g;

import org.bradders.casiocfx9800g.node.AAtomFactor;
import org.bradders.casiocfx9800g.node.ADivTerm;
import org.bradders.casiocfx9800g.node.AExpressionAtom;
import org.bradders.casiocfx9800g.node.AFactorTerm;
import org.bradders.casiocfx9800g.node.AFactorialAtom;
import org.bradders.casiocfx9800g.node.AFuncAtom;
import org.bradders.casiocfx9800g.node.AInputAtom;
import org.bradders.casiocfx9800g.node.AMinusExpression;
import org.bradders.casiocfx9800g.node.AMultTerm;
import org.bradders.casiocfx9800g.node.AMultadjTerm;
import org.bradders.casiocfx9800g.node.ANegateExpression;
import org.bradders.casiocfx9800g.node.ANumberAtom;
import org.bradders.casiocfx9800g.node.APlusExpression;
import org.bradders.casiocfx9800g.node.APowerFactor;
import org.bradders.casiocfx9800g.node.ATermExpression;
import org.bradders.casiocfx9800g.node.AVarAtom;
import org.bradders.casiocfx9800g.node.Node;
import org.bradders.casiocfx9800g.node.PAtom;
import org.bradders.casiocfx9800g.node.PExpression;
import org.bradders.casiocfx9800g.node.PFactor;
import org.bradders.casiocfx9800g.node.PTerm;
import org.bradders.casiocfx9800g.node.TNumberLiteral;
import org.bradders.casiocfx9800g.util.Printer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class evaluates nodes in the AST.
 * 
 * It would be nicer if this were OO, but SableCC enforces a visitor pattern
 */
public class Evaluator
{
   private final RuntimeContext context;
   

   public Evaluator(RuntimeContext context)
   {
      this.context = context;
   }

   public static int getInt(TNumberLiteral numberLiteral, Node context)
   {
      try
      {
         return Integer.parseInt(numberLiteral.getText());
      }
      catch (Exception e)
      {
         throw new CompileException(String.format(
               "Expecting an integer, found '%s' at %s",
               numberLiteral,
               Printer.nodeToString(context)));
      }
   }

   public double evaluate(PExpression expression)
   {
      if (expression instanceof ATermExpression) {
         return evaluate(((ATermExpression)expression).getTerm());
      }
      if (expression instanceof APlusExpression) {
         return evaluate((APlusExpression)expression);
      }
      if (expression instanceof AMinusExpression) {
         return evaluate((AMinusExpression)expression);
      }
      if (expression instanceof ANegateExpression) {
         return evaluate((ANegateExpression)expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public double evaluate(APlusExpression expression)
   {
      return evaluate(expression.getTerm()) + evaluate(expression.getExpression());
   }

   public double evaluate(AMinusExpression expression)
   {
      return evaluate(expression.getTerm()) - evaluate(expression.getExpression());
   }

   public double evaluate(ANegateExpression expression)
   {
      return - evaluate(expression.getExpression());
   }
   
   public double evaluate(PTerm expression)
   {
      if (expression instanceof AFactorTerm) {
         return evaluate(((AFactorTerm)expression).getFactor());
      }
      if (expression instanceof AMultTerm) {
         return evaluate((AMultTerm)expression);
      }
      if (expression instanceof ADivTerm) {
         return evaluate((ADivTerm)expression);
      }
      if (expression instanceof AMultadjTerm) {
         return evaluate((AMultadjTerm)expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public double evaluate(AMultTerm expression)
   {
      return evaluate(expression.getFactor()) * evaluate(expression.getTerm());
   }

   public double evaluate(ADivTerm expression)
   {
      return evaluate(expression.getFactor()) - evaluate(expression.getTerm());
   }

   public double evaluate(AMultadjTerm expression)
   {
      return evaluate(expression.getFactor()) * evaluate(expression.getTerm());
   }
   
   public double evaluate(PFactor expression)
   {
      if (expression instanceof AAtomFactor) {
         return evaluate(((AAtomFactor)expression).getAtom());
      }
      if (expression instanceof APowerFactor) {
         return evaluate((APowerFactor)expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }
   
   public double evaluate(APowerFactor expression)
   {
      return Math.pow(
            evaluate(expression.getAtom()),
            evaluate(expression.getFactor()));
   }
   
   public double evaluate(PAtom expression)
   {
      if (expression instanceof AVarAtom) {
         return evaluate((AVarAtom)expression);
      }
      if (expression instanceof ANumberAtom) {
         return evaluate((ANumberAtom)expression);
      }
      if (expression instanceof AInputAtom) {
         return evaluate(((AExpressionAtom)expression).getExpression());
      }
      if (expression instanceof AExpressionAtom) {
         return evaluate(((AExpressionAtom)expression).getExpression());
      }
      if (expression instanceof AFuncAtom) {
         return evaluate((AFuncAtom)expression);
      }
      if (expression instanceof AFactorialAtom) {
         return evaluate((AFactorialAtom)expression);
      }
      throw new CompileException(String.format(
            "Unexpected type: %s at %s",
            expression.getClass(),
            Printer.nodeToString(expression)));
   }

   public double evaluate(AVarAtom expression)
   {
      return context.getVariableValue(expression.getVariableName().getText(), expression);
   }

   public double evaluate(ANumberAtom expression)
   {
      try {
         return Double.parseDouble(expression.getNumberLiteral().getText());
      } catch (Exception e) {
         throw new CompileException(e.getMessage() + " at " + Printer.nodeToString(expression));
      }
   }

   public double evaluate(AInputAtom expression)
   {
      throw new RuntimeException("qq TODO");
   }
   
   public double evaluate(AFuncAtom expression)
   {
      throw new RuntimeException("qq TODO");
   }
   
   public double evaluate(AFactorialAtom expression)
   {
      throw new RuntimeException("qq TODO");
   }
}
