These files are from my Casio CFX-9800G
They were written in around qq

The CFX-9800G was released in 1995.
It has 24Kb of memory
http://www.rskey.org/CMS/index.php/7?manufacturer=Casio&model=CFX-9800G

File Naming Scheme
==================


Loading the files
=================

The emulator will save any files you add to "storage memory" to the "gy363.fls" archive, but I can't see how to easily add data into there without typing it into the emulator.

So qq enter keys

Get "AutoIt" from http://www.autoitscript.com/site/autoit/downloads/
Then qq

Notation
========

In program mode, the CFX-9800G has several symbols which are difficult to reproduce in ASCII:

  ->   is used for the right arrow symbol, meaning "assign to variable"

  =>   is used for the double right arrow symbol, meaning "then" (and implying
       a preceeding if)

  \n   normal newlines are used in the files where the casio would display a
       return symbol (U+23CE)

  /    is used for the division symbol (U+00F7)

  #    is used for the "Display result command", a black down-right triangle,
       like U+25E2 (seems to be stored as 0x0C by the calc)

  !=   is used for the single "not equal" char on the casio
  <=, >= are used for the single char relational operators on the casio

  Lbl, Goto, Prog, Isz, Dsz, Deg, Range ...
       various keywords are treated as a single char on the casio, but are written in full in these files. (This could lead to ambiguity, but not in normal usage.)

  "r"  the casio distinguishes between rho and r qq

  [dt] qq

  ^2   qq


Programming Langauge Quickstart
===============================

The programming language used is fairly standard. A few things are worth noting for those familiar with modern PC languages like Java or C#:

":" or "\n" (shown as a down-left "return" arrow) are used to separate statements, and are equivalent.

If / Then is implied by the "=>" operator, e.g.
  X > 1 => Goto 1 : Goto 2

Isz / Dsz do "count jumps". See page 307 of CFX-9800G.pdf

Any text in double quotes is printed to the screen.
