/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class ASubArgsStatement extends PStatement
{
    private TSubArgsName _subArgsName_;
    private TSpace _space_;
    private PExpressionList _expressionList_;

    public ASubArgsStatement()
    {
        // Constructor
    }

    public ASubArgsStatement(
        @SuppressWarnings("hiding") TSubArgsName _subArgsName_,
        @SuppressWarnings("hiding") TSpace _space_,
        @SuppressWarnings("hiding") PExpressionList _expressionList_)
    {
        // Constructor
        setSubArgsName(_subArgsName_);

        setSpace(_space_);

        setExpressionList(_expressionList_);

    }

    @Override
    public Object clone()
    {
        return new ASubArgsStatement(
            cloneNode(this._subArgsName_),
            cloneNode(this._space_),
            cloneNode(this._expressionList_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASubArgsStatement(this);
    }

    public TSubArgsName getSubArgsName()
    {
        return this._subArgsName_;
    }

    public void setSubArgsName(TSubArgsName node)
    {
        if(this._subArgsName_ != null)
        {
            this._subArgsName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._subArgsName_ = node;
    }

    public TSpace getSpace()
    {
        return this._space_;
    }

    public void setSpace(TSpace node)
    {
        if(this._space_ != null)
        {
            this._space_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._space_ = node;
    }

    public PExpressionList getExpressionList()
    {
        return this._expressionList_;
    }

    public void setExpressionList(PExpressionList node)
    {
        if(this._expressionList_ != null)
        {
            this._expressionList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expressionList_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._subArgsName_)
            + toString(this._space_)
            + toString(this._expressionList_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._subArgsName_ == child)
        {
            this._subArgsName_ = null;
            return;
        }

        if(this._space_ == child)
        {
            this._space_ = null;
            return;
        }

        if(this._expressionList_ == child)
        {
            this._expressionList_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._subArgsName_ == oldChild)
        {
            setSubArgsName((TSubArgsName) newChild);
            return;
        }

        if(this._space_ == oldChild)
        {
            setSpace((TSpace) newChild);
            return;
        }

        if(this._expressionList_ == oldChild)
        {
            setExpressionList((PExpressionList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
