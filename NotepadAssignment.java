import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotepadAssignment extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;

    public NotepadAssignment() {
        
        setTitle("Notepad Assignment");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        UIManager.put("Menu.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("MenuItem.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 16));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(helpMenu);

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        JMenuItem fontItem = new JMenuItem("Font");
        JMenuItem colorItem = new JMenuItem("Color");

        formatMenu.add(fontItem);
        formatMenu.add(colorItem);

        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        fontItem.addActionListener(e -> changeFont());
        colorItem.addActionListener(e -> changeColor());

        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Notepad Assignment\nName: C.J. Morapitiya\nID: s16110",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
    }
//open
    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }
//save
    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }

    // Change font
    private void changeFont() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String selectedFont = (String) JOptionPane.showInputDialog(
                this, "Choose Font:", "Font Chooser",
                JOptionPane.PLAIN_MESSAGE, null, fonts, textArea.getFont().getFamily());
        if (selectedFont != null) {
            textArea.setFont(new Font(selectedFont, Font.PLAIN, 16));
        }
    }

    // Change color
    private void changeColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose Text Color", textArea.getForeground());
        if (newColor != null) {
            textArea.setForeground(newColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotepadAssignment().setVisible(true);
        });
    }
}
