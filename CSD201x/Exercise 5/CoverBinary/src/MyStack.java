import java.util.EmptyStackException;

public class MyStack {
  private static class Node {
    int value;
    Node next;

    public Node(int val) {
      value = val;
    }

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  @FunctionalInterface
  public interface Func{
    public void handle(int value);
  }

  private Node head;

  public MyStack() {
    head = null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void push(int value) {
    head = new Node(value, head);
  }

  public int pop() {
    if(isEmpty()){
      throw new EmptyStackException();
    }

    int value = head.value;
    head = head.next;
    return value;
  }

  public void foreach(Func m) {
    Node top = head;
    while(top != null){
      m.handle(top.value);
      top = top.next;
    }
  }
}