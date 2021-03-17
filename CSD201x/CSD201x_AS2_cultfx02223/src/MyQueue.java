public class MyQueue<T> implements IList<T> {
  Node<T> head, tail;

  public MyQueue() {
    head = tail = null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void add(T x) {
    Node<T> newNode = new Node<>(x);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  /**
   * It is used to retrieves and removes the head of this queue, or returns null
   * if this queue is empty.
   * 
   * @return
   */
  public T poll() {
    if (isEmpty()) {
      return null;
    }
    Node<T> result = head;
    head = head.next;
    return result.info;
  }

  /**
   * It is used to retrieves, but does not remove, the head of this queue, or
   * returns null if this queue is empty.
   * 
   * @return
   */
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return head.info;
  }
}
