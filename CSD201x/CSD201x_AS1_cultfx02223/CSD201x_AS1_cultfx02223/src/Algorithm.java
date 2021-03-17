import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Algorithm {
  int k = 0;
  // Input file
  public static final String INPUT = "INPUT.txt";
  // Output file bubble sort
  public static final String BUBBLE_SORT_OUT = "OUTPUT1.txt";
  // Output file insert sort
  public static final String INSERT_SORT_OUT = "OUTPUT2.txt";
  // Output file select sort
  public static final String SELECT_SORT_OUT = "OUTPUT3.txt";
  // Output file linear search
  public static final String LINEAR_SEARCH_OUT = "OUTPUT4.txt";

  // 1. Write data form array to file
  public static void writeFile(float a[]) throws Exception {
    try (FileOutputStream writer = new FileOutputStream("DATA.txt")) {
      writer.write(String.valueOf(a.length).getBytes());
      writer.write('\n');
      for (int i = 0; i < a.length; i++) {
        writer.write(String.valueOf(a[i]).getBytes());
        if (i < a.length - 1) {
          writer.write(',');
        }
      }
    } catch (Exception e) {
      System.err.println("Error when write file.");
      throw e;
    }
  }

  // 2. Read data from file to array
  public static float[] readFile() throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader("DATA.txt"))) {
      int n = Integer.parseInt(reader.readLine());
      Scanner scanner = new Scanner(reader.readLine());
      scanner.useDelimiter(",");

      float[] a = new float[n];
      for (int i = 0; i < n; i++) {
        a[i] = scanner.nextFloat();
      }

      return a;
    } catch (Exception e) {
      System.out.println("Error read file");
      throw e;
    }
  }

  public static String getFileContent(String file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

  public static String joinArray(String delimiter, float[] a) {
    StringBuffer strB = new StringBuffer();
    for (int i = 0; i < a.length; i++) {
      strB.append(a[i]);
      if (i < a.length - 1) {
        strB.append(delimiter);
      }
    }
    return strB.toString();
  }

  public static String joinArray(String delimiter, List<Integer> a) {
    StringBuffer strB = new StringBuffer();

    for (int i = 0; i < a.size(); i++) {
      strB.append(a.get(i));
      if (i < a.size() - 1) {
        strB.append(delimiter);
      }
    }
    return strB.toString();
  }

  private static void printTimeRun(long timeRun) {
    System.out.println("time run: " + timeRun);
  }

  // 3. Sort by Bubble Sort
  public static void bubbleSort(float a[]) {
    try (FileWriter writer = new FileWriter(BUBBLE_SORT_OUT)) {
      // writer.write(joinArray(", ", a));
      // writer.write(System.lineSeparator());
      if (a.length < 2) {
        printTimeRun(0);
        // easer old content
        writer.write("");
        return;
      }

      long timeRun = 0;
      long startTime = System.nanoTime();
      boolean sorted;
      for (int k = 0; k < a.length; k++) {
        // flag check if after loop no any element in a swap
        // then array a was sorted.
        sorted = true;
        for (int i = 1; i < a.length - k; i++) {
          if (a[i] < a[i - 1]) {
            sorted = false;
            float temp = a[i];
            a[i] = a[i - 1];
            a[i - 1] = temp;
          }
        }
        long endTime = System.nanoTime();
        timeRun += (endTime - startTime);

        writer.write(joinArray(", ", a));
        writer.write(System.lineSeparator());

        startTime = System.nanoTime();

        if (sorted) {
          printTimeRun(timeRun);
          return;
        }
      }
      printTimeRun(timeRun);
    } catch (Exception e) {
      System.err.println("Error when write file Bubble Sort");
    }

  }

  // 4. Sort bay Selection Sort
  public static void selectionSort(float a[]) {
    try (FileWriter writer = new FileWriter(SELECT_SORT_OUT)) {
      if (a.length < 2) {
        printTimeRun(0);
        // easer old content
        writer.write("");
        return;
      }
      long timeRun = 0;

      for (int i = 0; i < a.length - 1; i++) {
        long startRun = System.nanoTime();

        int min = i;
        for (int j = i + 1; j < a.length; j++) {
          if (a[j] < a[min]) {
            min = j;
          }
        }
        float temp = a[i];
        a[i] = a[min];
        a[min] = temp;
        long endStart = System.nanoTime();

        timeRun += endStart - startRun;

        writer.write(joinArray(", ", a));
        writer.write(System.lineSeparator());

      }
      printTimeRun(timeRun);

    } catch (Exception e) {
      System.err.println("Error when write file Selection Sort");
    }

  }

  // 5. Sort by Insertion Sort
  public static void insertionSort(float a[]) {
    try (FileWriter writer = new FileWriter(INSERT_SORT_OUT)) {

      if (a.length < 2) {
        printTimeRun(0);
        // easer old content
        writer.write("");
        return;
      }
      long timeRun = 0;

      for (int i = 1; i < a.length; i++) {
        long startTime = System.nanoTime();
        int k = i - 1;
        float temp = a[i];
        while (k >= 0 && temp < a[k]) {
          a[k + 1] = a[k];
          k--;
        }
        a[k + 1] = temp;
        long endTime = System.nanoTime();
        timeRun += (endTime - startTime);

        writer.write(joinArray(", ", a));
        writer.write(System.lineSeparator());
      }
      printTimeRun(timeRun);
    } catch (Exception e) {
      System.err.println("Error when write file insertion Sort");
    }

  }

  // 6. Serach by Linear Search algorith: return array index
  public static List<Integer> search(float a[], float value) {
    List<Integer> indexs = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      if (a[i] > value) {
        indexs.add(i);
      }
    }

    // Write in Output4.txt
    try (FileWriter writer = new FileWriter(LINEAR_SEARCH_OUT)) {
      writer.write(joinArray(", ", indexs));
    } catch (Exception e) {
      //TODO: handle exception
    }
    return indexs;
  }

  // 7. Search by binary Search algorith
  public static int binarySearch(float a[], int l, int r, float x) {
    if (l > r) {
      return -1;
    }
    int mid = (l + r) / 2;

    if (a[mid] < x) {
      return binarySearch(a, mid + 1, r, x);
    } else if (a[mid] > x) {
      return binarySearch(a, l, mid, x);
    } else {
      return mid;
    }
  }

}
