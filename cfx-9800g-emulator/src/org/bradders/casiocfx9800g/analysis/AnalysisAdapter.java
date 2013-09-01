/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.analysis;

import java.util.*;
import org.bradders.casiocfx9800g.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleProgram(ASingleProgram node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASequenceProgram(ASequenceProgram node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPrinttextStatement(APrinttextStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAssignStatement(AAssignStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPrintvalStatement(APrintvalStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALabelStatement(ALabelStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGotoStatement(AGotoStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASubNoargsStatement(ASubNoargsStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASubArgsStatement(ASubArgsStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfStatement(AIfStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseACountJumpStatement(ACountJumpStatement node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATermExpression(ATermExpression node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusExpression(APlusExpression node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMinusExpression(AMinusExpression node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFactorTerm(AFactorTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultTerm(AMultTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivTerm(ADivTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleFactor(ASingleFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultgroupFactor(AMultgroupFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANegateFactor(ANegateFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPostfixopMultgroup(APostfixopMultgroup node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPowerMultgroup(APowerMultgroup node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncPostfixop(AFuncPostfixop node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFactorialPostfixop(AFactorialPostfixop node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAtomFunc(AAtomFunc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFunc1Func(AFunc1Func node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFunc2Func(AFunc2Func node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarAtom(AVarAtom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANumberAtom(ANumberAtom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFractionAtom(AFractionAtom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInputAtom(AInputAtom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpressionAtom(AExpressionAtom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPairFractionLiteral(APairFractionLiteral node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATripleFractionLiteral(ATripleFractionLiteral node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleExpressionList(ASingleExpressionList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASequenceExpressionList(ASequenceExpressionList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASingleAtomList(ASingleAtomList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASequenceAtomList(ASequenceAtomList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTStatementSeparator(TStatementSeparator node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTQuotedText(TQuotedText node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVariableName(TVariableName node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNumberLiteral(TNumberLiteral node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFractionSep(TFractionSep node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPrintResult(TPrintResult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLparen(TLparen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRparen(TRparen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAssignArrow(TAssignArrow node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTThenArrow(TThenArrow node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFunctionName(TFunctionName node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSubNoargsName(TSubNoargsName node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSubArgsName(TSubArgsName node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComparisonOp(TComparisonOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPow(TPow node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLabel(TLabel node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGoto(TGoto node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInputPrompt(TInputPrompt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBang(TBang node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSpace(TSpace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCountJumpOp(TCountJumpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
