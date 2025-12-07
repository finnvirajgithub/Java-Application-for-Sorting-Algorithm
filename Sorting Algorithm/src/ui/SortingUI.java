package ui;

import file.CSVLoader;
import performance.PerformanceEvaluator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SortingUI extends JFrame {

    private JTextField columnField;
    private JTextArea outputArea;
    private String selectedFilePath = "";

    public SortingUI() {

        setTitle("CSV Sorting Performance Analyzer");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel();
        JButton uploadBtn = new JButton("Upload CSV");
        JLabel colLabel = new JLabel("Column Index:");
        columnField = new JTextField(5);
        JButton runBtn = new JButton("Run Sorting");

        topPanel.add(uploadBtn);
        topPanel.add(colLabel);
        topPanel.add(columnField);
        topPanel.add(runBtn);

        // ===== OUTPUT AREA =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON ACTIONS =====

        uploadBtn.addActionListener(e -> chooseFile());

        runBtn.addActionListener(e -> runSorting());

        setVisible(true);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(this);

        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedFilePath = file.getAbsolutePath();
            outputArea.setText("Selected File:\n" + selectedFilePath + "\n\n");
        }
    }

    private void runSorting() {
        if (selectedFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please upload a CSV file first!");
            return;
        }

        int columnIndex;
        try {
            columnIndex = Integer.parseInt(columnField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid column index!");
            return;
        }

        double[] data = CSVLoader.loadNumericColumn(selectedFilePath, columnIndex);

        if (data.length == 0) {
            outputArea.append("No numeric data found in this column!\n");
            return;
        }

        outputArea.append("Data Loaded: " + data.length + " values\n\n");

        // Redirect console output to UI
        PerformanceEvaluator.evaluateAllWithUI(data, outputArea);
    }
}
