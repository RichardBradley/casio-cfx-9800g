/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.analysis;

import java.util.*;
import org.bradders.casiocfx9800g.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgram().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inASingleProgram(ASingleProgram node)
    {
        defaultIn(node);
    }

    public void outASingleProgram(ASingleProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleProgram(ASingleProgram node)
    {
        inASingleProgram(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getStatementSeparator() != null)
        {
            node.getStatementSeparator().apply(this);
        }
        outASingleProgram(node);
    }

    public void inASequenceProgram(ASequenceProgram node)
    {
        defaultIn(node);
    }

    public void outASequenceProgram(ASequenceProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASequenceProgram(ASequenceProgram node)
    {
        inASequenceProgram(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getStatementSeparator() != null)
        {
            node.getStatementSeparator().apply(this);
        }
        if(node.getProgram() != null)
        {
            node.getProgram().apply(this);
        }
        outASequenceProgram(node);
    }

    public void inAPrinttextStatement(APrinttextStatement node)
    {
        defaultIn(node);
    }

    public void outAPrinttextStatement(APrinttextStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrinttextStatement(APrinttextStatement node)
    {
        inAPrinttextStatement(node);
        if(node.getQuotedText() != null)
        {
            node.getQuotedText().apply(this);
        }
        outAPrinttextStatement(node);
    }

    public void inAAssignStatement(AAssignStatement node)
    {
        defaultIn(node);
    }

    public void outAAssignStatement(AAssignStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignStatement(AAssignStatement node)
    {
        inAAssignStatement(node);
        if(node.getQuotedText() != null)
        {
            node.getQuotedText().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getAssignArrow() != null)
        {
            node.getAssignArrow().apply(this);
        }
        if(node.getVariableName() != null)
        {
            node.getVariableName().apply(this);
        }
        if(node.getPrintResult() != null)
        {
            node.getPrintResult().apply(this);
        }
        outAAssignStatement(node);
    }

    public void inAPrintvalStatement(APrintvalStatement node)
    {
        defaultIn(node);
    }

    public void outAPrintvalStatement(APrintvalStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrintvalStatement(APrintvalStatement node)
    {
        inAPrintvalStatement(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getPrintResult() != null)
        {
            node.getPrintResult().apply(this);
        }
        outAPrintvalStatement(node);
    }

    public void inALabelStatement(ALabelStatement node)
    {
        defaultIn(node);
    }

    public void outALabelStatement(ALabelStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALabelStatement(ALabelStatement node)
    {
        inALabelStatement(node);
        if(node.getLabel() != null)
        {
            node.getLabel().apply(this);
        }
        if(node.getNumberLiteral() != null)
        {
            node.getNumberLiteral().apply(this);
        }
        outALabelStatement(node);
    }

    public void inAGotoStatement(AGotoStatement node)
    {
        defaultIn(node);
    }

    public void outAGotoStatement(AGotoStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGotoStatement(AGotoStatement node)
    {
        inAGotoStatement(node);
        if(node.getGoto() != null)
        {
            node.getGoto().apply(this);
        }
        if(node.getNumberLiteral() != null)
        {
            node.getNumberLiteral().apply(this);
        }
        outAGotoStatement(node);
    }

    public void inASubNoargsStatement(ASubNoargsStatement node)
    {
        defaultIn(node);
    }

    public void outASubNoargsStatement(ASubNoargsStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubNoargsStatement(ASubNoargsStatement node)
    {
        inASubNoargsStatement(node);
        if(node.getSubNoargsName() != null)
        {
            node.getSubNoargsName().apply(this);
        }
        outASubNoargsStatement(node);
    }

    public void inASubArgsStatement(ASubArgsStatement node)
    {
        defaultIn(node);
    }

    public void outASubArgsStatement(ASubArgsStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubArgsStatement(ASubArgsStatement node)
    {
        inASubArgsStatement(node);
        if(node.getSubArgsName() != null)
        {
            node.getSubArgsName().apply(this);
        }
        if(node.getSpace() != null)
        {
            node.getSpace().apply(this);
        }
        if(node.getAtomList() != null)
        {
            node.getAtomList().apply(this);
        }
        outASubArgsStatement(node);
    }

    public void inAIfStatement(AIfStatement node)
    {
        defaultIn(node);
    }

    public void outAIfStatement(AIfStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfStatement(AIfStatement node)
    {
        inAIfStatement(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getComparisonOp() != null)
        {
            node.getComparisonOp().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getThenArrow() != null)
        {
            node.getThenArrow().apply(this);
        }
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outAIfStatement(node);
    }

    public void inACountJumpStatement(ACountJumpStatement node)
    {
        defaultIn(node);
    }

    public void outACountJumpStatement(ACountJumpStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACountJumpStatement(ACountJumpStatement node)
    {
        inACountJumpStatement(node);
        if(node.getCountJumpOp() != null)
        {
            node.getCountJumpOp().apply(this);
        }
        if(node.getSpace() != null)
        {
            node.getSpace().apply(this);
        }
        if(node.getVariableName() != null)
        {
            node.getVariableName().apply(this);
        }
        if(node.getStatementSeparator() != null)
        {
            node.getStatementSeparator().apply(this);
        }
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outACountJumpStatement(node);
    }

    public void inASubStatement(ASubStatement node)
    {
        defaultIn(node);
    }

    public void outASubStatement(ASubStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubStatement(ASubStatement node)
    {
        inASubStatement(node);
        if(node.getProg() != null)
        {
            node.getProg().apply(this);
        }
        if(node.getSpace() != null)
        {
            node.getSpace().apply(this);
        }
        if(node.getQuotedText() != null)
        {
            node.getQuotedText().apply(this);
        }
        outASubStatement(node);
    }

    public void inAGraphStatement(AGraphStatement node)
    {
        defaultIn(node);
    }

    public void outAGraphStatement(AGraphStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGraphStatement(AGraphStatement node)
    {
        inAGraphStatement(node);
        if(node.getGraph() != null)
        {
            node.getGraph().apply(this);
        }
        if(node.getSpace() != null)
        {
            node.getSpace().apply(this);
        }
        if(node.getVariableName() != null)
        {
            node.getVariableName().apply(this);
        }
        if(node.getComparisonOp() != null)
        {
            node.getComparisonOp().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAGraphStatement(node);
    }

    public void inASingleExpression(ASingleExpression node)
    {
        defaultIn(node);
    }

    public void outASingleExpression(ASingleExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleExpression(ASingleExpression node)
    {
        inASingleExpression(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASingleExpression(node);
    }

    public void inAPlusExpression(APlusExpression node)
    {
        defaultIn(node);
    }

    public void outAPlusExpression(APlusExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpression(APlusExpression node)
    {
        inAPlusExpression(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAPlusExpression(node);
    }

    public void inAMinusExpression(AMinusExpression node)
    {
        defaultIn(node);
    }

    public void outAMinusExpression(AMinusExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExpression(AMinusExpression node)
    {
        inAMinusExpression(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMinusExpression(node);
    }

    public void inASingleMultdiv(ASingleMultdiv node)
    {
        defaultIn(node);
    }

    public void outASingleMultdiv(ASingleMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleMultdiv(ASingleMultdiv node)
    {
        inASingleMultdiv(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASingleMultdiv(node);
    }

    public void inAMultMultdiv(AMultMultdiv node)
    {
        defaultIn(node);
    }

    public void outAMultMultdiv(AMultMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultMultdiv(AMultMultdiv node)
    {
        inAMultMultdiv(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMultMultdiv(node);
    }

    public void inADivMultdiv(ADivMultdiv node)
    {
        defaultIn(node);
    }

    public void outADivMultdiv(ADivMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivMultdiv(ADivMultdiv node)
    {
        inADivMultdiv(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outADivMultdiv(node);
    }

    public void inASinglePrefixop(ASinglePrefixop node)
    {
        defaultIn(node);
    }

    public void outASinglePrefixop(ASinglePrefixop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASinglePrefixop(ASinglePrefixop node)
    {
        inASinglePrefixop(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASinglePrefixop(node);
    }

    public void inANegatePrefixop(ANegatePrefixop node)
    {
        defaultIn(node);
    }

    public void outANegatePrefixop(ANegatePrefixop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANegatePrefixop(ANegatePrefixop node)
    {
        inANegatePrefixop(node);
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getFunc() != null)
        {
            node.getFunc().apply(this);
        }
        outANegatePrefixop(node);
    }

    public void inASingleFunc(ASingleFunc node)
    {
        defaultIn(node);
    }

    public void outASingleFunc(ASingleFunc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleFunc(ASingleFunc node)
    {
        inASingleFunc(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASingleFunc(node);
    }

    public void inAFunc1Func(AFunc1Func node)
    {
        defaultIn(node);
    }

    public void outAFunc1Func(AFunc1Func node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunc1Func(AFunc1Func node)
    {
        inAFunc1Func(node);
        if(node.getFunctionName() != null)
        {
            node.getFunctionName().apply(this);
        }
        if(node.getMultgroup() != null)
        {
            node.getMultgroup().apply(this);
        }
        outAFunc1Func(node);
    }

    public void inAFunc2Func(AFunc2Func node)
    {
        defaultIn(node);
    }

    public void outAFunc2Func(AFunc2Func node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunc2Func(AFunc2Func node)
    {
        inAFunc2Func(node);
        if(node.getFunctionName() != null)
        {
            node.getFunctionName().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getExpressionList() != null)
        {
            node.getExpressionList().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        outAFunc2Func(node);
    }

    public void inAFuncNoArgsFunc(AFuncNoArgsFunc node)
    {
        defaultIn(node);
    }

    public void outAFuncNoArgsFunc(AFuncNoArgsFunc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncNoArgsFunc(AFuncNoArgsFunc node)
    {
        inAFuncNoArgsFunc(node);
        if(node.getFunctionNoargsName() != null)
        {
            node.getFunctionNoargsName().apply(this);
        }
        outAFuncNoArgsFunc(node);
    }

    public void inASingleMultgroup(ASingleMultgroup node)
    {
        defaultIn(node);
    }

    public void outASingleMultgroup(ASingleMultgroup node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleMultgroup(ASingleMultgroup node)
    {
        inASingleMultgroup(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASingleMultgroup(node);
    }

    public void inAMultMultgroup(AMultMultgroup node)
    {
        defaultIn(node);
    }

    public void outAMultMultgroup(AMultMultgroup node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultMultgroup(AMultMultgroup node)
    {
        inAMultMultgroup(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMultMultgroup(node);
    }

    public void inASingleFrac(ASingleFrac node)
    {
        defaultIn(node);
    }

    public void outASingleFrac(ASingleFrac node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleFrac(ASingleFrac node)
    {
        inASingleFrac(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASingleFrac(node);
    }

    public void inAPairFrac(APairFrac node)
    {
        defaultIn(node);
    }

    public void outAPairFrac(APairFrac node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPairFrac(APairFrac node)
    {
        inAPairFrac(node);
        if(node.getNumerator() != null)
        {
            node.getNumerator().apply(this);
        }
        if(node.getFractionSep() != null)
        {
            node.getFractionSep().apply(this);
        }
        if(node.getDenominator() != null)
        {
            node.getDenominator().apply(this);
        }
        outAPairFrac(node);
    }

    public void inATripleFrac(ATripleFrac node)
    {
        defaultIn(node);
    }

    public void outATripleFrac(ATripleFrac node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATripleFrac(ATripleFrac node)
    {
        inATripleFrac(node);
        if(node.getUnits() != null)
        {
            node.getUnits().apply(this);
        }
        if(node.getFirstSep() != null)
        {
            node.getFirstSep().apply(this);
        }
        if(node.getNumerator() != null)
        {
            node.getNumerator().apply(this);
        }
        if(node.getSecondSep() != null)
        {
            node.getSecondSep().apply(this);
        }
        if(node.getDenominator() != null)
        {
            node.getDenominator().apply(this);
        }
        outATripleFrac(node);
    }

    public void inASinglePow(ASinglePow node)
    {
        defaultIn(node);
    }

    public void outASinglePow(ASinglePow node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASinglePow(ASinglePow node)
    {
        inASinglePow(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASinglePow(node);
    }

    public void inAPowerPow(APowerPow node)
    {
        defaultIn(node);
    }

    public void outAPowerPow(APowerPow node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPowerPow(APowerPow node)
    {
        inAPowerPow(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getOp() != null)
        {
            node.getOp().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAPowerPow(node);
    }

    public void inASinglePostfixop(ASinglePostfixop node)
    {
        defaultIn(node);
    }

    public void outASinglePostfixop(ASinglePostfixop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASinglePostfixop(ASinglePostfixop node)
    {
        inASinglePostfixop(node);
        if(node.getSingle() != null)
        {
            node.getSingle().apply(this);
        }
        outASinglePostfixop(node);
    }

    public void inAFactorialPostfixop(AFactorialPostfixop node)
    {
        defaultIn(node);
    }

    public void outAFactorialPostfixop(AFactorialPostfixop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFactorialPostfixop(AFactorialPostfixop node)
    {
        inAFactorialPostfixop(node);
        if(node.getPostfixop() != null)
        {
            node.getPostfixop().apply(this);
        }
        if(node.getBang() != null)
        {
            node.getBang().apply(this);
        }
        outAFactorialPostfixop(node);
    }

    public void inAVarAtom(AVarAtom node)
    {
        defaultIn(node);
    }

    public void outAVarAtom(AVarAtom node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarAtom(AVarAtom node)
    {
        inAVarAtom(node);
        if(node.getVariableName() != null)
        {
            node.getVariableName().apply(this);
        }
        outAVarAtom(node);
    }

    public void inANumberAtom(ANumberAtom node)
    {
        defaultIn(node);
    }

    public void outANumberAtom(ANumberAtom node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberAtom(ANumberAtom node)
    {
        inANumberAtom(node);
        if(node.getNumberLiteral() != null)
        {
            node.getNumberLiteral().apply(this);
        }
        outANumberAtom(node);
    }

    public void inAInputAtom(AInputAtom node)
    {
        defaultIn(node);
    }

    public void outAInputAtom(AInputAtom node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInputAtom(AInputAtom node)
    {
        inAInputAtom(node);
        if(node.getInputPrompt() != null)
        {
            node.getInputPrompt().apply(this);
        }
        outAInputAtom(node);
    }

    public void inAExpressionAtom(AExpressionAtom node)
    {
        defaultIn(node);
    }

    public void outAExpressionAtom(AExpressionAtom node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpressionAtom(AExpressionAtom node)
    {
        inAExpressionAtom(node);
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        outAExpressionAtom(node);
    }

    public void inASingleExpressionList(ASingleExpressionList node)
    {
        defaultIn(node);
    }

    public void outASingleExpressionList(ASingleExpressionList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleExpressionList(ASingleExpressionList node)
    {
        inASingleExpressionList(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outASingleExpressionList(node);
    }

    public void inASequenceExpressionList(ASequenceExpressionList node)
    {
        defaultIn(node);
    }

    public void outASequenceExpressionList(ASequenceExpressionList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASequenceExpressionList(ASequenceExpressionList node)
    {
        inASequenceExpressionList(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getExpressionList() != null)
        {
            node.getExpressionList().apply(this);
        }
        outASequenceExpressionList(node);
    }

    public void inASingleAtomList(ASingleAtomList node)
    {
        defaultIn(node);
    }

    public void outASingleAtomList(ASingleAtomList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleAtomList(ASingleAtomList node)
    {
        inASingleAtomList(node);
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
        }
        outASingleAtomList(node);
    }

    public void inASequenceAtomList(ASequenceAtomList node)
    {
        defaultIn(node);
    }

    public void outASequenceAtomList(ASequenceAtomList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASequenceAtomList(ASequenceAtomList node)
    {
        inASequenceAtomList(node);
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getAtomList() != null)
        {
            node.getAtomList().apply(this);
        }
        outASequenceAtomList(node);
    }
}
