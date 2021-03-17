public class Product {
  // Declare fields of Product
  private String id, name;
  private int quantity;
  private double price;

  // 2. Constructor mehod
  public Product(String id, String name, int quantity, double price) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public Product() {

  }

  // 3. toString method using for display
  @Override
  public String toString() {
    return String.format("|%-5s|%-15s| %,15.2f| %,8d|", id, name, price, quantity);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
