package org.bradders.casiocfx9800g;

import org.bradders.casiocfx9800g.analysis.DepthFirstAdapter;

/**
 * This Analyser prepares the AST for running.
 *
 * The list of statements is extracted, Goto / Labels are prepared and
 * various static checks are made.  
 */
public class CompileTimeAnalyser extends DepthFirstAdapter
{
// qq check jumps are ints, not floats
}
