/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.bradders.casiocfx9800g.node;

import org.bradders.casiocfx9800g.analysis.*;

@SuppressWarnings("nls")
public final class TPrintResult extends Token
{
    public TPrintResult()
    {
        super.setText("#");
    }

    public TPrintResult(int line, int pos)
    {
        super.setText("#");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TPrintResult(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTPrintResult(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TPrintResult text.");
    }
}
