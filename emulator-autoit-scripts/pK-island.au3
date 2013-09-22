Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('island{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"ISLAND"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F3}{F1}') ; ViewWindow
Send('2,11,100,2,8,100{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('11,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-(3/31)')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F5}') ; >= (state is ["PRGM", "more", "REL"])
Send('2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('6.0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 159, $y + 413) ; theta (state is PRGM -> JUMP)
Send('{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('B-0.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('B-3.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; sqrt (state is PRGM -> JUMP)
Send('(6.25-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{^}2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(4-C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(4{+}C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{+}1')
MouseClick('left', $x + 290, $y + 487) ; ->
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{ENTER}')
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('5')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is OPTN -> more -> NUM)
MouseClick('left', $x + 159, $y + 413) ; theta (state is OPTN -> more -> NUM)
Send('/2)=0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(4-C),(10-B){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(4{+}C),(10-B){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('6.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 159, $y + 413) ; theta (state is PRGM -> JUMP)
Send('{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('B-0.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('B{+}(8.5/3)-6.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; sqrt (state is PRGM -> JUMP)
Send('((8.5/3){^}2-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{^}2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(6.5-C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(6.5{+}C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{+}1')
MouseClick('left', $x + 290, $y + 487) ; ->
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{ENTER}')
Send('B<5')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is OPTN -> more -> NUM)
MouseClick('left', $x + 159, $y + 413) ; theta (state is OPTN -> more -> NUM)
Send('/2)=0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(6.5-C),(10-B){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(6.5{+}C),(10-B){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
Send('8')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 159, $y + 413) ; theta (state is PRGM -> JUMP)
Send('{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
Send('B-(2/21)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F1}') ; Abs  (state is ["OPTN", "more", "NUM"])
Send('(B-7)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> NUM)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is OPTN -> more -> NUM)
MouseClick('left', $x + 117, $y + 413) ; rho (state is OPTN -> more -> NUM)
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is OPTN -> more -> NUM)
MouseClick('left', $x + 117, $y + 413) ; sqrt (state is OPTN -> more -> NUM)
Send('(1-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is OPTN -> more -> NUM)
MouseClick('left', $x + 117, $y + 413) ; rho (state is OPTN -> more -> NUM)
Send('{^}2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> NUM)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(10-C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(10{+}C),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{+}1')
MouseClick('left', $x + 290, $y + 487) ; ->
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 159, $y + 413) ; theta
Send('{ENTER}')
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('6.1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F3}') ; Frac  (state is ["OPTN", "more", "NUM"])
Send('(')
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is OPTN -> more -> NUM)
MouseClick('left', $x + 159, $y + 413) ; theta (state is OPTN -> more -> NUM)
Send('/3)')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F2}') ; != (state is ["PRGM", "more", "REL"])
Send('0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(10-.75C),(1.5B-7){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(10{+}.75C),(1.5B-7){ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('6{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('7{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.91,7.61{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('4.01,7.61{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('4.11,7.52{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('4.2,7.61{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('4.3,7.61{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.24,7.42{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.34,7.42{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.44,7.32{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.53,7.42{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3.63,7.42{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 202, $y + 413) ; QUIT
Send('{F1}') ; EXE
