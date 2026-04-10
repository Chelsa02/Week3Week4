import java.util.*;

public class RiskThresholdLookup {

    // -------- Linear Search (Unsorted) --------
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search -> Found at index " + i +
                        ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Search -> Not found, Comparisons: " + comparisons);
        return -1;
    }

    // -------- Binary Search: Insertion Point (lower_bound) --------
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Lower Bound (Insertion Index): " + low +
                ", Comparisons: " + comparisons);
        return low;
    }

    // -------- Binary Search: Upper Bound --------
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Upper Bound Index: " + low +
                ", Comparisons: " + comparisons);
        return low;
    }

    // -------- Floor (largest ≤ target) --------
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Floor Comparisons: " + comparisons);
                return arr[mid];
            } else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor Comparisons: " + comparisons);
        return result;
    }

    // -------- Ceiling (smallest ≥ target) --------
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Ceiling Comparisons: " + comparisons);
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                result = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Ceiling Comparisons: " + comparisons);
        return result;
    }

    // -------- Driver --------
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int[] sorted = {10, 25, 50, 100};

        int target = 30;

        // Linear Search (unsorted)
        linearSearch(unsorted, target);

        // Binary Search variants (sorted)
        lowerBound(sorted, target);
        upperBound(sorted, target);

        Integer f = floor(sorted, target);
        Integer c = ceiling(sorted, target);

        System.out.println("Floor(" + target + "): " + f);
        System.out.println("Ceiling(" + target + "): " + c);
    }
}