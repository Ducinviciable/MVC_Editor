package connect.NOTEPAD;

import javax.swing.*;
// import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.undo.UndoManager;
// import Model.TextModel;

public class TextView extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private TextModel model;
    private UndoManager undoManager;

    public TextView(TextModel model) {
        this.model = model;
        textArea = new JTextArea();
        fileChooser = new JFileChooser();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem saveAsItem = new JMenuItem("Save as");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");

        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsFile();
            }
        });

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        undoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

        redoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        menuBar.add(fileMenu);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(openItem);
        fileMenu.add(exitItem);

        menuBar.add(editMenu);
        editMenu.add(undoItem);
        editMenu.add(redoItem);

        setJMenuBar(menuBar);
        setTitle("NotePad");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Open
    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            model.openFile(file);
            textArea.setText(model.getTextContent());
        }
    }

    // Save
    private void saveFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            model.saveFile(file, textArea.getText());
        }
    }

    // Save as
    private void saveAsFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            model.saveFile(file, textArea.getText());
        }
    }

    // Undo
    private void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    // Redo
    private void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

}
