/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class AVarAtom extends PAtom
{
    private TVariableName _variableName_;

    public AVarAtom()
    {
        // Constructor
    }

    public AVarAtom(
        @SuppressWarnings("hiding") TVariableName _variableName_)
    {
        // Constructor
        setVariableName(_variableName_);

    }

    @Override
    public Object clone()
    {
        return new AVarAtom(
            cloneNode(this._variableName_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarAtom(this);
    }

    public TVariableName getVariableName()
    {
        return this._variableName_;
    }

    public void setVariableName(TVariableName node)
    {
        if(this._variableName_ != null)
        {
            this._variableName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._variableName_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._variableName_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._variableName_ == child)
        {
            this._variableName_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._variableName_ == oldChild)
        {
            setVariableName((TVariableName) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
