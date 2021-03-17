import java.io.FileReader;
import java.util.Scanner;

public class ReadProduct {
  private ReadProduct() {
  }

  public static void readInto(String filename, IList<Product> products) throws Exception {
    Scanner scannerToken = null;

    try (Scanner scannerLine = new Scanner(new FileReader(filename))){
      while (scannerLine.hasNextLine()) {
        String line = scannerLine.nextLine();
        scannerToken = new Scanner(line);
        scannerToken.useDelimiter(",");
        String id = scannerToken.next();
        String name = scannerToken.next();
        int quantity = scannerToken.nextInt();
        double price = scannerToken.nextDouble();
        Product product = new Product(id, name, quantity, price);
        products.add(product);

        scannerToken.close();
      }
    } catch (Exception e) {
      System.err.println("Error when read product from file: " + filename);
      throw e;
    } finally {
      if (scannerToken != null) {
        scannerToken.close();
      }
    }
  }
}
