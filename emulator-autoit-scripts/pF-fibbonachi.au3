Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('fibbonachi{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"FIBBONACHI"{ENTER}')
Send('?')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('?')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('A{+}B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('A{ENTER}')
Send('C')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F1}') ; EXE
