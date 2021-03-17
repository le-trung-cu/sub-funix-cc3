import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

  static Scanner scanner;
  static Algorithm algorithm;
  static float a[];

  public static void main(String[] args) throws Exception {
    scanner = new Scanner(System.in);
    algorithm = new Algorithm();
    a = new float[0];
    applicationLoop();
  }

  public static void applicationLoop() {
    while (true) {
      try {
        showMenu();
        int menuItem = scanner.nextInt();
        if (menuItem == 0) {
          System.out.println("Thank!!");
          break;
        }

        handle(menuItem, algorithm);
        System.out.println("===================================================");
        scanner.nextLine();
        scanner.nextLine();

      } catch (Exception e) {
        System.err.println("Error!");
        System.err.println("Error Message: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void handle(int menuItem, Algorithm algorithm) throws Exception {
    float valueSearch;
    float[] aCopy;
    switch (menuItem) {
    case 1:
      System.out.print("Nhap number: ");
      int n = scanner.nextInt();
      a = new float[n];
      for (int i = 0; i < n; i++) {
        a[i] = scanner.nextFloat();
      }
      Algorithm.writeFile(a);
      break;
    case 2:
      a = Algorithm.readFile();
      System.out.println(Algorithm.joinArray(", ", a));
      break;
    case 3:
      System.out.println("Bubble Sort");
      aCopy = Arrays.copyOf(a, a.length);
      Algorithm.bubbleSort(aCopy);
      System.out.println(Algorithm.getFileContent(Algorithm.BUBBLE_SORT_OUT));
      break;
    case 4:
      System.out.println("Selection Sort");

      aCopy = Arrays.copyOf(a, a.length);
      Algorithm.selectionSort(aCopy);
      System.out.println(Algorithm.getFileContent(Algorithm.SELECT_SORT_OUT));
      break;
    case 5:
      System.out.println("Insertion Sort");

      aCopy = Arrays.copyOf(a, a.length);
      Algorithm.insertionSort(aCopy);
      System.out.println(Algorithm.getFileContent(Algorithm.INSERT_SORT_OUT));
      break;
    case 6:
      System.out.println("Linear Search:");

      valueSearch = inputValueSearch();
      List<Integer> indexsOf = Algorithm.search(a, valueSearch);
      System.out.println(Algorithm.joinArray(", ", indexsOf));
      break;
    case 7:
      System.out.println("Binary Search:");

      valueSearch = inputValueSearch();
      aCopy = Arrays.copyOf(a, a.length);
      Algorithm.insertionSort(aCopy);
      int indexOfB = Algorithm.binarySearch(aCopy, 0, aCopy.length, valueSearch);
      if (indexOfB == -1) {
        System.out.println("không tìm thấy");
      } else {
        System.out.println("Indext of fist element: " + indexOfB);
      }

      break;
    default:
      break;
    }
  }

  public static void showMenu() {

    System.out.println("Show menu : ");
    System.out.println("1. Nhap");
    System.out.println("2. Doc");
    System.out.println("3. Bubble Sort");
    System.out.println("4. Select Sort");
    System.out.println("5. Insert Sort");
    System.out.println("6. Liner Search > Value");
    System.out.println("7. Binary Search");
    System.out.println("0. Exit");
    System.out.print("your choise: ");
  }

  private static float inputValueSearch() {
    System.out.print("nhập value: ");
    return scanner.nextFloat();
  }

}
