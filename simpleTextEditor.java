import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import  java.io.*;
import java.util.logging.SimpleFormatter;

public class simpleTextEditor implements ActionListener {
    // created jframe after importing javax & created objects
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;// jMenuBar name is defined by me, can be anything
    //JMenu Bar items
    JMenu file;
    JMenu edit;
    JMenu close;
    // JMenuItem for file
    JMenuItem newfile;
    JMenuItem openfile;
    JMenuItem savefile;
    JMenuItem printfile;    // or JMenuItem newfile, openfile, savefile, printfile

    // JMenuItem for edit
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;

    // JMenuItem for close
    JMenuItem closeEditor;



    //creating constructor / instantiating the objects
    simpleTextEditor()
    {
        //creating the frame and initializing the welcome text as well as the position
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(100,0,800,1000);
        jTextArea = new JTextArea("Welcome Text Area"); // by default statement, now we just have created this textarea, after that need to
                                                        // add on the frame
        frame.add(jTextArea);

        // addition on JMenuBar
        jMenuBar = new JMenuBar();    // all things on left are defined by me     Right side by default keyword
        file = new JMenu("File");
        edit = new JMenu("Edit");
        close = new JMenu("Close");
        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(close);

        //Addition of JMenuItem on file - Initializing
        newfile = new JMenuItem("New");    //newfile is added as New          // 156
        newfile.addActionListener(this);  // providing action to newfile --> PA

        openfile = new JMenuItem("Open");
        openfile.addActionListener(this);   // PA

        savefile = new JMenuItem("Save");
        savefile.addActionListener(this);   // PA

        printfile = new JMenuItem("Print");
        printfile.addActionListener(this);   // PA

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);

        //Addition of JMenuItem on edit
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);   // PA   //123

        copy = new JMenuItem("Copy");
        copy.addActionListener(this);   // PA

        paste = new JMenuItem("Paste");
        paste.addActionListener(this);    // PA   //321

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        //Addition of JMenuItem on close
        closeEditor = new JMenuItem("Close");      // 454
        closeEditor.addActionListener(this);    // PA

        close.add(closeEditor);

        //adding the JMenu Bar to the frame
        frame.setJMenuBar(jMenuBar);

        frame.setDefaultCloseOperation((WindowConstants.EXIT_ON_CLOSE));   // whenever red button clicked, program execution is stopped
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        simpleTextEditor editor = new simpleTextEditor();   // created object to call the simpleTextEditor class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();  // command - Save,copy,cut,print etc
        // For edit below
        if(s.equals("Copy"))
        {
            jTextArea.copy();
        } else if (s.equals("Cut")){
            jTextArea.cut();     //123
        } else if (s.equals("Paste")){
            jTextArea.paste();   //321
        } else if (s.equals("Print")) {      // since printer is not there, we threw a message
            try{
                jTextArea.print();
            } catch (PrinterException ex){
                throw new RuntimeException(ex);
            }
        } else if (s.equals("New")){       // 156
            jTextArea.setText("");
        } else if(s.equals("Close")){     // 454
            frame.setVisible(false);       // or System.exit(1);
        } else if(s.equals("Open")){
            JFileChooser jfc = new JFileChooser("C");    // choosing file from system
            int ans = jfc.showOpenDialog(null);
            // or if(ans==0){
            if(ans==JFileChooser.APPROVE_OPTION){      // when you open dialog box it shows open and close below -- > every dialog box
                File file = new File(jfc.getSelectedFile().getAbsolutePath());   // approve means right to open the file, this will store will store the path of file
                String s1="", s2="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));  // buffereader is used to read the file
                    s2 = bufferedReader.readLine();   // s2 is going to read the file, it just reads the first line
                    while((s1=bufferedReader.readLine())!=null)   // hence used while loop to read the all the lines to read multiple lines and storing in s2
                    {
                        s2+=s1+"\n";   // storing line in s2    used to += to save all the lines in s2;
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if(s.equals("Save")){
            JFileChooser jFileChooser = new JFileChooser("C");    // choosing the file, which file i want to save
            int ans = jFileChooser.showSaveDialog(null);     // showOpenDialog
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());   // to get the path same as Buffer reader
                BufferedWriter writer = null;      // Bufferwriter to save the file
                try {
                    // Initializing over the file i have chosen (151)
                    writer  = new BufferedWriter(new FileWriter(file, false));  // until we are not stopping to write we don't need to stop - false
                    // false - by default we are not writing anything, whatever we have to write it will be written on jTextArea
                    writer.write((jTextArea.getText()));    // to write we need to get the text of existing file
                    writer.flush();     // flushing the content of existing file
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);     // we may surround by null path hence try and catch
                }
            }
        }
    }
}

