/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class TFunctionNoargsName extends Token
{
    public TFunctionNoargsName()
    {
        super.setText("Ran#");
    }

    public TFunctionNoargsName(int line, int pos)
    {
        super.setText("Ran#");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFunctionNoargsName(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFunctionNoargsName(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TFunctionNoargsName text.");
    }
}
