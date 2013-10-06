Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('swiss{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"SWISS"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F3}{F1}') ; ViewWindow
Send('1,3.01,100,1,2,100{ENTER}')
Send('1.1')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.9{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.8{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A{+}0.015')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('A<1.9')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
Send('1.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.8{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.6{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A{+}0.015')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('A=1.4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('1.6')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('A')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('1.9')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('1.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.6{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.4{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A{+}0.01')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('A=1.2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('1.8')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('A<1.9{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('1.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.4{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A{+}0.015')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('A=1.4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('1.6')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('A')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('1.9')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('4{ENTER}')
Send('1.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,1.1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A{+}0.015')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A{ENTER}')
Send('A')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F6}') ; <= (state is ["PRGM", "more", "REL"])
Send('1.9')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 202, $y + 413) ; QUIT
Send('{F1}') ; EXE