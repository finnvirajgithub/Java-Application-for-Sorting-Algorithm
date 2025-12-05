package ui;

import file.CSVLoader;
import performance.PerformanceEvaluator;

public class MainUI {

    public static void main(String[] args) {

        System.out.println("=== CSV Sorting Performance Analyzer ===");

        String filePath = "data.csv"; // we will add this later

        double[] data = CSVLoader.loadNumericColumn(filePath, 0);

        PerformanceEvaluator.evaluateAll(data);
    }
}
