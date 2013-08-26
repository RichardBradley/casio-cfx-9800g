$pid = Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
Send('POISSON{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"POISSON"{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('S{ENTER}')
Send('"LAMBDA":?')
MouseClick('left', $x + 290, $y + 487) ; ->
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 117, $y + 413) ; rho
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 159, $y + 376) ; PRGM
Send('{F3}{F1}') ; Lbl (state is still program -> jump menu)
Send('1{ENTER}')
Send('"VALUE":?')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('X{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 159, $y + 451) ; e^x
Send('(-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 117, $y + 413) ; rho
Send(')*(')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 117, $y + 413) ; rho
Send('{^}X)/X')
MouseClick('left', $x + 117, $y + 376) ; OPTN
Send('{F6}{F3}') ; PROB
Send('{F1}') ; !
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 159, $y + 376) ; PRGM
Send('{F5}') ; Print result (state is still program menu)
Send('"SUM="{ENTER}')
Send('C{+}S')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('S')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 159, $y + 376) ; PRGM
Send('{F5}') ; Print result (state is still program menu)
Send('{F3}{F2}') ; Goto (state is still program -> jump menu)
MouseClick('left', $x + 202, $y + 413) ; EXIT (state is now program menu)
MouseClick('left', $x + 202, $y + 413) ; EXIT (state is now normal)
Send('1')

; end program text

MouseClick('left', $x + 202, $y + 413) ; EXIT
Send('{F1}') ; EXE
