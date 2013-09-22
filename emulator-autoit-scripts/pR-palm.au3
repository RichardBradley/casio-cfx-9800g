Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('palm{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"PALM"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F3}{F1}') ; ViewWindow
Send('2,14,100,2,8,100{ENTER}')
Send('6')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('2,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('14,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-0.1')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
Send('B')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F6}') ; more (state is ["PRGM", "more"])
Send('{F3}') ; REL (state is ["PRGM", "more", "REL"])
Send('{F5}') ; >= (state is ["PRGM", "more", "REL"])
Send('4')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('2,3.9:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5,3.9:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send(':')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('2,3.8:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('3,3.8:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send(':')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('8,3.9:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('14,3.9:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send(':')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('11,3.8:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('10,3.8:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('2.7')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A:2.5')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B:1')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('W{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('B{+}1(6/62)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('K{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('B{+}(6/62)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A{+}(3/31)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F4}') ; Isz  (state is ["PRGM", "JUMP"])
Send('K{ENTER}')
Send('K<4{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('A{+}(3/31)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('B{+}(6/62)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A{+}2(3/31)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A{ENTER}')
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B{ENTER}')
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('8')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('8.6641-B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; sqrt (state is PRGM -> JUMP)
Send('(3.664-C{^}2)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
MouseClick('left', $x + 73, $y + 413) ; ALPHA (state is PRGM -> JUMP)
MouseClick('left', $x + 117, $y + 413) ; rho (state is PRGM -> JUMP)
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(8.75-')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 117, $y + 413) ; rho
Send('),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(8.75{+}')
MouseClick('left', $x + 73, $y + 413) ; ALPHA
MouseClick('left', $x + 117, $y + 413) ; rho
Send('),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('B-(3/31)')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B{ENTER}')
Send('B>6.75')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('3{ENTER}')
Send('0')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('W{ENTER}')
Send('2.38')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('A:6.74')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C:6.94')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('D:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":3.82')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:3.91')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.94')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('2.57,7.03{ENTER}')
Send('2.57')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('A:7.13')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B:2.77')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C:7.13')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('D:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.57')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.23')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:3.05')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.77')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.32')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.41')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":3.24')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.11')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":3.72')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.61')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:2.57')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.61')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.86')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.71')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:3.44')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.71')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.86')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:2.38')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.42')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:2.29')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.42')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":2.29')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.32')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.23')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.49')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.59')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:4.49')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.03')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":C')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.94')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.68')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":C')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.84')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:6.74')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.35')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:6.65')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.55')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.3')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:6.84')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.2')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:6.74')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.03')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.11')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:6.94')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":4.01')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.03')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:A')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:7.32')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":3.05')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.42')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:4.2')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":3.34')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:7.23')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:3.91')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')
Send('5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('W:7.65')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.87')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:10.14')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.87')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":9.95')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.68')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:9.18')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.68')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.89')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.68')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:7.74')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.68')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.51')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:9.37')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":9.09')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.29')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:9.66')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.29')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.22')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:8.03')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.41')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:5.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.8')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:5.1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:4.9')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.61')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:4.9')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":9.18')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:9.76')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":9.37')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:4.71')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.99')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:4.71')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.90')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:4.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.51')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:4.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":9.09')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:4.32')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.7')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:4.32')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS":8.89')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('A:4.13')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:8.99')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"PROGS"{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> CTL)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> CTL)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('progs{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('W=0')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
Send('W=1{F3}') ; => (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('C,D{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('2{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('C,D{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F2}') ; Goto  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('1{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(A{+}(3/31)),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('(A{+}4(3/31)),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('5{ENTER}')
Send('B{+}(6/62)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B:C{+}1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('5(3/31){+}A,B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('C')
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
Send('5{ENTER}')
Send('B{+}(6/62)')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> JUMP)
Send('B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A{+}(3/31),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('A{+}4(3/31),B{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F1}') ; Lbl  (state is ["PRGM", "JUMP"])
Send('9{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> JUMP)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> JUMP)
Send('{F1}') ; EXE