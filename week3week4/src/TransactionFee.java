import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // HH:MM format

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFee {

    // -------- Bubble Sort (by fee) --------
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination (optimization)
            if (!swapped) break;
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // -------- Insertion Sort (by fee + timestamp) --------
    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare fee first, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift right
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Comparator logic (stable)
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // -------- High-fee outliers --------
    public static List<Transaction> getHighFeeOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    // -------- Driver --------
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Choose sorting method based on size
        if (transactions.size() <= 100) {
            bubbleSortByFee(transactions);
            System.out.println("Bubble Sorted: " + transactions);
        } else {
            insertionSortByFeeAndTime(transactions);
            System.out.println("Insertion Sorted: " + transactions);
        }

        // Outliers
        List<Transaction> outliers = getHighFeeOutliers(transactions);
        System.out.println("High-fee outliers: " + outliers);
    }
}
