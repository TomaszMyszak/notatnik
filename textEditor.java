/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezi.wsti.texteditor;
// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener {
    // Text component
    JTextArea t;
 
    // Frame
    JFrame f;
 
    // Constructor
    editor()
    {
        // Create a frame
        f = new JFrame("teksciarz");
 
        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
 
            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }
 
        // Text component
        t = new JTextArea();
 
        // Create a menubar
        JMenuBar mb = new JMenuBar();
 
        // Create amenu for menu
        JMenu m1 = new JMenu("Plik");
 
        // Create menu items
        JMenuItem mi1 = new JMenuItem("Nowy");
        JMenuItem mi2 = new JMenuItem("Otworz");
        JMenuItem mi3 = new JMenuItem("Zapisz jako");
        JMenuItem mi9 = new JMenuItem("Drukuj");
 
        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
 
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
 
        // Create amenu for menu
        JMenu m2 = new JMenu("Edycja");
 
        // Create menu items
        JMenuItem mi4 = new JMenuItem("wytnij");
        JMenuItem mi5 = new JMenuItem("skopiuj");
        JMenuItem mi6 = new JMenuItem("wstaw");
 
        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
 
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
 
        JMenuItem mc = new JMenuItem("zamknij");
 
        mc.addActionListener(this);
 
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
 
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }
 
    // If a button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
 
        if (s.equals("wytnij")) {
            t.cut();
        }
        else if (s.equals("skopiuj")) {
            t.copy();
        }
        else if (s.equals("wstaw")) {
            t.paste();
        }
        else if (s.equals("Zapisz jako")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");
 
            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION) {
 
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);
 
                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);
 
                    // Write
                    w.write(t.getText());
 
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "użytkownik anulował operację");
        }
        else if (s.equals("Drukuj")) {
            try {
                // print the file
                t.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if (s.equals("Otworz")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");
 
            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);
 
            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // String
                    String s1 = "", sl = "";
 
                    // File reader
                    FileReader fr = new FileReader(fi);
 
                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);
 
                    // Initialize sl
                    sl = br.readLine();
 
                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
 
                    // Set the text
                    t.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "użytkownik anulował operację");
        }
        else if (s.equals("Nowy")) {
            t.setText("");
        }
        else if (s.equals("Zamknij")) {
            f.setVisible(false);
        }
    }
 
    // Main class
    public static void main(String args[])
    {
        editor e = new editor();
    }
}