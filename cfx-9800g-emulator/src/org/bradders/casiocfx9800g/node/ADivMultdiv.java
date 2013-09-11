/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class ADivMultdiv extends PMultdiv
{
    private PMultdiv _left_;
    private TDiv _div_;
    private PPrefixop _right_;

    public ADivMultdiv()
    {
        // Constructor
    }

    public ADivMultdiv(
        @SuppressWarnings("hiding") PMultdiv _left_,
        @SuppressWarnings("hiding") TDiv _div_,
        @SuppressWarnings("hiding") PPrefixop _right_)
    {
        // Constructor
        setLeft(_left_);

        setDiv(_div_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new ADivMultdiv(
            cloneNode(this._left_),
            cloneNode(this._div_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADivMultdiv(this);
    }

    public PMultdiv getLeft()
    {
        return this._left_;
    }

    public void setLeft(PMultdiv node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TDiv getDiv()
    {
        return this._div_;
    }

    public void setDiv(TDiv node)
    {
        if(this._div_ != null)
        {
            this._div_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._div_ = node;
    }

    public PPrefixop getRight()
    {
        return this._right_;
    }

    public void setRight(PPrefixop node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._div_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._div_ == child)
        {
            this._div_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PMultdiv) newChild);
            return;
        }

        if(this._div_ == oldChild)
        {
            setDiv((TDiv) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PPrefixop) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}