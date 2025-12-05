package performance;

import sorting.InsertionSort;

public class PerformanceEvaluator {

    public static void evaluateAll(double[] data) {

        System.out.println("Running Insertion Sort...");

        long start = System.nanoTime();
        double[] sorted = InsertionSort.sort(data);
        long end = System.nanoTime();

        long timeTaken = end - start;

        System.out.println("Insertion Sort Time: " + timeTaken + " ns");

        System.out.println("First 5 Sorted Values:");
        for (int i = 0; i < Math.min(5, sorted.length); i++) {
            System.out.print(sorted[i] + " ");
        }
    }
}
