import java.util.*;

class Client {
    String id;
    int riskScore;
    double accountBalance;

    public Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + ":" + riskScore;
    }
}

public class ClientRisk{

    // -------- Bubble Sort (Ascending by riskScore) --------
    public static void bubbleSortAsc(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualization
                    System.out.println("Swap: " + arr[j] + " <-> " + arr[j + 1]);
                }
            }

            if (!swapped) break; // early exit
        }

        System.out.println("Total swaps: " + swaps);
    }

    // -------- Insertion Sort (Descending by riskScore + accountBalance) --------
    public static void insertionSortDesc(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compareDesc(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Comparator: DESC riskScore, then DESC accountBalance
    private static int compareDesc(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c2.riskScore, c1.riskScore);
        }
        return Double.compare(c2.accountBalance, c1.accountBalance);
    }

    // -------- Top N High Risk Clients --------
    public static List<Client> topNClients(Client[] arr, int n) {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            result.add(arr[i]);
        }
        return result;
    }

    // -------- Driver --------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (Ascending)
        bubbleSortAsc(clients);
        System.out.println("Bubble Sorted (ASC): " + Arrays.toString(clients));

        // Insertion Sort (Descending)
        insertionSortDesc(clients);
        System.out.println("Insertion Sorted (DESC): " + Arrays.toString(clients));

        // Top 3 risks
        List<Client> topClients = topNClients(clients, 3);
        System.out.println("Top Risks: " + topClients);
    }
}
