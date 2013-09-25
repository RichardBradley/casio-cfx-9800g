package org.bradders.casiocfx9800g.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.math.BigDecimal;

import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.bradders.casiocfx9800g.CompiledFile;
import org.bradders.casiocfx9800g.Compiler;
import org.bradders.casiocfx9800g.RuntimeContext;
import org.bradders.casiocfx9800g.StatementRunner;

public class AppletUserInterface extends JApplet implements ActionListener, UserInterface
{
   private JTextField inputField;
   private JTextPane transcript;
   private Compiler compiler;
   private RuntimeContext context;
   private StatementRunner runner;
   private GraphImageJLabel graph;

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
      // compiler.setBaseDir("qq");
      context = new RuntimeContext();
      runner = new StatementRunner(context, compiler, this);
   }

   protected void initUI()
   {
      // TODO: can get in/out in single pane, like Eclipse does for console?
      setPreferredSize(new Dimension(450,350));
      
      inputField = new JTextField();
      inputField.addActionListener(this);
      inputField.setFont(new Font("Monospaced", Font.PLAIN, 12));
      
      transcript = new JTextPane();
      transcript.setEditable(false);
      
      JScrollPane transScr = new JScrollPane(transcript,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      initStyles();
      
      graph = new GraphImageJLabel();
      
      //setJMenuBar(new MiffsMenus(this).generate());
      
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

   @Override
   public void actionPerformed(ActionEvent evt)
   {
      if (evt.getSource() == inputField) {
         String input = inputField.getText();

         if ((input == null) || input.equals(""))
           return;

         printLine(input, ST_ECHO_INPUT);
         respond(input);
         inputField.selectAll();
         inputField.requestFocus();
       }
   }
   
   protected void printLine(Object o, String style)
   {
     String text = o.toString() + "\n";
     Document doc = transcript.getDocument();

     try {
       doc.insertString(doc.getLength(), text, transcript.getStyle(style));
     } catch (Exception e) {
       e.printStackTrace();
     }

     transcript.setCaretPosition(doc.getLength());
   }
   
   public void respond(String input)
   {
     try {
        CompiledFile compiledInput = compiler.compile(new StringReader(input));
        runner.run(compiledInput);
     } catch (Exception e) {
       printLine(e, ST_ERR);
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
   }

   @Override
   public BigDecimal readValue()
   {
      throw new RuntimeException("// TODO Auto-generated method stub");
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
}
