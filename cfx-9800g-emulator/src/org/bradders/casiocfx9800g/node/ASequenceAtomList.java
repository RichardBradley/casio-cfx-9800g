/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class ASequenceAtomList extends PAtomList
{
    private PAtom _atom_;
    private TComma _comma_;
    private PAtomList _atomList_;

    public ASequenceAtomList()
    {
        // Constructor
    }

    public ASequenceAtomList(
        @SuppressWarnings("hiding") PAtom _atom_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PAtomList _atomList_)
    {
        // Constructor
        setAtom(_atom_);

        setComma(_comma_);

        setAtomList(_atomList_);

    }

    @Override
    public Object clone()
    {
        return new ASequenceAtomList(
            cloneNode(this._atom_),
            cloneNode(this._comma_),
            cloneNode(this._atomList_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASequenceAtomList(this);
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

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PAtomList getAtomList()
    {
        return this._atomList_;
    }

    public void setAtomList(PAtomList node)
    {
        if(this._atomList_ != null)
        {
            this._atomList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._atomList_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._atom_)
            + toString(this._comma_)
            + toString(this._atomList_);
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

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._atomList_ == child)
        {
            this._atomList_ = null;
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

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._atomList_ == oldChild)
        {
            setAtomList((PAtomList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
