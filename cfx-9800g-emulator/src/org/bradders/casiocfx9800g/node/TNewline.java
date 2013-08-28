/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class TNewline extends Token
{
    public TNewline(String text)
    {
        setText(text);
    }

    public TNewline(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNewline(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNewline(this);
    }
}
