package org.bradders.casiocfx9800g.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.bradders.casiocfx9800g.CompiledFile;
import org.bradders.casiocfx9800g.Compiler;
import org.bradders.casiocfx9800g.Evaluator;
import org.bradders.casiocfx9800g.RuntimeContext;
import org.bradders.casiocfx9800g.StatementRunner;

/**
 * Runs the CFX emulator in an Applet.
 * 
 * The emulator is run on its own thread.
 * 
 * TODO: use swing.plaf?
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
      mnuFile.add(new InterruptAction());
      
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
            
            // for program files, print "(terminated)"; for input expressions,
            // print the return value
            if (program.getFile() != null) {
               printLine("(terminated)", ST_RESULT);
            } else {
               if (retVal != null) {
                  printLine(Evaluator.formatForDisplay(retVal), ST_RESULT);
               }
            }
         } catch (InterruptedException e) {
            // resume loop
            printLine("(cancelled)", ST_RESULT);
         } catch (Exception e) {
            if (e.getCause() instanceof InterruptedException) {
               printLine("(cancelled)", ST_RESULT);
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
               JOptionPane.showMessageDialog(
                     AppletUserInterface.this,
                     "Cannot start a new program while one is running. Please choose 'interrupt' from the menu.",
                     "cfx-9800g-emulator",
                     JOptionPane.WARNING_MESSAGE);
               return; // disallow open file while emulator is running
            }
            
            try {
               Frame frame = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, AppletUserInterface.this);
               URL selectedFile = ProgramFileChooserFrame.chooseProgramFile(frame);

               if (selectedFile != null) {
                  printLine("Open file: " + compiler.getFilename(selectedFile), ST_ECHO_INPUT);
                  
                  compiler.setBaseDir(selectedFile);
                  inputtedProgram = compiler.loadFile(selectedFile);
                  programReady.signal();
               }
            } catch (Exception e) {
               e.printStackTrace();
               printLine(e, ST_ERR);
            }
         } finally {
            emulatorLock.unlock();
         }
      }
   }
   
   private class InterruptAction extends AbstractAction
   {
      @Override
      public Object getValue(String key)
      {
         if (Action.NAME.equals(key)) {
            return "Interrupt";
         }
         return super.getValue(key);
      }
      
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         emulatorThread.interrupt();
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

   protected void printLine(final Object o, final String styleName)
   {
      if (!SwingUtilities.isEventDispatchThread()) {
         try {
            SwingUtilities.invokeAndWait(new Runnable() {
               @Override
               public void run()
               {
                  printLine(o, styleName);
               }
            });
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         try {
            String text = o.toString() + "\n";
            StyledDocument doc = (StyledDocument) transcript.getDocument();

            Style style = transcript.getStyle(styleName);
            doc.setLogicalStyle(doc.getLength(), style);
            doc.insertString(doc.getLength(), text, style);

            transcript.setCaretPosition(doc.getLength());
         } catch (Exception e) {
            e.printStackTrace();
         }
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
      if (Thread.currentThread() != emulatorThread) {
         throw new RuntimeException("Only the emulator thread should call this method");
      }
      
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
      if (Thread.currentThread() != emulatorThread) {
         throw new RuntimeException("Only the emulator thread should call this method");
      }

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
