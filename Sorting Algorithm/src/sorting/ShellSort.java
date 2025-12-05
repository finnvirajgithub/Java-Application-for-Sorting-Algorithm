package sorting;

public class ShellSort {

    public static double[] sort(double[] arr) {
        double[] data = arr.clone();
        int n = data.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                double temp = data[i];
                int j = i;

                while (j >= gap && data[j - gap] > temp) {
                    data[j] = data[j - gap];
                    j -= gap;
                }
                data[j] = temp;
            }
        }
        return data;
    }
}
