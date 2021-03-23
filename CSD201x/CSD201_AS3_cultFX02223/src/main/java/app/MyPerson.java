package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import app.datastructs.MyBSTree;

public class MyPerson {

  MyBSTree<Person> tree;
  MyPersonService service;

  public MyPerson() {
    tree = new MyBSTree<>();
    service = new MyPersonService(tree);
  }

  // 1.1 input and insert a new product to tree
  public void insert() {
    Person person = createPerson();
    if (service.insert(person)) {
      System.out.println("Added 1 new person.");
    }
  }

  // 1.2 in-order traverse
  public void inOrder() {
    service.inOrder();
  }

  // 1.3 BFT a tree
  public void BFT() {
    service.BFT();
  }

  public void postOrder(){
    service.postOrder();
  }

  public void preOrder(){
    service.preOrder();
  }

  // 1.4 search by ID
  public void search() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Search by ID: ");
    String id = scanner.nextLine();

    Person person = service.search(id);
    if (person != null) {
      System.out.println(person.toString());
    } else {
      System.out.println(Color.YELLOW + "Has not person by id: " + id + Color.RESET);
    }
  }

  // 1.5 delete by ID
  public void delete() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Delete person by id: ");
    String id = scanner.nextLine();

    service.delete(id);
  }

  // 1.6 simply balancing a tree
  public void balance() {
    tree.balance();
  }

  public Person createPerson() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Create a new person.");
      try {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        if (tree.contains(new Person(id))) {
          System.err.println(Color.RED + "Id ready exists, try again." + Color.RESET);
          continue;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("birthplace: ");
        String birthplace = scanner.nextLine();

        System.out.print("bdob (date/ month/year): ");
        String bdob = scanner.nextLine();
        // Validate bdob.
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(bdob);

        return new Person(id, name, birthplace, bdob);
      } catch (Exception e) {
        System.err.println(Color.RED + "Error: " + e.getMessage() + Color.RESET);
        System.out.print(Color.GREEN + "Try again? (y/n): " + Color.RESET);
        String y = scanner.nextLine();
        if (!y.equalsIgnoreCase("y")) {
          return null;
        }
      }
    }
  }

  public void seedDataForTest() {
    tree.insert(new Person("1", "Ha", "Ha noi", "12/09/90"));
    tree.insert(new Person("4", "Lan", "Ha noi", "04/04/87"));
    tree.insert(new Person("2", "An", "Nam Dinh", "12/09/90"));
    tree.insert(new Person("3", "Binh", "TH", "01/05/92"));
    tree.insert(new Person("5", "Tuan", "TB", "02/02/89"));
    
  }
}