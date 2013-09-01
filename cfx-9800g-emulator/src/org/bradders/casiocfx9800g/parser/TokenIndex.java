/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.parser;

import org.bradders.casiocfx9800g.node.*;
import org.bradders.casiocfx9800g.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTStatementSeparator(@SuppressWarnings("unused") TStatementSeparator node)
    {
        this.index = 0;
    }

    @Override
    public void caseTQuotedText(@SuppressWarnings("unused") TQuotedText node)
    {
        this.index = 1;
    }

    @Override
    public void caseTVariableName(@SuppressWarnings("unused") TVariableName node)
    {
        this.index = 2;
    }

    @Override
    public void caseTNumberLiteral(@SuppressWarnings("unused") TNumberLiteral node)
    {
        this.index = 3;
    }

    @Override
    public void caseTFractionSep(@SuppressWarnings("unused") TFractionSep node)
    {
        this.index = 4;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 5;
    }

    @Override
    public void caseTPrintResult(@SuppressWarnings("unused") TPrintResult node)
    {
        this.index = 6;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 7;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 8;
    }

    @Override
    public void caseTMult(@SuppressWarnings("unused") TMult node)
    {
        this.index = 9;
    }

    @Override
    public void caseTDiv(@SuppressWarnings("unused") TDiv node)
    {
        this.index = 10;
    }

    @Override
    public void caseTLparen(@SuppressWarnings("unused") TLparen node)
    {
        this.index = 11;
    }

    @Override
    public void caseTRparen(@SuppressWarnings("unused") TRparen node)
    {
        this.index = 12;
    }

    @Override
    public void caseTAssignArrow(@SuppressWarnings("unused") TAssignArrow node)
    {
        this.index = 13;
    }

    @Override
    public void caseTThenArrow(@SuppressWarnings("unused") TThenArrow node)
    {
        this.index = 14;
    }

    @Override
    public void caseTFunctionName(@SuppressWarnings("unused") TFunctionName node)
    {
        this.index = 15;
    }

    @Override
    public void caseTSubNoargsName(@SuppressWarnings("unused") TSubNoargsName node)
    {
        this.index = 16;
    }

    @Override
    public void caseTSubArgsName(@SuppressWarnings("unused") TSubArgsName node)
    {
        this.index = 17;
    }

    @Override
    public void caseTComparisonOp(@SuppressWarnings("unused") TComparisonOp node)
    {
        this.index = 18;
    }

    @Override
    public void caseTPow(@SuppressWarnings("unused") TPow node)
    {
        this.index = 19;
    }

    @Override
    public void caseTLabel(@SuppressWarnings("unused") TLabel node)
    {
        this.index = 20;
    }

    @Override
    public void caseTGoto(@SuppressWarnings("unused") TGoto node)
    {
        this.index = 21;
    }

    @Override
    public void caseTInputPrompt(@SuppressWarnings("unused") TInputPrompt node)
    {
        this.index = 22;
    }

    @Override
    public void caseTBang(@SuppressWarnings("unused") TBang node)
    {
        this.index = 23;
    }

    @Override
    public void caseTSpace(@SuppressWarnings("unused") TSpace node)
    {
        this.index = 24;
    }

    @Override
    public void caseTCountJumpOp(@SuppressWarnings("unused") TCountJumpOp node)
    {
        this.index = 25;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 26;
    }
}
