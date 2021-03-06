/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class ALabelStatement extends PStatement
{
    private TLabel _label_;
    private TNumberLiteral _numberLiteral_;

    public ALabelStatement()
    {
        // Constructor
    }

    public ALabelStatement(
        @SuppressWarnings("hiding") TLabel _label_,
        @SuppressWarnings("hiding") TNumberLiteral _numberLiteral_)
    {
        // Constructor
        setLabel(_label_);

        setNumberLiteral(_numberLiteral_);

    }

    @Override
    public Object clone()
    {
        return new ALabelStatement(
            cloneNode(this._label_),
            cloneNode(this._numberLiteral_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALabelStatement(this);
    }

    public TLabel getLabel()
    {
        return this._label_;
    }

    public void setLabel(TLabel node)
    {
        if(this._label_ != null)
        {
            this._label_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._label_ = node;
    }

    public TNumberLiteral getNumberLiteral()
    {
        return this._numberLiteral_;
    }

    public void setNumberLiteral(TNumberLiteral node)
    {
        if(this._numberLiteral_ != null)
        {
            this._numberLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numberLiteral_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._label_)
            + toString(this._numberLiteral_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._label_ == child)
        {
            this._label_ = null;
            return;
        }

        if(this._numberLiteral_ == child)
        {
            this._numberLiteral_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._label_ == oldChild)
        {
            setLabel((TLabel) newChild);
            return;
        }

        if(this._numberLiteral_ == oldChild)
        {
            setNumberLiteral((TNumberLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
