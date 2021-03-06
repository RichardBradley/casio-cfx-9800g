/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class TLabel extends Token
{
    public TLabel()
    {
        super.setText("Lbl ");
    }

    public TLabel(int line, int pos)
    {
        super.setText("Lbl ");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLabel(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLabel(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLabel text.");
    }
}
