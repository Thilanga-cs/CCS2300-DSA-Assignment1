package module3_performance;

import java.util.Random;

public class PerformanceAnalyzer {

    private static int[] generateRandomArray(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(100000); // bigger range
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) copy[i] = arr[i];
        return copy;
    }

    public static void run() {
        int[] sizes = {100, 500, 1000};

        System.out.println("\nModule 3: Algorithm Performance Analyzer");
        System.out.println("Algorithms: Merge Sort + Binary Search");
        System.out.println("\nSize\tSort Time (ns)\tSearch Time (ns)");
        System.out.println("----\t-------------\t----------------");

        for (int size : sizes) {
            int[] data = generateRandomArray(size);

            // Measure Sort
            int[] toSort = copyArray(data);
            long sortStart = System.nanoTime();
            MergeSort.sort(toSort);
            long sortEnd = System.nanoTime();

            // Pick a target from the sorted array (guaranteed exists)
            int target = toSort[size / 2];

            // Measure Search (Binary Search needs sorted array)
            long searchStart = System.nanoTime();
            BinarySearch.search(toSort, target);
            long searchEnd = System.nanoTime();

            long sortTime = sortEnd - sortStart;
            long searchTime = searchEnd - searchStart;

            System.out.println(size + "\t" + sortTime + "\t\t" + searchTime);
        }
    }
}
