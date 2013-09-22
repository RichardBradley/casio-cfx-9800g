Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('pseudoprime{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"PRIME OR PSEUDOPRIME"{ENTER}')
Send('"''P''":?')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('P{ENTER}')
Send('2{^}P')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('Q{ENTER}')
Send('Q-P*')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(Q/P)')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM)
Send('{F1}') ; EXE
