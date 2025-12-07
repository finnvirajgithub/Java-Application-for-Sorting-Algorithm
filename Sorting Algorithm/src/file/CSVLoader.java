package file;

import java.io.*;
import java.util.*;

public class CSVLoader {

    public static double[] loadNumericColumn(String filePath, int columnIndex) {
        List<Double> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    double value = Double.parseDouble(data[columnIndex]);
                    values.add(value);
                } catch (Exception ignored) {}
            }
        } catch (IOException e) {
            System.out.println("File reading error!");
        }

        double[] array = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            array[i] = values.get(i);
        }

        return array;
    }

    // ✅ NEW: Load full CSV for JTable
    public static String[][] loadTableData(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Table load error!");
        }

        return rows.toArray(new String[0][]);
    }
}
