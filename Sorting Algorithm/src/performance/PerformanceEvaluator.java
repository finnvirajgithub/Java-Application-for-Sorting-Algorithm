package performance;

import sorting.*;

import javax.swing.*;

public class PerformanceEvaluator {

    public static void evaluateAllWithUI(double[] data, JTextArea output) {

        output.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        output.append("📊 SORTING PERFORMANCE REPORT\n");
        output.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");

        long t1 = test("Insertion Sort", () -> InsertionSort.sort(data), output, "🟦");
        long t2 = test("Quick Sort", () -> QuickSort.sort(data), output, "🟩");
        long t3 = test("Merge Sort", () -> MergeSort.sort(data), output, "🟨");
        long t4 = test("Heap Sort", () -> HeapSort.sort(data), output, "🟪");
        long t5 = test("Shell Sort", () -> ShellSort.sort(data), output, "🟥");

        long min = Math.min(t1, Math.min(t2, Math.min(t3, Math.min(t4, t5))));

        output.append("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        output.append("🏆 BEST ALGORITHM\n");
        output.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        if (min == t1) output.append("➡ INSERTION SORT (FASTEST)\n");
        else if (min == t2) output.append("➡ QUICK SORT (FASTEST)\n");
        else if (min == t3) output.append("➡ MERGE SORT (FASTEST)\n");
        else if (min == t4) output.append("➡ HEAP SORT (FASTEST)\n");
        else output.append("➡ SHELL SORT (FASTEST)\n");

        output.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
    }

    private static long test(String name, Runnable algorithm, JTextArea output, String icon) {

        long start = System.nanoTime();
        algorithm.run();
        long end = System.nanoTime();

        long time = end - start;

        output.append(icon + " " +
                String.format("%-15s", name) +
                " :  " +
                String.format("%,d", time) + " ns\n");

        return time;
    }
}
