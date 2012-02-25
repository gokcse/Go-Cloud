package org.cit.CloudComputing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

//////////////////////////////////////////////////////////////////////////////////
class Frame_class extends Frame
        implements ActionListener, ItemListener, CloudComputable {           // open brace of Frame_class

    MenuItem item1, item2, item3, item4, item5, item6;
    MenuItem item7, item8, item9;
    MenuItem item10;
    MenuItem fontitem1, fontitem2, fontitem3, fontitem4;
    MenuItem sizeitem1, sizeitem2, sizeitem3, sizeitem4, sizeitem5, sizeitem6, sizeitem7,
            sizeitem8, sizeitem9, sizeitem10, sizeitem11, sizeitem12;
    MenuItem styleitem1, styleitem2, styleitem3;
    MenuItem bkcitem[] = new MenuItem[13];
    MenuItem frcitem[] = new MenuItem[13];
    String filename = "", directory = "", findstr = "", fname = "", dir = "";
    JTextArea txtArea;
    JMenuItem Open;
    int fontNumeric = 12;
    TextArea tx1 = new TextArea();
    String fileName = null;
    String copystring;
    int fontStyle = Font.PLAIN;
    String fontName = "Courier";
    FileDialog openDialog;
    FileDialog saveDialog;
    private ErrorDialog errorDialog = null;
    private TEDialog teDialog = null;
    String s;
    static String st = "";
    String heading = " Save File ";
    static int window_no = 0;
    static int window_counter = 0;

    ///////////////////////////////////////////////////////////////////////////////
    Frame_class(String title) {//start of Frame_class constructor

        super(title);

        MenuBar mbar = new MenuBar();

        setMenuBar(mbar);

        menu(mbar);

        AddChoice(mbar);
        bkfrcolor(mbar);
        textarea(this);
        menu1(mbar);

        //create an object to handle window events
        MyWindowAdapter adapter = new MyWindowAdapter(this);
        //register it to receive those event
        addWindowListener(adapter);

        //addKeyListener(this);
        //requestFocus();

        this.AddDialog();
        tx1.setBackground(Color.white);
        tx1.setForeground(Color.black);
        //this.setVisible(true);

    }//end of Frame_class constructor
/////////////////////////////////////////////////////////////////////////////////  

    public String[] listOfServices() {
        String[] services = {"Open", "Save", "Close" };
        return services;
    }

    public String getInfo() {
        return "This is a cool text editor.";
    }

    public String[] listOfAvailableComponents() {
        String[] components = {"textarea"};
        return components;
           
        
    }


   public String openFileService(String filename){
                       try{
                StringBuffer ret=new StringBuffer("");
                String cur_line=new String();
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
                while((cur_line=br.readLine()) != null){
                   ret.append(ret + "\n" + cur_line);
                }
                return ret.toString();
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
   }

    public void menu1(MenuBar mbar) {//start of menu1()
        Menu help = new Menu("  Help  ");
        help.add(item10 = new MenuItem("About.."));
        mbar.add(help);
        item10.addActionListener(this);

    }

    public void menu(MenuBar mbar) {//start of menu()


        Menu file = new Menu("  File  ");

        file.add(item1 = new MenuItem("New"));
        file.add(item2 = new MenuItem("Open"));
        file.add(item3 = new MenuItem("Close"));
        file.add(item4 = new MenuItem("Save"));
        file.add(item5 = new MenuItem("Save As"));
        file.addSeparator();
        file.add(item6 = new MenuItem("Exit"));

        mbar.add(file);

        Menu edit = new Menu("  Edit  ");

        edit.add(item7 = new MenuItem("Cut"));
        edit.add(item8 = new MenuItem("Copy"));
        edit.add(item9 = new MenuItem("Paste"));

        mbar.add(edit);



        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);



    }//end of menu()
    //////////////////////////////////////////////////////////////////////////////

    private void AddChoice(MenuBar mbar) {

        Menu font = new Menu("   Font   ");

        font.add(fontitem1 = new MenuItem("Courier"));
        font.addSeparator();
        font.add(fontitem2 = new MenuItem("Arial"));
        font.addSeparator();
        font.add(fontitem4 = new MenuItem("TimesRoman"));

        mbar.add(font);

        Menu size = new Menu("   Size   ");

        size.add(sizeitem1 = new MenuItem("8"));
        size.addSeparator();

        size.add(sizeitem2 = new MenuItem("10"));
        size.addSeparator();

        size.add(sizeitem3 = new MenuItem("12"));
        size.addSeparator();

        size.add(sizeitem4 = new MenuItem("14"));
        size.addSeparator();

        size.add(sizeitem5 = new MenuItem("16"));
        size.addSeparator();

        size.add(sizeitem6 = new MenuItem("18"));
        size.addSeparator();

        size.add(sizeitem7 = new MenuItem("20"));
        size.addSeparator();

        size.add(sizeitem8 = new MenuItem("22"));
        size.addSeparator();

        size.add(sizeitem9 = new MenuItem("24"));
        size.addSeparator();

        size.add(sizeitem10 = new MenuItem("26"));
        size.addSeparator();

        size.add(sizeitem11 = new MenuItem("28"));
        size.addSeparator();

        size.add(sizeitem12 = new MenuItem("30"));

        mbar.add(size);

        Menu fontstyle = new Menu("   Font Style   ");

        fontstyle.add(styleitem1 = new MenuItem("Bold"));
        fontstyle.addSeparator();
        fontstyle.add(styleitem2 = new MenuItem("Plain"));
        fontstyle.addSeparator();
        fontstyle.add(styleitem3 = new MenuItem("Italic"));


        mbar.add(fontstyle);

        fontitem1.addActionListener(this);
        fontitem2.addActionListener(this);
        //fontitem3.addActionListener(this);
        fontitem4.addActionListener(this);


        sizeitem1.addActionListener(this);
        sizeitem2.addActionListener(this);
        sizeitem3.addActionListener(this);
        sizeitem4.addActionListener(this);
        sizeitem5.addActionListener(this);
        sizeitem6.addActionListener(this);
        sizeitem7.addActionListener(this);
        sizeitem8.addActionListener(this);
        sizeitem9.addActionListener(this);
        sizeitem10.addActionListener(this);
        sizeitem11.addActionListener(this);
        sizeitem12.addActionListener(this);

        styleitem1.addActionListener(this);
        styleitem2.addActionListener(this);
        styleitem3.addActionListener(this);



    }

    private void bkfrcolor(MenuBar mbar) {

        Menu bkcolor = new Menu("   BackGround Color   ");
        Menu frcolor = new Menu("   Text Color   ");


        String colname[] = {"Red", "Blue", "Green", "Cyan", "Black", "Dark Gray",
            "Gray", "Light Gray", "Magenta", "Orange", "Pink", "White", "Yellow"};

        String frcolname[] = {" Red", " Blue", " Green", " Cyan", " Black", " Dark Gray",
            " Gray", " Light Gray", " Magenta", " Orange", " Pink", " White", " Yellow"};

        for (int i = 0; i <= 12; i++) {
            bkcolor.add(bkcitem[i] = new MenuItem(colname[i]));
            bkcitem[i].addActionListener(this);

            frcolor.add(frcitem[i] = new MenuItem(frcolname[i]));
            frcitem[i].addActionListener(this);

        }
        mbar.add(bkcolor);
        mbar.add(frcolor);

    }

    public void textarea(Frame_class fc1) {




        tx1.setEditable(true);
        fc1.add(tx1);

    }

/////////////////////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ae) {

        String args = (String) ae.getActionCommand();
        if (args.equals("New")) {
            StartNewWindow();
        } else if (args.equals("Open")) {
            heading = " Open File ";
            openFile();
        } else if (args.equals("Save")) {
            heading = " Save File ";
            saveOpenedFile();
        } else if (args.equals("Close")) {
            closedFile();
        } else if (args.equals("Save As")) {
            heading = " Save File As ";
            saveAsFile(this);
        } else if (args.equals("Exit")) {
            System.exit(0);
        } else if (args.equals("Cut")) {
            cut();
        } else if (args.equals("Copy")) {
            copy();
        } else if (args.equals("Paste")) {
            paste();
        } else if (args.equals("Courier")) {
            fontName = "Courier";
            textfont();
        } else if (args.equals("Arial")) {
            fontName = "Arial";
            textfont();
        } else if (args.equals("TimesRoman")) {
            fontName = "TimesRoman";
            textfont();
        } else if (args.equals("8")) {
            fontNumeric = 8;
            textfont();
        } else if (args.equals("8")) {
            fontNumeric = 8;
            textfont();
        } else if (args.equals("8")) {
            fontNumeric = 8;
            textfont();
        } else if (args.equals("10")) {
            fontNumeric = 10;
            textfont();
        } else if (args.equals("12")) {
            fontNumeric = 12;
            textfont();
        } else if (args.equals("14")) {
            fontNumeric = 14;
            textfont();
        } else if (args.equals("16")) {
            fontNumeric = 16;
            textfont();
        } else if (args.equals("18")) {
            fontNumeric = 18;
            textfont();
        } else if (args.equals("20")) {
            fontNumeric = 20;
            textfont();
        } else if (args.equals("22")) {
            fontNumeric = 22;
            textfont();
        } else if (args.equals("24")) {
            fontNumeric = 24;
            textfont();
        } else if (args.equals("26")) {
            fontNumeric = 26;
            textfont();
        } else if (args.equals("28")) {
            fontNumeric = 28;
            textfont();
        } else if (args.equals("30")) {
            fontNumeric = 30;
            textfont();
        } else if (args.equals("Bold")) {
            fontStyle = Font.BOLD;
            textfont();
        } else if (args.equals("Plain")) {
            fontStyle = Font.PLAIN;
            textfont();
        } else if (args.equals("Italic")) {
            fontStyle = Font.ITALIC;
            textfont();
        } else if (args.equals("Red")) {
            tx1.setBackground(Color.red);
        } else if (args.equals("Blue")) {
            tx1.setBackground(Color.blue);
        } else if (args.equals("Green")) {
            tx1.setBackground(Color.green);
        } else if (args.equals("Cyan")) {
            tx1.setBackground(Color.cyan);
        } else if (args.equals("Black")) {
            tx1.setBackground(Color.black);
        } else if (args.equals("Dark Gray")) {
            tx1.setBackground(Color.darkGray);
        } else if (args.equals("Gray")) {
            tx1.setBackground(Color.gray);
        } else if (args.equals("Light Gray")) {
            tx1.setBackground(Color.lightGray);
        } else if (args.equals("Magenta")) {
            tx1.setBackground(Color.magenta);
        } else if (args.equals("Orange")) {
            tx1.setBackground(Color.orange);
        } else if (args.equals("Pink")) {
            tx1.setBackground(Color.pink);
        } else if (args.equals("White")) {
            tx1.setBackground(Color.white);
        } else if (args.equals("Yellow")) {
            tx1.setBackground(Color.yellow);
        } else if (args.equals(" Red")) {
            tx1.setForeground(Color.red);
        } else if (args.equals(" Blue")) {
            tx1.setForeground(Color.blue);
        } else if (args.equals(" Green")) {
            tx1.setForeground(Color.green);
        } else if (args.equals(" Cyan")) {
            tx1.setForeground(Color.cyan);
        } else if (args.equals(" Black")) {
            tx1.setForeground(Color.black);
        } else if (args.equals(" Dark Gray")) {
            tx1.setForeground(Color.darkGray);
        } else if (args.equals(" Gray")) {
            tx1.setForeground(Color.gray);
        } else if (args.equals(" Light Gray")) {
            tx1.setForeground(Color.lightGray);
        } else if (args.equals(" Magenta")) {
            tx1.setForeground(Color.magenta);
        } else if (args.equals(" Orange")) {
            tx1.setForeground(Color.orange);
        } else if (args.equals(" Pink")) {
            tx1.setForeground(Color.pink);
        } else if (args.equals(" White")) {
            tx1.setForeground(Color.white);
        } else if (args.equals(" Yellow")) {
            tx1.setForeground(Color.yellow);
        } else if (args.equals("About..")) {
            showTEDialog("I, Rajagopal Neelakantan, working as an Application Developer in IBM.\n" + "This Text Editor was developed in Java as a part of Mini Project in System Software. \n" + "To know more about me \n\n" + "Mail me at rajneela@in.ibm.com\n\n" + "Thanks for using this product.\n" + "\tRajagopal Neelakantan");
        }

    }
    //     }      

    private void textfont() {

        tx1.setFont(new Font(fontName, fontStyle, fontNumeric));

    }

    public void itemStateChanged(ItemEvent ie) {
    }

    private void StartNewWindow() {

        ++window_counter;
        ++window_no;

        st = Integer.toString(window_no);

        Frame_class f = new Frame_class("Text Editor 1.0 By Rajagopal Neelakantan : New Window  " + st);

        f.setSize(700, 600);
    }

    private void newWindow() {
        fileName = null;
        tx1.setText("");
        tx1.requestFocus();
    }

    private boolean lentest() {

        s = tx1.getText();
        int len = s.length();
        if (len > 0) {
            return (true);
        } else {
            return (false);
        }
    }

    private void closedFile() {

        if (lentest()) {
            saveOpenedFile();
            newWindow();
            String ar;
            if (window_no >= 1) {
                ar = "New Window  " + st + " : ";
            } else {
                ar = "";
            }
            this.setTitle("Text Editor 1.0 By Rajagopal Neelakantan  : " + ar + "*.*");


        } else {
            newWindow();
        }
    }

    /* public  void keyPressed(KeyEvent ke){

    int key=ke.getKeyCode();

    switch(key){
    case KeyEvent.VK_F1:openFile();break;
    }

    }*/
