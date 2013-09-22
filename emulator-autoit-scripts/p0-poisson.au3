Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('poisson{ENTER}') ; file name - at most 8 chars

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
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('"VALUE":?')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('X{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 159, $y + 451) ; e^x (state is PRGM -> JUMP)
Send('(-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send(')*(')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{^}X)/X')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F1}') ; x! (state is ["OPTN", "more", "PROB"])
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('C')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('"SUM="{ENTER}')
Send('C{+}S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('S{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F1}') ; EXE
