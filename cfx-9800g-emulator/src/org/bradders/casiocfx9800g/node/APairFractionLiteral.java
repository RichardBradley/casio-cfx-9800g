/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class APairFractionLiteral extends PFractionLiteral
{
    private TNumberLiteral _numerator_;
    private TFractionSep _fractionSep_;
    private TNumberLiteral _denominator_;

    public APairFractionLiteral()
    {
        // Constructor
    }

    public APairFractionLiteral(
        @SuppressWarnings("hiding") TNumberLiteral _numerator_,
        @SuppressWarnings("hiding") TFractionSep _fractionSep_,
        @SuppressWarnings("hiding") TNumberLiteral _denominator_)
    {
        // Constructor
        setNumerator(_numerator_);

        setFractionSep(_fractionSep_);

        setDenominator(_denominator_);

    }

    @Override
    public Object clone()
    {
        return new APairFractionLiteral(
            cloneNode(this._numerator_),
            cloneNode(this._fractionSep_),
            cloneNode(this._denominator_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPairFractionLiteral(this);
    }

    public TNumberLiteral getNumerator()
    {
        return this._numerator_;
    }

    public void setNumerator(TNumberLiteral node)
    {
        if(this._numerator_ != null)
        {
            this._numerator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numerator_ = node;
    }

    public TFractionSep getFractionSep()
    {
        return this._fractionSep_;
    }

    public void setFractionSep(TFractionSep node)
    {
        if(this._fractionSep_ != null)
        {
            this._fractionSep_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fractionSep_ = node;
    }

    public TNumberLiteral getDenominator()
    {
        return this._denominator_;
    }

    public void setDenominator(TNumberLiteral node)
    {
        if(this._denominator_ != null)
        {
            this._denominator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._denominator_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._numerator_)
            + toString(this._fractionSep_)
            + toString(this._denominator_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._numerator_ == child)
        {
            this._numerator_ = null;
            return;
        }

        if(this._fractionSep_ == child)
        {
            this._fractionSep_ = null;
            return;
        }

        if(this._denominator_ == child)
        {
            this._denominator_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._numerator_ == oldChild)
        {
            setNumerator((TNumberLiteral) newChild);
            return;
        }

        if(this._fractionSep_ == oldChild)
        {
            setFractionSep((TFractionSep) newChild);
            return;
        }

        if(this._denominator_ == oldChild)
        {
            setDenominator((TNumberLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
