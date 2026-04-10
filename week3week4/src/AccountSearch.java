import java.util.*;

public class AccountSearch {

    // -------- Linear Search: First Occurrence --------
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First -> Comparisons: " + comparisons);
        return -1;
    }

    // -------- Linear Search: Last Occurrence --------
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                result = i;
            }
        }

        System.out.println("Linear Last -> Comparisons: " + comparisons);
        return result;
    }

    // -------- Binary Search: First Occurrence --------
    public static int binaryFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1, comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                high = mid - 1; // move left
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary First -> Comparisons: " + comparisons);
        return result;
    }

    // -------- Binary Search: Last Occurrence --------
    public static int binaryLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1, comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                low = mid + 1; // move right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Last -> Comparisons: " + comparisons);
        return result;
    }

    // -------- Count Occurrences --------
    public static int countOccurrences(String[] arr, String target) {
        int first = binaryFirst(arr, target);
        int last = binaryLast(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // -------- Driver --------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear Search
        int firstLinear = linearFirst(logs, "accB");
        int lastLinear = linearLast(logs, "accB");

        System.out.println("Linear First Index: " + firstLinear);
        System.out.println("Linear Last Index: " + lastLinear);

        // Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // Binary Search
        int firstBinary = binaryFirst(logs, "accB");
        int lastBinary = binaryLast(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary First Index: " + firstBinary);
        System.out.println("Binary Last Index: " + lastBinary);
        System.out.println("Count: " + count);
    }
}