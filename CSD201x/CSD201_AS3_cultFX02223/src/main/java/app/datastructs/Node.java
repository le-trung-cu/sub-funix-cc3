package app.datastructs;

public class Node<T> {
  public int bf;
  public T value;
  public int height;

  public Node<T> left, right;

  public Node(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
