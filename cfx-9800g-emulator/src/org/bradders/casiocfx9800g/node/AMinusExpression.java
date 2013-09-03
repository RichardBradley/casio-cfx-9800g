/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class AMinusExpression extends PExpression
{
    private PExpression _left_;
    private TMinus _minus_;
    private PMultdiv _right_;

    public AMinusExpression()
    {
        // Constructor
    }

    public AMinusExpression(
        @SuppressWarnings("hiding") PExpression _left_,
        @SuppressWarnings("hiding") TMinus _minus_,
        @SuppressWarnings("hiding") PMultdiv _right_)
    {
        // Constructor
        setLeft(_left_);

        setMinus(_minus_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AMinusExpression(
            cloneNode(this._left_),
            cloneNode(this._minus_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMinusExpression(this);
    }

    public PExpression getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExpression node)
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

    public TMinus getMinus()
    {
        return this._minus_;
    }

    public void setMinus(TMinus node)
    {
        if(this._minus_ != null)
        {
            this._minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._minus_ = node;
    }

    public PMultdiv getRight()
    {
        return this._right_;
    }

    public void setRight(PMultdiv node)
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
            + toString(this._minus_)
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

        if(this._minus_ == child)
        {
            this._minus_ = null;
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
            setLeft((PExpression) newChild);
            return;
        }

        if(this._minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PMultdiv) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
