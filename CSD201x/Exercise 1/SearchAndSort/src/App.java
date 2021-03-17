import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        float[] a = nhapArray();
        bubbleSort(a);
    }

    private static float[] nhapArray() {
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        int n = scan.nextInt();
        float[] a = new float[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextFloat();
        }
        return a;
    }

    private static void bubbleSort(float[] a) {
        boolean continued = true;
        while (continued) {
            continued = false;
            for (int i = 0; i < a.length - 1; i++) {
                if(a[i] > a[i+1]){
                    float t = a[i];
                    a[i] = a[i+1];
                    a[i+1] = t;
                    continued = true;
                }
            }

            System.out.println(Arrays.toString(a));
        }
    }
}
