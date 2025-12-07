package ui;

import file.CSVLoader;
import performance.PerformanceEvaluator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class BeautifulSortingUI extends JFrame {

    private JTable table;
    private JTextArea outputArea;
    private JSpinner columnSpinner;
    private String selectedFilePath = "";

    public BeautifulSortingUI() {

        setTitle("CSV Sorting Performance Analyzer");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        getContentPane().setBackground(new Color(230, 235, 240));


        // FONT
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

        // ===== TOP BAR =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(new Color(32, 64, 128));

        JButton uploadBtn = new JButton("Upload CSV");
        JButton runBtn = new JButton("Run Sorting");

        JLabel colLabel = new JLabel("Numeric Column Index:");
        colLabel.setForeground(Color.WHITE);

        columnSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));

        uploadBtn.setFont(mainFont);
        runBtn.setFont(mainFont);
        colLabel.setFont(mainFont);
        columnSpinner.setFont(mainFont);

        topPanel.add(uploadBtn);
        topPanel.add(colLabel);
        topPanel.add(columnSpinner);
        topPanel.add(runBtn);

        // ===== TABLE PANEL =====
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0, 150, 136));
        table.setSelectionForeground(Color.WHITE);

        // Alternate row colors 
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0)
                        c.setBackground(new Color(245, 247, 250));   // light gray
                    else
                        c.setBackground(Color.WHITE);
                }

                setBorder(noFocusBorder);
                return c;
            }
        });

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // ===== RESULT PANEL =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        outputArea.setBackground(new Color(15, 15, 15));
        outputArea.setForeground(new Color(0, 230, 118));


        JScrollPane resultScroll = new JScrollPane(outputArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll, resultScroll);
        splitPane.setDividerLocation(300);

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        // ===== ACTIONS =====
        uploadBtn.addActionListener(e -> chooseFile());
        runBtn.addActionListener(e -> runSorting());

        setVisible(true);
    }

    //  FILE CHOOSER
    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            selectedFilePath = file.getAbsolutePath();

            loadTable(file);
            outputArea.setText("Loaded File: " + selectedFilePath + "\n\n");
        }
    }

    //  LOAD CSV INTO TABLE
    private void loadTable(File file) {
        String[][] data = CSVLoader.loadTableData(file.getAbsolutePath());

        int cols = data[0].length;
        String[] headers = new String[cols];

        for (int i = 0; i < cols; i++) headers[i] = "Col " + i;

        table.setModel(new DefaultTableModel(data, headers));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(32, 64, 128));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);

    }

    //  RUN SORTING
    private void runSorting() {

        if (selectedFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please upload a CSV file!");
            return;
        }

        int columnIndex = (int) columnSpinner.getValue();

        double[] data = CSVLoader.loadNumericColumn(selectedFilePath, columnIndex);

        if (data.length == 0) {
            outputArea.append("No numeric data found in this column!\n");
            return;
        }

        outputArea.setText("");
        outputArea.append(" File Loaded Successfully\n");
        outputArea.append(" Data Count: " + data.length + " values\n\n");

        PerformanceEvaluator.evaluateAllWithUI(data, outputArea);
    }
}
