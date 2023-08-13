import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;

    //File Menu Items:-
    JMenuItem newFile, openFile, saveFile;

    //Edit Menu Items:-
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor() {
        frame = new JFrame(); // Initialise the frame

        menuBar =  new JMenuBar();//Initialise the menuBar

        textArea = new JTextArea();//Initialise the textArea

        file = new JMenu("File");// Initialise both File and Edit on the MenuBar
        edit = new JMenu("Edit");

        //Initialising the File menu Items:-
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Adding File Menu Item to ActionListener:-
        newFile.addActionListener(this);//this keyword means the curr obj of TextEditor
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding File menu items to the File Menu:-
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialising the Edit menu Items:-
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Adding Edit Menu Item to ActionListener:-
        cut.addActionListener(this);//this keyword means the curr obj of TextEditor
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding Edit menu items to the Edit Menu:-
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);// add both Menu's on the Menu bar
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);

        //Set Dimensions of Frame.
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //For Edit menu Options:-
        if(e.getSource()==cut) {
            //Perform Cut Operation
            textArea.cut();
        }
        if(e.getSource()==copy) {
            //Perform Copy Operation
            textArea.copy();
        }
        if(e.getSource()==paste) {
            //Perform Paste Operation
            textArea.paste();
        }
        if(e.getSource()==selectAll) {
            //Perform SelectAll Operation
            textArea.selectAll();
        }
        if(e.getSource()==close) {
            //Perform Close Operation
            System.exit(0);// 0:- to close the app
        }

        //For File Menu Options:-
        if(e.getSource()==newFile) {
            TextEditor textEditor = new TextEditor();
        }
        if(e.getSource()==openFile) {
            //Open a File Chooser
            JFileChooser fileChooser = new JFileChooser();
            int chooseOption = fileChooser.showOpenDialog(null);//opens the open file dialog

            //if we have clicked open file:-
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                // getting a selected file
                File file = fileChooser.getSelectedFile();
                //get the path of the file:-
                String filePath = file.getPath();

                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = "", output = "";
                    //Read the contents of the file line by line:
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //Set the Output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        }

        if(e.getSource()==saveFile) {
            //initialise the file picker
            JFileChooser fileChooser = new JFileChooser();
            int chooseOption = fileChooser.showSaveDialog(null); //opens the save file dialog
            //check if we clicked save button
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //create a new file in directed path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialise file writer:-
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialise Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of tex area to file:-
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }


}