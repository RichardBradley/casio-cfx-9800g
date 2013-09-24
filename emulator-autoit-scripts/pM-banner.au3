Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('banner{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"BANNER"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F3}{F1}') ; ViewWindow
Send('3.5,8.8333,100,1,4,100{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('4')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(A{+}1),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-0.04')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F5}') ; >= (state is ["PRGM", "more", "REL"])
Send('3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
MouseClick('left', $x + 117, $y + 376) ; OPTN (state is OPTN)
Send('{F6}') ; more (state is ["OPTN", "more"])
Send('{F4}') ; NUM (state is ["OPTN", "more", "NUM"])
Send('{F1}') ; Abs  (state is ["OPTN", "more", "NUM"])
Send('(2.5-B)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> NUM)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is OPTN -> more -> NUM)
MouseClick('left', $x + 117, $y + 413) ; sqrt (state is OPTN -> more -> NUM)
Send('(0.25-C{^}2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is OPTN -> more -> NUM)
Send('D{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(5.5-D),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(5.5{+}D),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('6,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-0.05')
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
Send('2{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('6,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-0.04')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
Send('B>1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.1,2.5{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.3,2.3{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.7,2.7{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.9,2.5{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.5,2.9{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.3,2.7{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.7,2.3{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5.5,2.1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 202, $y + 413) ; QUIT
Send('{F1}') ; EXE
