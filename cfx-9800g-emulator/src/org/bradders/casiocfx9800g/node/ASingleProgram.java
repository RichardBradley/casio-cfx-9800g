/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class ASingleProgram extends PProgram
{
    private PStatement _statement_;
    private TStatementSeparator _statementSeparator_;

    public ASingleProgram()
    {
        // Constructor
    }

    public ASingleProgram(
        @SuppressWarnings("hiding") PStatement _statement_,
        @SuppressWarnings("hiding") TStatementSeparator _statementSeparator_)
    {
        // Constructor
        setStatement(_statement_);

        setStatementSeparator(_statementSeparator_);

    }

    @Override
    public Object clone()
    {
        return new ASingleProgram(
            cloneNode(this._statement_),
            cloneNode(this._statementSeparator_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleProgram(this);
    }

    public PStatement getStatement()
    {
        return this._statement_;
    }

    public void setStatement(PStatement node)
    {
        if(this._statement_ != null)
        {
            this._statement_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._statement_ = node;
    }

    public TStatementSeparator getStatementSeparator()
    {
        return this._statementSeparator_;
    }

    public void setStatementSeparator(TStatementSeparator node)
    {
        if(this._statementSeparator_ != null)
        {
            this._statementSeparator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._statementSeparator_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._statement_)
            + toString(this._statementSeparator_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._statement_ == child)
        {
            this._statement_ = null;
            return;
        }

        if(this._statementSeparator_ == child)
        {
            this._statementSeparator_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._statement_ == oldChild)
        {
            setStatement((PStatement) newChild);
            return;
        }

        if(this._statementSeparator_ == oldChild)
        {
            setStatementSeparator((TStatementSeparator) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
