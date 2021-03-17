import java.util.Scanner;

public class OperationToProduct {

  // Search index of element of product in Linked list, if not founf then return-1
  public int index(Product p, MyList<Product> list) {
    int index = 0;
    for (Product e : list) {
      if (e == p) {
        return index;
      }
      index++;
    }

    return -1;
  }

  // Cread a product have:(ID, name, quantity, price) that input from keyboard
  public Product createProduct() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Input Id: ");
    String id = scanner.nextLine();

    System.out.print("Input Name: ");
    String name = scanner.nextLine();

    System.out.print("Input quantity: ");
    int quantity = scanner.nextInt();

    System.out.print("Input price: ");
    double price = scanner.nextDouble();

    Product product = new Product(id, name, quantity, price);

    return product;
  }

  // Read all products from file and save to Linked List ( Insert at tail of
  // Linked List), info of a product in a line
  public void getAllItemsFromFile(String fileName, IList<Product> list) {
    try {
      ReadProduct.readInto(fileName, list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Add a new product into tail of Linked List
  public void addLast(MyList<Product> list) {
    Product p = createProduct();
    list.insertToTail(p);
  }

  // Display info of all product on Linked list
  public void displayAll(MyList<Product> list) {
    for (Product product : list) {
      displayProduct(product);
    }
  }

  // Wite all product of Linked List to file
  public void writeAllItemsToFile(String fileName, MyList<Product> list) throws Exception {
    WriteProduct.writeToFile(fileName, list);
  }

  // Search first element by ID
  public Product searchByCode(MyList<Product> list) {
    System.out.print("Search product by Id: ");
    String id = getString();
    for (Product product : list) {
      if (product.getId().equals(id)) {
        displayProduct(product);
        return product;
      }
    }

    System.out.println("Not found product by Id: " + id);
    System.out.println(-1);
    return null;
  }

  // Delete first element by ID (=ID)
  public void deleteByCode(MyList<Product> list) {
    System.out.print("Delete product by id: ");
    String id = getString();

    Product p = null;
    for (Product product : list) {
      if (product.getId().equals(id)) {
        p = product;
        break;
      }
    }

    if (p != null) {
      System.out.println("1 product deleted");
      list.deleteElement(p);
      return;
    }

    System.out.println("Not found product by Id: " + id);

  }

  // Sort by ID
  public void sortByCode(MyList<Product> list) {
    list.insertionSort(list, new MyList.ICompare<Product>() {
      @Override
      public int comp(Product o1, Product o2) {
        return o1.getId().compareTo(o2.getId());
      }
    });
    System.out.println("List products sorted by Id:");

    displayAll(list);
  }

  // Add new product to head of Linked list
  public void addFirst(MyList<Product> list) {
    Product product = createProduct();
    if (product != null) {
      list.insertToHead(product);
      System.out.println("1 product was add.");
    }
  }

  // Cover to binary
  public void convertBinary(int i) {
    System.out.println(CoverToBinary.cover(i));
  }

  // Delete element at position k
  public void deletePosition(MyList<Product> list) {
    System.out.println("Delete element at position K: ");
    int k = getInt();

    list.deleteAt(k);

    for (Product product : list) {
      System.out.println(product.toString());
    }
  }

  public void displayProduct(MyList<Product> list) {
    for (Product product : list) {
      displayProduct(product);
    }
  }

  public void displayProduct(Product product) {
    System.out.println(product.toString());
  }

  private String getString() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  private int getInt() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public void displayAll(MyStack<Product> stack) {
    while (!stack.isEmpty()) {
      Product product = stack.pop();
      displayProduct(product);
    }
  }

  public void displayAll(MyQueue<Product> queue) {
    while (!queue.isEmpty()) {
      Product product = queue.poll();
      displayProduct(product);
    }
  }
}
