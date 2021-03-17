import java.util.EmptyStackException;

public class MyStack<T> implements IList<T> {
  Node<T> head;

  public MyStack() {
    head = null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void push(T x) {
    head = new Node<>(x, head);
  }

  public T pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    Node<T> top = head;
    head = head.next;
    return top.info;
  }

  @Override
  public void add(T x) {
    push(x);
  }
}
