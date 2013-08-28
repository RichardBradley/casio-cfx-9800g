/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class AAtomFactor extends PFactor
{
    private PAtom _atom_;

    public AAtomFactor()
    {
        // Constructor
    }

    public AAtomFactor(
        @SuppressWarnings("hiding") PAtom _atom_)
    {
        // Constructor
        setAtom(_atom_);

    }

    @Override
    public Object clone()
    {
        return new AAtomFactor(
            cloneNode(this._atom_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAtomFactor(this);
    }

    public PAtom getAtom()
    {
        return this._atom_;
    }

    public void setAtom(PAtom node)
    {
        if(this._atom_ != null)
        {
            this._atom_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._atom_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._atom_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._atom_ == child)
        {
            this._atom_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._atom_ == oldChild)
        {
            setAtom((PAtom) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
