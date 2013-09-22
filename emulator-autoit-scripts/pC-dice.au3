Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('dice{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"DICE"{ENTER}')
Send('100')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('S{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('P{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('"YOU HAVE"{ENTER}')
Send('S')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('"BET="{ENTER}')
Send('?')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('B{ENTER}')
Send('S-B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('S{ENTER}')
Send('P{+}(B*2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('P{ENTER}')
Send('"YOU GOT"{ENTER}')
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*6){+}1){+}(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*6){+}1)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('Y')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('Y=7')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('P{+}S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('Y=7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"U WIN THE POT"{ENTER}')
Send('Y=7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('P')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('Y=7')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('P')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('Y>7')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('P-B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('P{ENTER}')
Send('Y>7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('S{+}B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('Y>7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"U KEEP YOU BET"{ENTER}')
Send('Y=7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('"HE GOT"{ENTER}')
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*6){+}1){+}(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*6){+}1)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('H')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('H=7')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"HE WON THE POT"{ENTER}')
Send('H=7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('P')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('H=7')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('P{ENTER}')
Send('H>7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('P-B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('P{ENTER}')
Send('H>7{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"HE KEEPS HIS BET"{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('S<0{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('S')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F5}') ; >= (state is ["PRGM", "more", "REL"])
Send('1000000')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('"YOU''RE A MILLIONAIRE..."{ENTER}')
Send('"YOU WIN"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('"YOU''RE BROKE"{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F1}') ; EXE
