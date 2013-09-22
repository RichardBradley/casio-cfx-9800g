Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('cccp{ENTER}') ; file name - at most 8 chars

; program text starts here

Send('"CCCP"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F3}{F1}') ; ViewWindow
Send('0.97,2.097,10,1,1.62,10{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is SKTCH)
Send('{F4}') ; SKTCH (state is ["SKTCH"])
Send('{F5}') ; GRPH (state is ["SKTCH", "GRPH"])
Send('{F6}') ; more (state is ["SKTCH", "GRPH", "more"])
Send('{F2}') ; Graph Y< (state is ["SKTCH", "GRPH", "more"])
Send('2{ENTER}')
Send('1')
MouseClick('left', $x + 290, $y + 487) ; -> (state is SKTCH -> GRPH -> more)
Send('A{ENTER}')
Send('1.18')
MouseClick('left', $x + 290, $y + 487) ; -> (state is SKTCH -> GRPH -> more)
Send('B:1.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is SKTCH -> GRPH -> more)
Send('C:1.09')
MouseClick('left', $x + 290, $y + 487) ; -> (state is SKTCH -> GRPH -> more)
Send('D:1.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is SKTCH -> GRPH -> more)
Send('E:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.17')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.08')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.18')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.09')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.49')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.08,1.47:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.07,1.47:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.08,1.46{ENTER}')
Send('1.10')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B:1.49')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C:1.11')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('D:1.49')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('E:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.12')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.5')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.01')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.05')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.43')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.4')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.04')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.02')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.39')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.06')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.03,1.44:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.04,1.44{ENTER}')
Send('1.06')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B:1.42')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C:1.12')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('D:1.42')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('E:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.07')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.41')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.10')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.41')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.10')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.43')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.12')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.43')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.14')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.45')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.14')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.46')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.54')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.56')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.15')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.45')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.55')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.16')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.46')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.54')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.17')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:B')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:1.48')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:1.52')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.13,1.56:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.13,1.55:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('1.12,1.56{ENTER}')
Send('1.57')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('C:C')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('E:1.13')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('B:1.1')
MouseClick('left', $x + 290, $y + 487) ; ->
Send('D:')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F2}') ; CTL (state is ["PRGM", "CTL"])
Send('{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')
Send('1.58')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('C:C')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('E:1.11')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('B:1.08')
MouseClick('left', $x + 290, $y + 487) ; -> (state is PRGM -> CTL)
Send('D:{F1}') ; Prog (state is ["PRGM", "CTL"])
Send('"LINE"{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM -> CTL)
MouseClick('left', $x + 202, $y + 413) ; QUIT (state is PRGM -> CTL)
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('line{ENTER}') ; file name - at most 8 chars

; program text starts here

MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('B,C{ENTER}')
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F1}{F1}') ; Plot
Send('D,E{ENTER}')
Send('A=1')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A=2')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')
Send('A=3')
MouseClick('left', $x + 73, $y + 376) ; SHIFT (state is PRGM)
MouseClick('left', $x + 159, $y + 376) ; PRGM (state is PRGM)
Send('{F3}') ; JUMP (state is ["PRGM", "JUMP"])
Send('{F3}') ; => (state is ["PRGM", "JUMP"])
MouseClick('left', $x + 73, $y + 376) ; SHIFT
Send('{F4}{F6}{F2}{F1}') ; Line
Send('{ENTER}')

; end program text

MouseClick('left', $x + 73, $y + 376) ; SHIFT
MouseClick('left', $x + 202, $y + 413) ; QUIT
Send('{F1}') ; EXE
