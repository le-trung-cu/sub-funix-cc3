public class MyList<T> {
  Node<T> head, tail;
  int length;

  @FunctionalInterface
  public interface Comparator<T> {
    public int compare(T o1, T o2);
  }

  public static class Node<T> {
    T value;
    Node<T> next;

    private Node(T value) {
      this.value = value;
      next = null;
    }

  }

  public MyList() {
    head = tail = null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void clear() {
    head = tail = null;
    length = 0;
  }

  public void add(T value) {
    var node = new Node<T>(value);
    length++;
    if (isEmpty()) {
      head = tail = node;
      return;
    }
    tail.next = node;
    tail = node;
  }

  public void addMany(T[] arr) {
    for (T value : arr) {
      add(value);
      length++;
    }
  }

  public void traverse() {
    Node s = head;
    while (s != null) {
      System.out.println(s.value.toString());
      s = s.next;
    }
  }

  public void sort(Comparator<T> comparator) {
    if (length <= 1) {
      return;
    }

    // danh sách có các phần tử từ head -> prevRandSorted đã được sắp xếp
    Node<T> prevRandSorted = head;
    Node<T> currentNode;
    while (prevRandSorted.next != null) {
      currentNode = prevRandSorted.next;

      if (comparator.compare(currentNode.value, prevRandSorted.value) >= 0) {
        prevRandSorted = prevRandSorted.next;
        continue;
      }

      Node<T> startNode = head;
      Node<T> preStartNode = null;

      while (comparator.compare(currentNode.value, startNode.value) > 0 && currentNode != startNode.next) {
        preStartNode = startNode;
        startNode = startNode.next;
      }
      // chèn vào đầu danh sách
      if (preStartNode == null) {
        prevRandSorted.next = currentNode.next;
        currentNode.next = head;
        head = currentNode;
        continue;
      }
      // chèn vào giữa danh sách
      prevRandSorted.next = currentNode.next;
      currentNode.next = startNode;
      preStartNode.next = currentNode;
      continue;

    }

  }
}
