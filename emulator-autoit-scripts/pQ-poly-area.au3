Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('area{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"POLY AREA"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is CATALOG)
MouseClick('left', $x + 78, $y + 578) ; CATALOG (state is CATALOG)
Send('D{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{DOWN}{ENTER}') ; Deg (state is ["CATALOG"])
Send('{ENTER}')
Send('"LEN"?')
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
Send('L{ENTER}')
Send('"NO OF SIDES"?')
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
Send('N{ENTER}')
Send('360/N')
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is CATALOG)
MouseClick('left', $x + 159, $y + 413) ; theta (state is CATALOG)
Send('{ENTER}')
Send('0.5*')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is CATALOG)
MouseClick('left', $x + 159, $y + 413) ; theta (state is CATALOG)
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is CATALOG)
MouseClick('left', $x + 159, $y + 413) ; theta (state is CATALOG)
Send('{ENTER}')
Send('0.5L/')
MouseClick('left', $x + 290, $y + 451) ; tan (state is CATALOG)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is CATALOG)
MouseClick('left', $x + 159, $y + 413) ; theta (state is CATALOG)
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
Send('B{ENTER}')
Send('B*0.5L*0.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
Send('B{ENTER}')
Send('2B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is CATALOG)
Send('B{ENTER}')
Send('N*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM)
Send('{F1}') ; EXE
