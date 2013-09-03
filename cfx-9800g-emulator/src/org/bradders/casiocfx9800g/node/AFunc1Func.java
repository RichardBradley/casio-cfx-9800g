/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class AFunc1Func extends PFunc
{
    private TFunctionName _functionName_;
    private PMultgroup _multgroup_;

    public AFunc1Func()
    {
        // Constructor
    }

    public AFunc1Func(
        @SuppressWarnings("hiding") TFunctionName _functionName_,
        @SuppressWarnings("hiding") PMultgroup _multgroup_)
    {
        // Constructor
        setFunctionName(_functionName_);

        setMultgroup(_multgroup_);

    }

    @Override
    public Object clone()
    {
        return new AFunc1Func(
            cloneNode(this._functionName_),
            cloneNode(this._multgroup_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFunc1Func(this);
    }

    public TFunctionName getFunctionName()
    {
        return this._functionName_;
    }

    public void setFunctionName(TFunctionName node)
    {
        if(this._functionName_ != null)
        {
            this._functionName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._functionName_ = node;
    }

    public PMultgroup getMultgroup()
    {
        return this._multgroup_;
    }

    public void setMultgroup(PMultgroup node)
    {
        if(this._multgroup_ != null)
        {
            this._multgroup_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._multgroup_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._functionName_)
            + toString(this._multgroup_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._functionName_ == child)
        {
            this._functionName_ = null;
            return;
        }

        if(this._multgroup_ == child)
        {
            this._multgroup_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._functionName_ == oldChild)
        {
            setFunctionName((TFunctionName) newChild);
            return;
        }

        if(this._multgroup_ == oldChild)
        {
            setMultgroup((PMultgroup) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
