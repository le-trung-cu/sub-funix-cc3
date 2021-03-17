package app.datastructs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorInOrder<T> implements Iterator<Node<T>> {
  private Node<T> current;
  private MyStack<Node<T>> stack;

  public IteratorInOrder(Node<T> root) {
    this.current = root;
    stack = new MyStack<>();
  }

  @Override
  public boolean hasNext() {
    return this.current != null || !stack.isEmpty();
  }

  @Override
  public Node<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    while (this.current != null) {
      this.stack.push(current);
      current = current.left;
    }
    current = stack.pop();
    Node<T> currentNode = current;
    current = current.right;
    return currentNode;
  }
}
