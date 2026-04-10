import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTrade {

    // -------- Merge Sort (Ascending, Stable) --------
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= ensures left element comes first if equal
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // Copy back
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // -------- Quick Sort (Descending) --------
    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortDesc(arr, low, pivotIndex - 1);
            quickSortDesc(arr, pivotIndex + 1, high);
        }
    }

    // Lomuto Partition (Descending)
    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // choose last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC order
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // -------- Merge Two Sorted Lists (Ascending) --------
    public static Trade[] mergeSortedLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // -------- Total Volume --------
    public static long totalVolume(Trade[] arr) {
        long sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    // -------- Driver --------
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort (Ascending)
        mergeSort(trades, 0, trades.length - 1);
        System.out.println("Merge Sort ASC: " + Arrays.toString(trades));

        // Quick Sort (Descending)
        quickSortDesc(trades, 0, trades.length - 1);
        System.out.println("Quick Sort DESC: " + Arrays.toString(trades));

        // Merge two sorted lists
        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 200)
        };

        Trade[] afternoon = {
                new Trade("a1", 300),
                new Trade("a2", 400)
        };

        Trade[] merged = mergeSortedLists(morning, afternoon);
        System.out.println("Merged: " + Arrays.toString(merged));

        // Total volume
        System.out.println("Total Volume: " + totalVolume(merged));
    }
}