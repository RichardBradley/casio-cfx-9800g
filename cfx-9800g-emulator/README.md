I found an emulator, but it was not for the colour version of the calc (qq fill in model numbers).

So I decided to write a simple emulator to run the graphical programs in 3 colours.

First, download sablecc.

Write the grammar file -- see sablecc/casio-cfx-9800g.sablecc 
See http://sablecc.org/ for sablecc docs

Use SableCC to generate a parser from the grammar file:
    java -jar lib/sablecc-3.7/lib/sablecc.jar -d src src/sablecc/casio-cfx-9800g.sablecc 

