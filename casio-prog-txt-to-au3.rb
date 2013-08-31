#! ruby
#
# This app converts transcribed Casio CFX-9800G programs from text form
# into an AutoIt script which will type in the program text and then
# run it.
#
# It expects one argument, the input text file name.
#
# The output is written to the file of the same name, but with an ".au3"
# extension. The script will overwrite files without prompting.

class AutoItTranslator

  def initialize
    # 'text' holds the plain text (for "Send") so far this line (if any).
    @text = ''

    # 'out' holds the AutoIt source code so far
    @out = ''

    # 'menu' holds the current menu state in the programme text editor
    @menu = []
  end

  # Before starting a key sequence (e.g. for a keyword like Goto),
  # or at the end of the current line, we write all the plain text
  # we have so far using 'Send'
  def flush_text comment = ''
    if !@text.empty?
      @out << "Send('#{@text}')"
      unless comment.empty?
        @out << ' ; ' << comment
        @out << " (state is #{@menu})" unless @menu.empty?
      end
      @out << "\n"
    elsif !comment.empty?
      raise "Comment '#{comment}' given, but no text to flush"
    end
    @text = ''
  end

  def click button_name, comment = button_name
    flush_text
    x,y = case button_name
          when 'SHIFT' then [73,376]
          when 'OPTN' then [117,376]
          when 'VARS' then [159,376]
          when 'ALPHA' then [73,413]
          when 'x^2' then [117,413]
          when 'EXIT' then [202,413]
          when 'ln' then [159,451]
          when '->' then [290,487]
          else raise "Bad button_name '#{button_name}'"
          end
    @out << "MouseClick('left', $x + #{x}, $y + #{y}) ; #{comment}"
    @out << " (state is #{@menu.join ' -> '})" unless @menu.empty?
    @out << "\n"
  end

  def enter_menu menu_spec
    curr_spec = @menu.dup
    menu_spec = menu_spec.split(/ -> /)
    # remove any common ancestors
    while !curr_spec.empty? && !menu_spec.empty? && curr_spec.first == menu_spec.first
      curr_spec.shift
      menu_spec.shift
    end
    # move up the hierarchy to the common ancestor
    curr_spec.each do |menu|
      @menu.pop
      click 'EXIT' unless menu == 'more'
    end
    # move down the hierarchy to the new menu
    menu_spec.each do |menu|
      @menu.push menu
      # Each case statement in this switch describes the last action in the chain
      case @menu.join ' -> '
      when 'PRGM'
        click 'SHIFT'
        click 'VARS', 'PRGM'
      when 'PRGM -> JUMP'
        @text << '{F3}'
        flush_text 'JUMP'
      when 'OPTN'
        click 'OPTN'
      when 'OPTN -> more'
        @text << '{F6}'
        flush_text 'more'
      when 'OPTN -> more -> PROB'
        @text << '{F3}'
        flush_text 'PROB'
      when 'OPTN -> more -> NUM'
        @text << '{F4}'
        flush_text 'NUM'
      else
        raise "Bad menu '#{@menu.join ' -> '}'"
      end
    end
  end

  # Converts the given txt line into au3
  def translate_line line
    return if line =~ /^#/
    line.scan /Lbl |Goto |=>|Isz |Dsz |e\^|Deg|Range |Int |Plot |->|./ do |token|
      case token
      when '#'
        enter_menu 'PRGM'
        @text << "{F5}"
        flush_text 'print result'
      when 'Lbl ', 'Goto ', '=>', 'Isz ', 'Dsz '
        enter_menu 'PRGM -> JUMP'
        key = case token
              when 'Lbl ' then 1
              when 'Goto ' then 2
              when '=>' then 3
              when 'Isz ' then 4
              when 'Dsz ' then 5
              else raise "invalid token '#{token}'"
              end
        @text << "{F#{key}}"
        flush_text token
      when 'Abs ', 'Int ', 'Frac ', 'Rnd ', 'Intg ', 'RndFi '
        enter_menu 'OPTN -> more -> NUM'
        key = case token
              when 'Abs ' then 1
              when 'Int ' then 2
              when 'Frac ' then 3
              when 'Rnd ' then 4
              when 'Intg ' then 5
              when 'RndFi ' then 5
              else raise "invalid token '#{token}'"
              end
        @text << "{F#{key}}"
        flush_text token
      when 'Range '
        click 'SHIFT'
        @text << '{F3}{F1}'
        flush_text 'ViewWindow'
      when 'Plot '
        enter_menu ''
        click 'SHIFT'
        @text << '{F4}{F6}{F1}{F1}'
        flush_text 'Plot'
      when 'r'
        click 'ALPHA'
        click 'x^2', 'rho'
      when 'e^'
        click 'SHIFT'
        click 'ln', 'e^x'
      when '!'
        enter_menu 'OPTN -> more -> PROB'
        @text << '{F1}'
        flush_text 'x!'
      when '->'
        click '->'
      when /^[+^]$/
        @text << "{#{token}}"
      when /^[-"A-Z0-9?:\/()*= ,.<>]$/
        @text << token
      else
        raise "invalid token '#{token}' on line '#{line}'"
      end
    end
    # end of the line: 
    # if the line did not end with "#" then a newline is needed
    unless line =~ /\#$/
      @text << '{ENTER}'
    end
    flush_text
  end

  def add_preamble prog_name
    unless prog_name =~ /[A-Za-z]{1,8}/
      raise "Program name invalid: '#{prog_name}'"
    end

    @out << <<END
Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
Send('{F3}') ; start new prog
Send('#{prog_name}{ENTER}') ; file name - at most 8 chars

; program text starts here

END
  end

  def add_postamble
    enter_menu ''
    @out << <<END

; end program text

MouseClick('left', $x + 202, $y + 413) ; EXIT
Send('{F1}') ; EXE
END
  end

  # returns the au3 text as a string
  def translate_file filename
    unless filename =~ /\.txt$/
      raise "Expecting a filename ending in '.txt', got '#{filename}'"
    end 
     
    prog_name = filename.sub(/^(p.-)?(.*)\.txt$/, '\\2').upcase
    out_filename = filename.sub(/\.txt$/, '.au3')

    add_preamble prog_name

    File.open(filename, 'r') do |f|
      f.each do |line|
        translate_line line
      end
    end

    add_postamble

    File.open(out_filename, 'w') do |f|
      f << @out
    end
  end
end

# Entry point:

raise "Expecting one argument 'prog.txt'" \
  unless ARGV.length == 1

tr = AutoItTranslator.new
tr.translate_file ARGV.first
