Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('casino{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"CASINO"{ENTER}')
Send('100')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('S{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('H{ENTER}')
Send('"IF YOU BET 0"{ENTER}')
Send('"AT ANY TIME"{ENTER}')
Send('"YOU CAN SEE THE"{ENTER}')
Send('"PRIZES {+} SCORE"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('(S/1')
MouseClick('left', $x + 182, $y + 663) ; EXP (state is PRGM -> JUMP)
Send('99)')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F5}') ; >= (state is ["PRGM", "more", "REL"])
Send('1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
Send('S>H{F3}') ; => (state is ["PRGM", "JUMP"])
Send('S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('H{ENTER}')
Send('"YOU HAVE"{ENTER}')
Send('S')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('"PLACE YOUR BET"{ENTER}')
Send('?')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('B:B=0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('B=0.5{F3}') ; => (state is ["PRGM", "JUMP"])
Send('S{+}999')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('B=0.1{F3}') ; => (state is ["PRGM", "JUMP"])
Send('2S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('B>S{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('S-B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*100)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('X{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*100)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('Y{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F2}') ; Int  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F3}') ; PROB (state is ["OPTN", "more", "PROB"])
Send('{F4}') ; Ran# (state is ["OPTN", "more", "PROB"])
Send('*100)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> PROB)
Send('Z{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('B=0.75')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('12')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('X{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('B=0.75')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('12')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('Y{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('B=0.75')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('12')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('Z{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGX"{ENTER}')
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGY"{ENTER}')
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGZ"{ENTER}')
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGA"{ENTER}')
Send('S=0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('"ABC - 50"{ENTER}')
Send('"AAA - 5"{ENTER}')
Send('"BBB - 100"{ENTER}')
Send('"CCC - 125"{ENTER}')
Send('0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('"Z   - 5"{ENTER}')
Send('"ZZ  - 50"{ENTER}')
Send('"ZZZ - 500"{ENTER}')
Send('"WOMBAT - ?"{ENTER}')
Send('"HISCORE =":H{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('"YOU RAN OUT OF"{ENTER}')
Send('"MONEY"{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('Y{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('"H   H   AA  :"{ENTER}')
Send('"H   H  A  A :"{ENTER}')
Send('"HHHHH  AAAA :"{ENTER}')
Send('"H   H  A  A"{ENTER}')
Send('"H   H  A  A ."{ENTER}')
Send('""{ENTER}')
Send('{F4}') ; Isz  (state is ["PRGM", "JUMP"])
Send('Y{ENTER}')
Send('Y')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('Y{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('" "{ENTER}')
Send('"Y Y EEEE  SS"{ENTER}')
Send('"Y Y E    S  S"{ENTER}')
Send('" Y  EEE   S"{ENTER}')
Send('" Y  E      S"{ENTER}')
Send('" Y  E    S  S"{ENTER}')
Send('" Y  EEEE  SS"{ENTER}')
Send('{F4}') ; Isz  (state is ["PRGM", "JUMP"])
Send('Y{ENTER}')
Send('Y')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('"YOU WIN"{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('progx{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('X=12')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('X>40{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D{ENTER}')
Send('"ARMADILLO"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('X>60{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('2')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D{ENTER}')
Send('"BABOON"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('X>80{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D{ENTER}')
Send('"CATTERPILLAR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D{ENTER}')
Send('"ZULU WARRIOR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('"WOMBAT"{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('progy{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('Y=12')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('Y>40{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('E{ENTER}')
Send('"ARMADILLO"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('Y>60{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('2')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('E{ENTER}')
Send('"BABOON"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('Y>80{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('E{ENTER}')
Send('"CATTERPILLAR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('E{ENTER}')
Send('"ZULU WARRIOR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('"WOMBAT"{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('E{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('progz{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('Z=12')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('Z>40{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('F{ENTER}')
Send('"ARMADILLO"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('Z>60{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('2')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('F{ENTER}')
Send('"BABOON"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('Z>80{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('F{ENTER}')
Send('"CATTERPILLAR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('F{ENTER}')
Send('"ZULU WARRIOR"{ENTER}')
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('"WOMBAT"{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('F{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('proga{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('D')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('S{+}(5*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('"YOU WON"{ENTER}')
Send('5*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('S{+}(50*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('"YOU WON"{ENTER}')
Send('50*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('D')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('S{+}(100*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('"YOU WON"{ENTER}')
Send('100*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('D')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('S{+}(125*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('"YOU WON"{ENTER}')
Send('125*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('D')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('S{+}(5*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"YOU WON"{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('5*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('S{+}(50*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('"YOU WON"{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('50*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('S{+}(500*B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('S{ENTER}')
Send('"YOU WON"{ENTER}')
Send('500*B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('D')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('5')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
Send('"YOU WON"{ENTER}')
Send('50*B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('B{+}S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('S{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
Send('E')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('5')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('"YOU WON"{ENTER}')
Send('50*B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('B{+}S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('S{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
Send('F')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('5')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')
Send('"YOU WON"{ENTER}')
Send('50*B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F5}') ; print result (state is ["PRGM"])
Send('B{+}S')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM)
Send('S{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('8{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F1}') ; EXE