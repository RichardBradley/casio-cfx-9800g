/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.analysis;

import org.bradders.casiocfx9800g.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseASingleProgram(ASingleProgram node);
    void caseASequenceProgram(ASequenceProgram node);
    void caseAPrinttextStatement(APrinttextStatement node);
    void caseAAssignStatement(AAssignStatement node);
    void caseAPrintvalStatement(APrintvalStatement node);
    void caseALabelStatement(ALabelStatement node);
    void caseAGotoStatement(AGotoStatement node);
    void caseASubNoargsStatement(ASubNoargsStatement node);
    void caseASubArgsStatement(ASubArgsStatement node);
    void caseAIfStatement(AIfStatement node);
    void caseACountJumpStatement(ACountJumpStatement node);
    void caseATermExpression(ATermExpression node);
    void caseAPlusExpression(APlusExpression node);
    void caseAMinusExpression(AMinusExpression node);
    void caseANegateExpression(ANegateExpression node);
    void caseAFactorTerm(AFactorTerm node);
    void caseAMultTerm(AMultTerm node);
    void caseADivTerm(ADivTerm node);
    void caseASingleFactor(ASingleFactor node);
    void caseAMultgroupFactor(AMultgroupFactor node);
    void caseAPostfixopMultgroup(APostfixopMultgroup node);
    void caseAPowerMultgroup(APowerMultgroup node);
    void caseAFuncPostfixop(AFuncPostfixop node);
    void caseAFactorialPostfixop(AFactorialPostfixop node);
    void caseAAtomFunc(AAtomFunc node);
    void caseAFunc1Func(AFunc1Func node);
    void caseAFunc2Func(AFunc2Func node);
    void caseAVarAtom(AVarAtom node);
    void caseANumberAtom(ANumberAtom node);
    void caseAInputAtom(AInputAtom node);
    void caseAExpressionAtom(AExpressionAtom node);
    void caseASingleExpressionList(ASingleExpressionList node);
    void caseASequenceExpressionList(ASequenceExpressionList node);
    void caseASingleAtomList(ASingleAtomList node);
    void caseASequenceAtomList(ASequenceAtomList node);

    void caseTStatementSeparator(TStatementSeparator node);
    void caseTQuotedText(TQuotedText node);
    void caseTVariableName(TVariableName node);
    void caseTNumberLiteral(TNumberLiteral node);
    void caseTComma(TComma node);
    void caseTPrintResult(TPrintResult node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTLparen(TLparen node);
    void caseTRparen(TRparen node);
    void caseTAssignArrow(TAssignArrow node);
    void caseTThenArrow(TThenArrow node);
    void caseTFunctionName(TFunctionName node);
    void caseTSubNoargsName(TSubNoargsName node);
    void caseTSubArgsName(TSubArgsName node);
    void caseTComparisonOp(TComparisonOp node);
    void caseTPow(TPow node);
    void caseTLabel(TLabel node);
    void caseTGoto(TGoto node);
    void caseTInputPrompt(TInputPrompt node);
    void caseTBang(TBang node);
    void caseTSpace(TSpace node);
    void caseTCountJumpOp(TCountJumpOp node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
