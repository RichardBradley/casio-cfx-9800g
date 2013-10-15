casio-cfx-9800g Overview and Introduction
=========================================

Calculator programs I wrote in secondary school, and an emulator to run them on.

"calc-programs" 
---------------

The files in "calc-programs" are transcribed from my Casio CFX-9800G.
I wrote the originals sometime between 1996 and 2001.

The CFX-9800G was released in 1995.
It has 24Kb of memory
http://www.rskey.org/CMS/index.php/7?manufacturer=Casio&model=CFX-9800G

"fx-9860g-emulator", "casio-prog-txt-to-au3.rb"
-----------------------------------------------

This is a copy of Casio's official emulator for the "fx-9800G USB" device. This is very similar to the CFX-9800G except that it is a bit more recent (so has some extra functionality), and is black-and-white (so cannot run some of my old "picture" programs).

The ruby script "casio-prog-txt-to-au3.rb" can convert casio programs in the notation used here (see "Notation" below) into ".au3" scripts for [AutoIt](http://www.autoitscript.com/site/autoit/downloads/) to run any of these scripts on that emulator. The ruby script will not accept programs which use any of the colour commands ("Orange Line" etc.).

This emulator is useful for verifying the accuracy of the Java emulator (see "cfx-9800g-emulator" below).

(Note that the ruby script uploads the program files into the emulator by typing in the program text using AutoIt. This seems a little redundant, but I couldn't figure out a sensble system for uploading files directly into the emulator. The emulator does have a "storage memory" configured which interacts with the filesystem, but it saves any files inside the binary "gy363.fls" archive -- how can I add files to that archive without reverse-engineering the binary format?)

The fx-9800G USB has a screen resolution of 128x64, whereas the CFX-9800G has only 96x64.

The ruby script notices magic comments starting '// fx-emulator:' to allow for modifications to the program (for example, to change the Range of the graph image to account for the different screen resolutions).

"cfx-9800g-emulator"
--------------------

This is a Java interpreter I wrote to run the casio programs. It uses [SableCC](http://sablecc.org/)

There are two front ends to the app. It can run as a standalone java app which handles text input & output via the console, and launches a JFrame to display any graphics drawn by the program. Or it can run as an Applet to embed the emulator in a web page (as on [my website](http://bradders.org/cfx-9800g/)).

The emulator simulates the 3-colour display available on the CFX-9800G. It can run all the programs here, but only has a small subset of the CFX's fucntionality.

Installation and Setup
======================

Checkout the code.

Casio's emulator
----------------

To run the programs using Casio's emulator, you will need to be using Windows. Then:

* Install [AutoIt](http://www.autoitscript.com/site/autoit/downloads/) and Ruby
* Convert the program from ".txt" to ".au3": `ruby casio-prog-txt-to-au3.rb calc-programs/p1-powerseqs.txt emulator-autoit-scripts/p1-powerseqs.au3`
** (Note that converted copies of all my programs are checked in to this repos.)
* Launch the generated ".au3" script: `start emulator-autoit-scripts/p1-powerseqs.au3`
* The script will launch a new emulator, type in the program and execute it

My emulator
-----------

To run the programs using my Java emulator, you'll need Eclipse:

* Open the cfx-9800g-emulator directory as a project in Eclipse
* Launch the project with the program file as the sole command-line argument
* The app will emulate the program, launching a JFrame to display any graphics if required

TODO: package this into a JAR or something to make this easier.

Notation
========

In program mode, the CFX-9800G has several symbols which are difficult to reproduce in ASCII:

`->`   is used for the right arrow symbol, meaning "assign to variable"

`=>`   is used for the double right arrow symbol, meaning "then" (there is no preceeding "if")

`\n`   normal newlines are used in the files where the Casio would display a
       return symbol (like U+23CE)

`/`    is used for the division symbol (U+00F7)

`#`    is used for the "Display result command", a black down-right triangle,
       like U+25E2 (seems to be stored as 0x0C by the calc)

`!=`   is used for the single "not equal" char on the Casio

`<=`, `>=` are used for the single char relational operators on the Casio

`Lbl`, `Goto`, `Prog`, `Isz`, `Dsz`, `Deg`, `Range` ...
       various keywords are treated as a single char on the Casio, but are written in full in these files. (This could lead to ambiguity v.s. variable names.)

`r`,`t`  Lowercase letters are hard to access on the Casio, but there are easily accessible buttons for rho and
       theta to use as variable names. For simplicity, these text files use '`r`' for rho and '`t`' for theta, and don't use any other lowercase letters.

`^2`   The calculator has a "squared" char which appears as superscript "2". In these
       files we just use "`^2`".

`|`    The calculator has a "fraction" symbol which appears like a small backwards "L".
       These files use "`|`" for this symbol.
       In this notation, `1|1|3` is "one and one-third", and `77|24` is 77/24 as a fraction.

`Prog` The Casio lets you call other programs from within a program (see "Using Subroutines", p308 of CFX-9800G.pdf).
       Since programs on the CFX-9800G don't have names, only a number or letter index, the `Prog` command takes
       a single letter or number as an argument.
       To aid readability, in these files `Prog` takes a string argument, which is the filename of the programe to jump to.
       (The function stack is only 10 deep on the Casio.)

(Range from my old casio is referred to as 'View Window' in the new casio)

Programming Langauge Quickstart
===============================

The programming language used is fairly self-explanatory. A few things are worth noting for those familiar with modern languages like Java or C#:

"`:`" or "`\n`" (shown as a down-left "return" arrow on the calc) are used to separate statements, and are equivalent.

If / Then is implied by the "`=>`" operator, e.g.

    X > 1 => Goto 1 : Goto 2

`Isz` / `Dsz` do "count jumps". See page 307 of CFX-9800G.pdf. They are basically `++` and `--`, except that if the new value is 0, then the next statement is skipped.

Any text in double quotes is printed to the screen.
