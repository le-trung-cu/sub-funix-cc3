import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteProduct {
  private WriteProduct() {
  }

  public static void writeToFile(String fileName, MyList<Product> list) throws Exception {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      int line = 0;
      for (Product product : list) {

        String productStr = String.format("%s,%s,%d,%.2f", product.getId(), product.getName(), product.getQuantity(),
            product.getPrice());
        if (line != 0) {
          writer.newLine();
        }
        line = 1;
        writer.write(productStr);
      }
    } catch (Exception e) {
      System.err.println("Error when write products to file " + fileName);
      throw e;
    }
  }
}
