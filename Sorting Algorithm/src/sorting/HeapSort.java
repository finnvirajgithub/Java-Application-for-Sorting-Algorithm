package sorting;

public class HeapSort {

    public static double[] sort(double[] arr) {
        double[] data = arr.clone();
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(data, n, i);

        for (int i = n - 1; i >= 0; i--) {
            double temp = data[0];
            data[0] = data[i];
            data[i] = temp;

            heapify(data, i, 0);
        }

        return data;
    }

    private static void heapify(double[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            double swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
