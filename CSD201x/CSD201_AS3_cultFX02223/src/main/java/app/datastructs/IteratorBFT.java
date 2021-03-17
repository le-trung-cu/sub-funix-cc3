package app.datastructs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorBFT<T> implements Iterator<Node<T>> {
  MyQueue<Node<T>> queue;
  Node<T> current;

  public IteratorBFT(Node<T> root) {
    this.current = root;
    queue = new MyQueue<>();
  }

  @Override
  public boolean hasNext() {
    return current != null || !queue.isEmpty();
  }

  @Override
  public Node<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    Node<T> currentNode = current;

    if(currentNode.left != null)
      queue.enqueue(current.left);
    if(currentNode.right != null)
      queue.enqueue(current.right);
      
    current = queue.dequeue();

    return currentNode;
  }
  
}
