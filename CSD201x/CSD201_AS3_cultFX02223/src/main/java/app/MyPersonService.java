package app;

import java.util.Iterator;

import app.datastructs.MyBSTree;
import app.datastructs.Node;

public class MyPersonService {

  MyBSTree<Person> tree;

  public MyPersonService(MyBSTree<Person> tree) {
    this.tree = tree;
  }

  public boolean insert(Person person) {
    return person != null && tree.insert(person);
  }

  // 1.2 in-order traverse
  public void inOrder() {
    Iterator<Node<Person>> iterator = tree.iteratorInOrder();
    while (iterator.hasNext()) {
      Node<Person> current = iterator.next();
      System.out.println(current.value.toString());
    }
  }

  // post-order traverse
  public void postOrder(){
    Iterator<Node<Person>> iterator = tree.iteratorPostOrder();
    while (iterator.hasNext()) {
      Node<Person> current = iterator.next();
      System.out.println(current.value.toString());
    }
  }
  // pre-order traverse
  public void preOrder(){
    Iterator<Node<Person>> iterator = tree.iteratorPreOrder();
    while (iterator.hasNext()) {
      Node<Person> current = iterator.next();
      System.out.println(current.value.toString());
    }
  }


  // 1.3 BFT a tree
  public void BFT() {
    Iterator<Node<Person>> iterator = tree.iteratorBFT();
    while (iterator.hasNext()) {
      Node<Person> current = iterator.next();
      System.out.println(current.value.toString());
    }
  }

  // 1.4 search by ID
  public Person search(String id) {
    Person person = new Person(id);
    Person personFromTree = tree.search(person);
    return personFromTree;
  }

  // 1.5 delete by ID
  public void delete(String id) {
    Person person = new Person(id);
    if (tree.delete(person)) {
      System.out.println("Deleted 1 person.");
    } else {
      System.out.println(Color.YELLOW + "Has not person by id: " + id + Color.RESET);
    }
  }

  // 1.6 simply balancing a tree
  public void balance() {
    tree.balance();
  }
}
