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
require 'set'

class AutoItTranslator

  def initialize
    # 'text' holds the plain text (for "Send") so far this line (if any).
    @text = ''

    # 'out' holds the AutoIt source code so far
    @out = ''

    # 'menu' holds the current menu state in the programme text editor
    @menu = []

    # 'referenced_filenames' holds the filenames of other programs referenced by this
    # program
    @referenced_filenames = Set.new

    # The set of filenames in the calc which have been used. This is needed
    # to detect name collisions and to avoid re-entering the same prog twice.
    @used_calc_prog_names = Set.new
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
          when '^' then [159,413]
          when 'EXIT' then [202,413]
          when 'ln' then [159,451]
          when '->' then [290,487]
          when 'fraction' then [73,487]
          when '4' then [78,578]
          else raise "Bad button_name '#{button_name}'"
          end
    @out << "MouseClick('left', $x + #{x}, $y + #{y}) ; #{comment}"
    @out << " (state is #{@menu.join ' -> '})" unless @menu.empty?
    @out << "\n"
  end

  def enter_menu menu_spec
    menu_spec = menu_spec.split(/ -> /)
    # It is unreliable to short-cut menu navigation 
    # (i.e. to get from A -> B to A -> C by pressing EXIT then C, rather than starting again
    # at A), but we can at least skip navigation if we're already in place
    return if menu_spec == @menu

    # move down the hierarchy to the new menu.
    # we assume that the inital menu is always reachable and that it discards the current
    # menu state (e.g. pressing "PRGM" will get you into the PRGM menu even if you're currently
    # inside the OPTN menu)
    @menu.clear
    menu_spec.each do |menu|
      @menu.push menu
      # Each case statement in this switch describes the last action in the chain
      case @menu.join ' -> '
      when 'PRGM'
        click 'SHIFT'
        click 'VARS', 'PRGM'
      when 'PRGM -> CTL'
        @text << '{F2}'
        flush_text 'CTL'
      when 'PRGM -> JUMP'
        @text << '{F3}'
        flush_text 'JUMP'
      when 'PRGM -> more'
        @text << '{F6}'
        flush_text 'more'
      when 'PRGM -> more -> REL'
        @text << '{F3}'
        flush_text 'REL'
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
      when 'CATALOG'
        click 'SHIFT'
        click '4', 'CATALOG'
      else
        raise "Bad menu '#{@menu.join ' -> '}'"
      end
    end
  end

  # Converts the given txt line into au3
  def translate_line line
    return if line =~ %r{^//}
    line.scan /Prog "[^"]+"|Lbl |Goto |=\>|Isz |Dsz |e\^|Deg|Range |Int |Frac |Plot |Line|Ran#|-\>|\<=|\>=|!=|Graph Y(?:\<|\>|=|\>=|\<=)|Cls|./ do |token|
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
      when 'Line'
        enter_menu ''
        click 'SHIFT'
        @text << '{F4}{F6}{F2}{F1}'
        flush_text 'Plot'
      when '!=', '<=', '>='
        enter_menu 'PRGM -> more -> REL'
        key = case token
              when '!=' then 2
              when '>=' then 5
              when '<=' then 6
              else raise "invalid token '#{token}'"
              end
        @text << "{F#{key}}"
        flush_text token
      when 'Graph Y=', 'Graph Y<=', 'Graph Y<', 'Graph Y>=', 'Graph Y>'
        enter_menu 'CATALOG'
        @text << 'G'
        idx = case token
              when 'Graph Y=' then 10
              when 'Graph Y<' then 11
              when 'Graph Y>' then 12
              when 'Graph Y<=' then 13
              when 'Graph Y>=' then 14
              else raise "invalid token '#{token}'"
              end
        @text << ("{DOWN}" * idx) << '{ENTER}'
        flush_text token
      when 'r'
        click 'ALPHA'
        click 'x^2', 'rho'
      when 't'
        click 'ALPHA'
        click '^', 'theta'
      when 'e^'
        click 'SHIFT'
        click 'ln', 'e^x'
      when '!'
        enter_menu 'OPTN -> more -> PROB'
        @text << '{F1}'
        flush_text 'x!'
      when 'Ran#'
        enter_menu 'OPTN -> more -> PROB'
        @text << '{F4}'
        flush_text 'Ran#'
      when '|'
        click 'fraction'
      when '->'
        click '->'
      when /Prog "([^"]+)"/
        enter_menu 'PRGM -> CTL'
        @text << "{F1}"
        flush_text 'Prog'

        @referenced_filenames << $1
        prog_name = filename_to_calc_prog_name($1)
        @text << "\"#{prog_name}\""
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

  def add_global_preamble
    @out << <<END
Run('fx-9860g-emulator/NewGraph.exe', 'fx-9860g-emulator')
WinWait('NewGraph', '', 1)
WinActivate('NewGraph')
$pos = WinGetPos('[ACTIVE]')
$x = $pos[0]
$y = $pos[1]

Send('B') ; open program menu
END
  end

  def add_preamble_for_prog prog_name
    unless prog_name =~ /[A-Za-z]{1,8}/
      raise "Program name invalid: '#{prog_name}'"
    end

    unless @used_calc_prog_names.add? prog_name
      raise "Program name already used: '#{prog_name}'"
    end

    @out << <<END
Send('{F3}') ; start new prog
; Send the program name in lowercase to avoid a strange ALPHA-LOCK behaviour
Send('#{prog_name.downcase}{ENTER}') ; file name - at most 8 chars

; program text starts here

END
  end

  def add_postamble_for_prog
    flush_text
    @out << <<END

; end program text

END
    click 'SHIFT'
    click 'EXIT', 'QUIT'
    @menu.clear
  end

  def add_global_postamble main_prog_name
    idx = @used_calc_prog_names.sort.find_index main_prog_name
    idx.times do
      @test << '{DOWN}'
    end
    @text << '{F1}'
    flush_text 'EXE'
  end

  def filename_to_calc_prog_name filename
    filename[/([A-Z0-9]*).txt$/i, 1].upcase
  end

  # returns the au3 text as a string
  def translate_file infilename, outfilename
    unless infilename =~ /\.txt$/
      raise "Expecting an input filename ending in '.txt', got '#{filename}'"
    end 
    
    @in_dir = File.dirname(infilename)
    @referenced_filenames << File.basename(infilename)
    translated_filenames = Set.new

    add_global_preamble
    
    while next_filename = (@referenced_filenames - translated_filenames).first
      begin
        translated_filenames << next_filename
        prog_name = filename_to_calc_prog_name next_filename
        
        add_preamble_for_prog prog_name
        File.open(File.join(@in_dir, next_filename), 'r') do |f|
          f.each do |line|
            translate_line line
          end
        end
        add_postamble_for_prog
      rescue => e
        raise e, "#{e} for file '#{next_filename}'", e.backtrace
      end
    end

    add_global_postamble filename_to_calc_prog_name(File.basename(infilename))

    File.open(outfilename, 'w') do |f|
      f << @out
    end
  end
end

# Entry point:

raise "Expecting two arguments: #{$0} <input-prog.txt> <output-script.au3>" \
  unless ARGV.length == 2

tr = AutoItTranslator.new
tr.translate_file ARGV[0], ARGV[1]
