public class MyList {

  public static class Node {
    int value;
    Node next;

    private Node(int value) {
      this.value = value;
      next = null;
    }
  }

  private Node head, tail;

  public MyList() {
    head = tail = null;
  }

  boolean isEmpty() {
    return head == null;
  }

  void clear() {
    head = tail = null;
  }

  public void add(int value) {
    Node node = new Node(value);

    if(isEmpty()){
      head = tail = node;
      return;
    }

    node.next = head;
    head = node;
  }

  public void addMany(int[] a) {
    for (int value : a) {
      add(value);
    }
  }

  public void traverse() {
    Node s = head;
    while (s != null) {
      System.out.print(s.value + " ");
      s = s.next;
    }
    System.out.print('\n');
  }

  public void search(int value) {
    MyList list = new MyList();

    Node s = head;
    int index = 0;
    while (s != null) {
      if (s.value > value) {
        list.add(index);
      }
      index++;
      s = s.next;
    }

    list.traverse();
  }
}
