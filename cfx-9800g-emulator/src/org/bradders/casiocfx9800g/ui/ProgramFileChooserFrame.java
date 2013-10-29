package org.bradders.casiocfx9800g.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class ProgramFileChooserFrame extends JDialog
{
   private JTextPane programContentPane;

   private URL selectedFile;

   private Style programStyle;

   /**
    * Style constant for monospaced black.
    */
   static final String ST_REG = "regular";

   public static URL chooseProgramFile(Frame frame)
   {
      ProgramFileChooserFrame pfChooser = new ProgramFileChooserFrame(frame);
      pfChooser.setVisible(true);
      return pfChooser.selectedFile;
   }

   public ProgramFileChooserFrame(Frame frame)
   {
      super(frame, ModalityType.APPLICATION_MODAL);

      programContentPane = new JTextPane();
      programContentPane.setEditable(false);
      programContentPane.setPreferredSize(new Dimension(900, 700));
      programContentPane.setBorder(BorderFactory.createEtchedBorder());
      initStyles(programContentPane);

      JList fileList = new JList(getProgramFileList());
      fileList.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent evt)
         {
            onListItemSelected(evt);
         }
      });
      fileList.setSelectedIndex(0);

      Container c = getContentPane();
      BorderLayout borderLayout = new BorderLayout();
      c.setLayout(borderLayout);
      c.add(fileList, BorderLayout.WEST);
      c.add(programContentPane, BorderLayout.CENTER);
      JPanel bottomPanel = new JPanel();
      c.add(bottomPanel, BorderLayout.SOUTH);
      bottomPanel.add(new JButton(new OkAction()));
      bottomPanel.add(new JButton(new CancelAction()));
      pack();
   }

   private void initStyles(JTextPane text)
   {
      Style def = StyleContext.getDefaultStyleContext().getStyle(
            StyleContext.DEFAULT_STYLE);

      programStyle = text.addStyle(ST_REG, def);
      StyleConstants.setFontFamily(def, "Monospaced");
   }

   protected void onListItemSelected(ListSelectionEvent evt)
   {
      JList fileList = (JList) evt.getSource();

      selectedFile = ((ProgramFile) fileList.getSelectedValue()).url;
      System.err.println("selectedFile="+selectedFile);
      try {
         String fileContents = IOUtils.toString(selectedFile, Charsets.UTF_8);
         EditorKit editorKit = programContentPane.getEditorKit();
         Document document = editorKit.createDefaultDocument();
         document.insertString(0, fileContents, programStyle);
         programContentPane.setDocument(document);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private Object[] getProgramFileList()
   {
      Class<?> clazz = getClass();
      URL classUrl = clazz.getResource(clazz.getSimpleName() + ".class");
      ArrayList<ProgramFile> ret = new ArrayList<ProgramFile>();

      if ("jar".equals(classUrl.getProtocol())) {
         String indexFile;
         try {
            indexFile = IOUtils.toString(clazz.getResource("/META-INF/INDEX.LIST"));
         } catch (IOException e) {
            throw new RuntimeException(
                  "The JAR file must be compiled with a /META-INF/INDEX.LIST index",
                  e);
         }

         Matcher m = Pattern.compile("^[^/\\n]+\\.txt$", Pattern.MULTILINE)
            .matcher(indexFile);
         while (m.find()) {
            ret.add(new ProgramFile(clazz.getResource("/" + m.group())));
         }
      } else if ("file".equals(classUrl.getProtocol())) {
         try {
            URL rootUrl = clazz.getResource("/");
            File rootFile = new File(rootUrl.toURI());
            final Pattern fileNamePatt = Pattern.compile("[^/]+\\.txt");
            File[] programFiles = rootFile.listFiles(new FilenameFilter() {
               @Override
               public boolean accept(File dir, String name)
               {
                  return fileNamePatt.matcher(name).matches();
               }
            });
            for (File programFile : programFiles) {
               ret.add(new ProgramFile(programFile.toURI().toURL()));
            }
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      // TODO: how to import external files into a subdir of the JAR...

      return ret.toArray();
   }

   private class OkAction extends AbstractAction
   {
      @Override
      public Object getValue(String key)
      {
         if (Action.NAME.equals(key)) {
            return "OK";
         }
         return super.getValue(key);
      }

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         ProgramFileChooserFrame.this.dispose();
      }
   }

   private class CancelAction extends AbstractAction
   {
      @Override
      public Object getValue(String key)
      {
         if (Action.NAME.equals(key)) {
            return "Cancel";
         }
         return super.getValue(key);
      }

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         selectedFile = null;
         ProgramFileChooserFrame.this.dispose();
      }
   }

   private class ProgramFile
   {
      URL url;

      public ProgramFile(URL url)
      {
         if (url == null) {
            throw new NullPointerException();
         }
         this.url = url;
      }

      @Override
      public String toString()
      {
         return new File(url.getPath()).getName();
      }
   }
}
