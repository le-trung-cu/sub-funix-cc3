public class Node<T> {
  T info;
  Node<T> next;
  // 1. Constructor method
  public Node() {
  }

  public Node(T info) {
    this.info = info;
    this.next = null;
  }

  public Node(T info, Node<T> next) {
    this.info = info;
    this.next = next;
  }

  // 2. toString method using for display infor of product
}