//////////////////////////////////////////////////////////////////////////////// 
// public void keyReleased(KeyEvent ke){}
// public void keyTyped(KeyEvent ke){}
    // The Dialog boxes for openinig and saving file
    private void AddDialog() {

        openDialog = new FileDialog(this, "Open File", FileDialog.LOAD);
        saveDialog = new FileDialog(this, "Save File", FileDialog.SAVE);
    }

    //cuts selected textand places it on the buffer
    public void cut() {
        copystring = tx1.getSelectedText();
        tx1.replaceText("", tx1.getSelectionStart(), tx1.getSelectionEnd());
        tx1.requestFocus();
    }

    //copies selected text to buffer
    public void copy() {

        copystring = tx1.getSelectedText();
        tx1.requestFocus();
    }

    //paste text from buffer to the screen
    public void paste() {

        if (copystring.length() > 0) {
            tx1.insertText(copystring, tx1.getSelectionStart());
        }
        tx1.requestFocus();
    }

    @SuppressWarnings("deprecation")
    private void openFile() {

        try {
            FileDialog f = new FileDialog(this, "OPEN", FileDialog.LOAD);
            f.show();
            fname = f.getFile();
            dir = f.getDirectory();
            if (fname == null || dir == null) {
            } else {
                filename = fname;
                directory = dir;
                File fi = new File(directory, filename);
                FileInputStream fs = new FileInputStream(fi);
                byte b[] = new byte[fs.available()];
                fs.read(b);
                txtArea.setText(new String(b));
                txtArea.append("");
                setTitle(filename + " - NOTEPAD");
                txtArea.setCaretPosition(0);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File is not found.Please verify the file name", "OPEN", JOptionPane.WARNING_MESSAGE);
            Open.doClick();
        }

// String filename;
// 
// openDialog.setVisible(true);
// 
// filename=openDialog.getFile();
// if(filename!=null){
//  filename=check(filename);
//  if(read(filename)) 
//  {
//  fileName=filename;String ar;
//  if(window_no>=1){ar="New Window  "+st+" : ";}else ar="";
//  this.setTitle("Text Editor 1.0 By Rajagopal Neelakantan : "+ar+fileName);
//  }
//  }
//  tx1.requestFocus();
    }

    private boolean read(String filename) {


        String line;
        FileInputStream in = null;
        DataInputStream dataIn = null;
        BufferedInputStream bis = null;
        StringBuffer buffer = new StringBuffer();

        try {
            in = new FileInputStream(filename);
            bis = new BufferedInputStream(in);
            dataIn = new DataInputStream(bis);
        } catch (Throwable e) {
            showErrorDialog("Can't open\"" + filename + "\"");
            return (false);
        }

        try {
            while ((line = dataIn.readLine()) != null) {
                buffer.append(line + "\n");
            }

            in.close();
            tx1.setText(buffer.toString());
        } catch (IOException e) {
            showErrorDialog("Can't read \"" + filename + "\"");
            return (false);
        }
        return (true);
    }

    private String check(String filename) {
        if (filename.endsWith(".*.*")) {
            filename = filename.substring(0, filename.length() - 4);
        }
        return (filename);
    }

//method saves a file you opened previously
    //method produces error if you did not open a file previously
    public void saveOpenedFile() {

        if (!lentest()) {
            showErrorDialog("There is no  Text  to Save");
        } else if (fileName == null) {
            saveAsFile(this);
            //showErrorDialog("You did not previously open a file.Use save as.");
        } else {
            write(fileName);
            tx1.requestFocus();
        }
    }

//methods save as the file you are currently editing
    private void saveAsFile(Frame_class f1) {
        if (!lentest()) {
            showErrorDialog("There is no Text  to Save");
        } else {
            String filename;
            saveDialog.setVisible(true);
            filename = saveDialog.getFile();

            if (filename != null) {
                filename = check(filename);
                if (write(filename)) {
                    fileName = filename;
                    f1.setTitle(" Text Editor 1.0 By  Rajagopal Neelakantan  : " + heading + " : " + fileName + "                    " + st);

                }
            }

            tx1.requestFocus();
        }

    }


    public void saveFileService(String data, String filename){
        try{
            FileWriter test;
            test=new FileWriter(filename);
            test.write(data);
            test.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



//method handles writing files to the file system
    private boolean write(String filename) {


        FileOutputStream os = null;

        try {

            os = new FileOutputStream(filename);
        } catch (Throwable e) {
            showErrorDialog("Can't write\"" + filename + "\"");
            return (false);
        }

        try {

            String s = tx1.getText();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                os.write(s.charAt(i));
            }

            os.close();
        } catch (IOException e) {
            showErrorDialog("can't write\"" + filename + "\"");
            return (false);
        }
        return (true);
    }

    //DialogBoxes.......
    public void showErrorDialog(String message) {
        if (errorDialog != null) {
            errorDialog.dispose();
        }
        errorDialog = new ErrorDialog(this, message);
        errorDialog.setVisible(true);
    }

    public void showTEDialog(String message) {
        if (teDialog != null) {
            teDialog.dispose();
        }
        teDialog = new TEDialog(this, message);
        teDialog.setVisible(true);
    }
}//close brace of Frame_class
////////////////////////////////////////////////////////////////////////////////
class ErrorDialog extends Dialog {

    Frame_class parent;
    String message;

    public ErrorDialog(Frame_class parent, String message) {
        super(parent, "Error", true);
        setBackground(Color.yellow);
        this.parent = parent;
        this.message = message;

        Panel p;

        p = new Panel();
        p.add(new Label(message));
        p.setFont(new Font("System", Font.BOLD, 12));
        add("Center", p);

        Dimension d;
        d = parent.size();
        reshape(200, 50, 420, 100);
        setResizable(false);
    }

    public boolean handleEvent(Event event) {

        switch (event.id) {
            case Event.WINDOW_DESTROY:
                dispose();
                parent.tx1.requestFocus();
                return (true);
        }
        return (false);
    }
}

class TEDialog extends Dialog {

    Frame_class parent;
    String message;

    public TEDialog(Frame_class parent, String message) {
        super(parent, "TextEditor 1.0", true);
        setBackground(Color.yellow);
        this.parent = parent;
        this.message = message;

        TextArea p;

        p = new TextArea();
        p.setText(message);
        p.setFont(new Font("System", Font.BOLD, 12));
        add("Center", p);
        p.setEditable(false);
        Dimension d;
        d = parent.size();
        reshape(200, 50, 500, 300);
//setResizable(false);
    }

    public boolean handleEvent(Event event) {

        switch (event.id) {
            case Event.WINDOW_DESTROY:
                dispose();
                parent.tx1.requestFocus();
                return (true);
        }
        return (false);
    }
}

/////////////////////////////////////////////////////////////////////////////////
class MyWindowAdapter extends WindowAdapter {// MyWindowAdapter class

    Frame_class fc1;

    public MyWindowAdapter(Frame_class fc1) {
        this.fc1 = fc1;


    }

    public void windowClosing(WindowEvent we) {

        if (Frame_class.window_counter == 0) {
            System.exit(0);
        } else {
            fc1.setVisible(false);
        }
        --Frame_class.window_counter;
    }
}//end MyWindowAdapter class
////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////
//main() function class 
/////////////////////////////////////////////////////////////////////////////////
public class T_Editor {  //open brace of T_Editor class

    T_Editor(){
        Frame f1 = new Frame_class(" Text Editor 1.0 By Rajagopal Neelakantan");
//f1.setVisible(true);
        Dimension d;
        d = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setSize(d.width, d.height);
    }
    public static void main(String args[]) { //open brace of main() fun.


        Frame f1 = new Frame_class(" Text Editor 1.0 By Rajagopal Neelakantan");
//f1.setVisible(true);
        Dimension d;
        d = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setSize(d.width, d.height);
    }//close brace of main() fun.
}//close brace of T_Editor class

/////////////////////////////////////////////////////////////////////////////////

