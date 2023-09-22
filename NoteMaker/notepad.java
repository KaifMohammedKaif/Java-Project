import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class notepad extends JFrame implements ActionListener {
   
    JTextArea area;
    String text;

    notepad() {
        
        setTitle("NoteMaker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon npIcon = new ImageIcon(ClassLoader.getSystemResource("mk.jpg"));
        Image icon = npIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(15, 15, 25, 220));

        JMenu file = new JMenu("File");
        file.setFont(new Font("Arial", Font.PLAIN, 16));
        file.setForeground(Color.WHITE);
        menubar.add(file);

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Arial", Font.PLAIN, 16));
        menubar.add(edit);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem select = new JMenuItem("select All");
        select.addActionListener(this);
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(this);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(select);
        edit.add(delete);
        edit.add(undo);

        JMenu about = new JMenu("About");
        about.setForeground(Color.WHITE);
        about.setFont(new Font("Arial", Font.PLAIN, 16));
        menubar.add(about);

        JMenuItem aboutme = new JMenuItem("About me");
        aboutme.addActionListener(this);
        about.add(aboutme);

        setJMenuBar(menubar);

        area = new JTextArea();
        area.setBackground(Color.WHITE);
        area.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser Chooser = new JFileChooser();
            Chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("only .txt files", "txt");
            Chooser.addChoosableFileFilter(restrict);

            int action = Chooser.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = Chooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("save");

            int action = saveas.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("select All")) {
            area.selectAll();
        } else if (ae.getActionCommand().equals("Delete")) {
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("About me")) {
            new about().setVisible(true);
        }
    }

    public class about extends JFrame {

        about() {

            setTitle("About");
            setBounds(400, 200, 400, 300);

            JLabel tx = new JLabel(
                    "<html> <body text-align=center> This is NoteMaker <br> Version : 1.0.0 2023 &Copy; <br> it is inspired from Microsoft Notepad <br> <hr>  Hello guys this NoteMaker have all the features of a note making software  <br> there are features like cut, Copy Paste, Delete, Select All etc. <br> But this NoteMaker also have all the basic utilities like  Create new file, open file, Save file, Print file etc. <br> <br> THANKS <hr></body> </html>");
            tx.setBounds(100, 400, 600, 500);
            add(tx);

            setVisible(true);
        }

    }

    public static void main(String[] args) {
        new notepad();
    }
}