package org.bradders.casiocfx9800g.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.bradders.casiocfx9800g.CompiledFile;
import org.bradders.casiocfx9800g.Compiler;
import org.bradders.casiocfx9800g.Evaluator;
import org.bradders.casiocfx9800g.RuntimeContext;
import org.bradders.casiocfx9800g.StatementRunner;

/**
 * Runs the CFX emulator in an Applet.
 * 
 * The emulator is run on its own thread.
 */
public class AppletUserInterface extends JApplet implements UserInterface
{
   private JTextField inputField;
   private JTextPane transcript;
   private Compiler compiler;
   private RuntimeContext context;
   private StatementRunner runner;
   private GraphImageJLabel graph;
   
   /**
    * See EmulatorState, onInputFieldEnterPressed() and runEmulator()
    */
   private Lock emulatorLock = new ReentrantLock();
   private Condition inputReady = emulatorLock.newCondition();
   private BigDecimal inputtedValue;
   private Condition programReady = emulatorLock.newCondition();
   private CompiledFile inputtedProgram;
   private Condition continuePressed = emulatorLock.newCondition();
   private EmulatorState emulatorState;
   private Thread emulatorThread;

   /**
    * Style constant for monospaced black.
    */
   static final String ST_REG = "regular";
   /**
    * Style constant for monospaced black right-aligned.
    */
   static final String ST_RESULT = "result";
   /**
    * Style constant for monospaced blue.
    */
   static final String ST_ECHO_INPUT = "reply";
   /**
    * Style constant for monospaced red.
    */
   static final String ST_ERR = "error";

   @Override
   public void init()
   {
      super.init();
      
      initUI();
      initEmulator();
   }

   private void initEmulator()
   {
      compiler = new Compiler();
      context = new RuntimeContext();
      runner = new StatementRunner(context, compiler, this);
      
      emulatorThread = new Thread(new Runnable() {
         @Override
         public void run()
         {
            runEmulator();
         }
      }, "emulator thread");
      
      emulatorThread.start();
   }

