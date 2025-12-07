package sorting;

public class InsertionSort {

    public static double[] sort(double[] arr) {

        double[] data = arr.clone();

        for (int i = 1; i < data.length; i++) {
            double key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }

        return data;
    }
}
