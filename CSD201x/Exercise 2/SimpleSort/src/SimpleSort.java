import java.util.Arrays;
import java.util.Scanner;

public class SimpleSort {

    private int[] a;

    public static void main(String[] args) throws Exception {
        run();
    }

    private static void run() {
        SimpleSort t = new SimpleSort();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("\n Choose your option:");
            System.out.println("  1. Initial array");
            System.out.println("  2. Display data");
            System.out.println("  3. Selection sort");
            System.out.println("  4. Insertion sort");

            System.out.println("  5. Search");

            System.out.println("  0. Exit\n");
            System.out.print("  Your selection (0 -> 4): ");

            int choice = s.nextInt();

            if (choice == 0) {
                System.out.println(" Good bye, have a nice day!");
                break;
            }
            switch (choice) {
                case 1:
                    t.initial();
                    break;
                case 2:
                    t.display(t.a);
                    break;
                case 3:
                    int[] b = t.selectSort();
                    t.display(b);
                    break;
                case 4:
                    int[] c = t.insertSort();
                    t.display(c);
                    break;

                case 5:
                    System.out.print("value search: ");
                    System.out.println(t.search(s.nextInt()) + " ");
                    break;
                default:
                    System.out.println("**Invalid choice. Try again.**");
            }
        }
    }

    void display(int[] a) {
        int i;
        for (i = 0; i < a.length; i++)
            System.out.print("  " + a[i]);
        System.out.println();
    }

    public void initial() {
        Scanner scan = new Scanner(System.in);
        System.out.println("n = ");
        int n = scan.nextInt();
        System.out.println("input array:");
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
    }

    public int[] selectSort() {
        // select least node for each loop
        int[] b = Arrays.copyOf(this.a, this.a.length);
        for (int i = 0; i < b.length; i++) {
            int indexMin = i;
            for (int j = i + 1; j < b.length; j++) {
                if (b[indexMin] > b[j]) {
                    indexMin = j;
                }
            }
            int t = b[i];
            b[i] = b[indexMin];
            b[indexMin] = t;
        }

        return b;
    }

    public int[] insertSort() {
        int[] b = Arrays.copyOf(this.a, this.a.length);

        if (b.length <= 1) {
            return b;
        }

        for (int i = 1; i < b.length; i++) {
            int j = i;
            int t = b[j];
            while (j > 0 && t < b[j - 1]) {
                b[j] = b[j - 1];
                j--;
            }
            b[j] = t;
        }

        return b;
    }

    public int search(int value) {
        for (int i = 0; i < a.length; i++) {
            if (value == a[i]) {
                return i;
            }
        }

        return -1;
    }
}