   protected void initUI()
   {
      // TODO: can get in/out in single pane, like Eclipse does for console?
      setPreferredSize(new Dimension(450,350));
      
      inputField = new JTextField();
      inputField.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0)
         {
            onInputFieldEnterPressed();
         }
      });
      inputField.setFont(new Font("Monospaced", Font.PLAIN, 12));
      
      transcript = new JTextPane();
      transcript.setEditable(false);
      
      JScrollPane transScr = new JScrollPane(transcript,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      initStyles();
      
      graph = new GraphImageJLabel();
      
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu mnuFile = new JMenu("File");
      menuBar.add(mnuFile);
      mnuFile.add(new OpenFileAction());
      
      Container c = getContentPane();
      c.removeAll();
      c.setLayout(new BorderLayout());
      c.add(inputField, BorderLayout.SOUTH);
      c.add(transScr, BorderLayout.CENTER);
      c.add(graph, BorderLayout.NORTH);

      validate();
      repaint();
      inputField.requestFocus();
   }

   protected void initStyles()
   {
      Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

      Style regular = transcript.addStyle(ST_REG, def);
      StyleConstants.setFontFamily(def, "Monospaced");

      Style result = transcript.addStyle(ST_RESULT, regular);
      StyleConstants.setAlignment(result, StyleConstants.ALIGN_RIGHT);

      Style reply = transcript.addStyle(ST_ECHO_INPUT, regular);
      StyleConstants.setForeground(reply, Color.blue);

      Style error = transcript.addStyle(ST_ERR, regular);
      StyleConstants.setForeground(error, Color.red);
   }
   
   /**
    * Entry point for the emulator thread
    */
   private void runEmulator()
   {
      while (true) {
         try {
            emulatorLock.lockInterruptibly();
            CompiledFile program;
            try {
               emulatorState = EmulatorState.awaitingProgramInput;
               programReady.await();
               program = inputtedProgram;
               emulatorState = EmulatorState.running;
            } finally {
               emulatorLock.unlock();
            }
            BigDecimal retVal = runner.run(program);
            if (retVal != null) {
               printLine(Evaluator.formatForDisplay(retVal), ST_RESULT);
            }
         } catch (InterruptedException e) {
            // resume loop
         } catch (Exception e) {
            if (e.getCause() instanceof InterruptedException) {
               // resume loop
            } else {
               printLine(e, ST_ERR);
            }
         }
      }
   }

   private class OpenFileAction extends AbstractAction
   {
      @Override
      public Object getValue(String key)
      {
         if (Action.NAME.equals(key)) {
            return "Open";
         }
         return super.getValue(key);
      }
      
      @Override
      public void actionPerformed(ActionEvent evt)
      {
         emulatorLock.lock();
         try {
            if (emulatorState != EmulatorState.awaitingProgramInput) {
               return; // disallow open file while emulator is running
               // TODO: pop up a message?
            }
            
            try {
               // TODO: see 'we are an Applet' in Nonog.java
               JFileChooser ch = new JFileChooser(new File("."));
               if (ch.showOpenDialog(AppletUserInterface.this) == JFileChooser.APPROVE_OPTION) {
                  final File selectedFile = ch.getSelectedFile();
                  
                  printLine("Open file: " + selectedFile.getName(), ST_ECHO_INPUT);
                  
                  try {
                     compiler.setBaseDir(selectedFile.getAbsoluteFile().getParentFile());
                     inputtedProgram = compiler.loadFile(selectedFile.getAbsolutePath());
                     programReady.signal();
                  } catch (Exception e) {
                     printLine(e, ST_ERR);
                  }
               }
            } catch (Exception e) {
               printLine(e, ST_ERR);
            }
         } finally {
            emulatorLock.unlock();
         }
      }
   }
   
   private void onInputFieldEnterPressed()
   {
      emulatorLock.lock();
      try {
         if (emulatorState == EmulatorState.running) {
            // ignore the press
         } else if (emulatorState == EmulatorState.awaitingContinuePress) {
            continuePressed.signal();
         } else {
            String input = inputField.getText();
   
            if ((input == null) || input.equals("")) {
              return;
            }
   
            printLine(input, ST_ECHO_INPUT);
   
            if (emulatorState == EmulatorState.awaitingProgramInput) {
               // assume input is a program to be executed
               try {
                  inputtedProgram = compiler.compile(new StringReader(input));
                  programReady.signal();
               } catch (Exception e) {
                  printLine(e, ST_ERR);
               }
            } else if (emulatorState == EmulatorState.awaitingValueInput) {
               try {
                  inputtedValue = new BigDecimal(input, Evaluator.STORED_PRECISION);
                  inputReady.signal();
               } catch (Exception e) {
                  printLine("Bad input, must be number: " + e.getMessage(), ST_ERR);
               }
            } else {
               throw new RuntimeException("Bad emulator state: " + emulatorState);
            }
            
            inputField.selectAll();
            inputField.requestFocus();
         }
      } finally {
         emulatorLock.unlock();
      }
   }

   protected void printLine(final Object o, final String style)
   {
      if (!SwingUtilities.isEventDispatchThread()) {
         try {
            SwingUtilities.invokeAndWait(new Runnable() {
               @Override
               public void run()
               {
                  printLine(o, style);
               }
            });
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         String text = o.toString() + "\n";
         Document doc = transcript.getDocument();

         try {
            doc.insertString(doc.getLength(), text, transcript.getStyle(style));
         } catch (Exception e) {
            e.printStackTrace();
         }

         transcript.setCaretPosition(doc.getLength());
      }
   }
   
   @Override
   public void printLine(String string)
   {
      printLine(string, ST_REG);
   }

   @Override
   public void printResult(String value)
   {
      printLine(value, ST_RESULT);
      printLine("- Disp -", ST_RESULT);
      emulatorLock.lock();
      try {
         emulatorState = EmulatorState.awaitingContinuePress;
         continuePressed.await();
         emulatorState = EmulatorState.running;
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      } finally {
         emulatorLock.unlock();
      }
   }

   @Override
   public BigDecimal readValue()
   {
      printLine("?", ST_ECHO_INPUT);

      emulatorLock.lock();
      try {
         emulatorState = EmulatorState.awaitingValueInput;
         inputReady.await();
         emulatorState = EmulatorState.running;
         return inputtedValue;
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      } finally {
         emulatorLock.unlock();
      }
   }

   @Override
   public void line(CalcColour colour)
   {
      graph.line(colour);
   }

   @Override
   public void range(BigDecimal xMin, BigDecimal xMax, BigDecimal xScale,
         BigDecimal yMin, BigDecimal yMax, BigDecimal yScale)
   {
      graph.range(xMin, xMax, xScale, yMin, yMax, yScale);
   }

   @Override
   public void plot(CalcColour colour, BigDecimal x, BigDecimal y)
   {
      graph.plot(colour, x, y);
   }

   @Override
   public void graphDot(BigDecimal dotX, BigDecimal dotY, GraphShading shading)
   {
      graph.graphDot(dotX, dotY, shading);
   }

   @Override
   public Iterable<BigDecimal> iterateGraphXValues()
   {
      return graph.iterateGraphXValues();
   }

   @Override
   public void clearScreen()
   {
      graph.clearScreen();
   }
   
   private static enum EmulatorState
   {
      /**
       * The emulator is ready for a new program.
       * The thread is blocked on 'programReady' and expects a program in 'inputtedProgram'
       */
      awaitingProgramInput,
      /**
       * The emulator is waiting for a value from the user.
       * The thread is blocked on 'inputReady' and expects a value in 'inputtedValue'
       */
      awaitingValueInput,
      /**
       * A value has been displayed, the user must press enter to continue.
       * The thread is blocked on 'continuePressed'
       */
      awaitingContinuePress,
      /**
       * The emulator is working, user input should not be accepted
       */
      running;
   }
}