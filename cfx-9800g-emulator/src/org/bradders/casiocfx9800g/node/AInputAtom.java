/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class AInputAtom extends PAtom
{
    private TInputPrompt _inputPrompt_;

    public AInputAtom()
    {
        // Constructor
    }

    public AInputAtom(
        @SuppressWarnings("hiding") TInputPrompt _inputPrompt_)
    {
        // Constructor
        setInputPrompt(_inputPrompt_);

    }

    @Override
    public Object clone()
    {
        return new AInputAtom(
            cloneNode(this._inputPrompt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInputAtom(this);
    }

    public TInputPrompt getInputPrompt()
    {
        return this._inputPrompt_;
    }

    public void setInputPrompt(TInputPrompt node)
    {
        if(this._inputPrompt_ != null)
        {
            this._inputPrompt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._inputPrompt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._inputPrompt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._inputPrompt_ == child)
        {
            this._inputPrompt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._inputPrompt_ == oldChild)
        {
            setInputPrompt((TInputPrompt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
