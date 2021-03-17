import java.util.Iterator;

public class MyList<T> implements Iterable<T>, IList<T> {
  ICompare<T> compare;

  interface ICompare<T> {
    int comp(T o1, T o2);
  }

  Node<T> head;
  Node<T> tail;
  private int size = -1;

  public int getSize() {
    return size;
  }

  public T get(int index) throws IndexOutOfBoundsException{
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException("IndexOutOfBoundsException index = " + index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.info;
  }

  // Constructor method
  public MyList(Node<T> head, Node<T> tail) {
    this.head = head;
    this.tail = tail;
  }

  public MyList() {

  }

  public boolean isEmpty() {
    return head == null;
  }

  // Size of LinkedList
  public int length() {
    if (head == null) {
      return 0;
    }

    if (size == -1) {
      size = 1;
      Node<T> pointer = head;
      while (pointer != tail) {
        pointer = pointer.next;
        size++;
      }
    }

    return size;
  }

  // Insert to head of Linked List
  public void insertToHead(T x) {
    if (this.isEmpty()) {
      head = new Node<>(x);
      tail = head;
    } else {
      Node<T> newHead = new Node<>(x, this.head);
      this.head = newHead;
    }

    this.size++;
  }

  public void insertToTail(T x) {
    if (this.isEmpty()) {
      insertToHead(x);
      return;
    }

    tail.next = new Node<>(x);
    tail = tail.next;
    size++;
  }

  // Insert after of position k
  public void insertAfterPosition(int position, T x) {
    if (position > size || position < 0) {
      throw new IndexOutOfBoundsException("IndexOutOfBoundsException index = " + position);
    }

    if (position == 0) {
      insertToHead(x);
      return;
    }

    if (position == size) {
      insertToTail(x);
      return;
    }

    Node<T> pointer = new Node<>(null, head);
    for (int i = 0; i < position; i++) {
      pointer = pointer.next;
    }
    Node<T> nodeInsert = new Node<>(x, pointer.next);
    pointer.next = nodeInsert;
    size++;
  }

  // Delete element at tail of Linked List
  public void deleteTail() {
    if (this.head == tail) {
      this.head = null;
      this.tail = null;
      size = 0;
    } else {
      Node<T> pointer = new Node<>(null, head);
      while (pointer.next != tail) {
        pointer = pointer.next;
      }
      pointer.next = null;
      tail = pointer.next;
      size--;
    }
  }

  // Delete element at tail of Linked List
  public void deleteHead() {
    if (this.head == tail) {
      this.head = null;
      this.tail = null;
      size = 0;
    } else {
      head = head.next;
      size--;
    }
  }

  public void deleteAt(int position) {
    if (position < 0 || position >= size) {
      throw new IndexOutOfBoundsException("IndexOutOfBoundsException index = " + position);
    }
    if (position == 0) {
      deleteHead();
    } else if (position == size - 1) {
      deleteTail();
    } else {
      Node<T> pointer = new Node<>(null, head);
      for (int i = 0; i < position; i++) {
        pointer = pointer.next;
      }
      pointer.next = pointer.next.next;
      size--;
    }
  }

  // Delete element has value= x
  public void deleteElement(T x) {
    if (isEmpty()) {
      return;
    }
    if (size == 1) {
      head = null;
      tail = null;
      size = 0;
      return;
    }

    Node<T> pointer = new Node<>(null, head);

    while (pointer.next != null && pointer.next.info != x) {
      pointer = pointer.next;
    }

    if (pointer.next != null) {
      if (pointer.next == head) {
        // delete head element
        head = head.next;
      } else if (pointer.next == tail) {
        // delete tail element
        tail = pointer;
        pointer.next = null;
      } else {
        pointer.next = pointer.next.next;
      }

      size--;
    }

  }

  // swap 2 elements
  public void swap(Node<T> x1, Node<T> x2) {
    Node<T> preX;
    int position = 0;

    preX = new Node<>(null, head);

    // position of x1
    while (preX.next != null && preX.next != x1) {
      preX = preX.next;
      position++;
    }
    // if list contain x1, remove x1, insert value of x2
    if (preX.next != null) {
      preX.next = preX.next.next;
      insertAfterPosition(position, x2.info);
    }

    // position of x2
    while (preX.next != null && preX.next != x2) {
      preX = preX.next;
      position++;
    }
    // if list contain x1, remove x1, insert value of x2
    if (preX.next != null) {
      preX.next = preX.next.next;
      insertAfterPosition(position, x1.info);
    }
  }

  // Delete all Linked List
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public void insertionSort(MyList<T> list, ICompare<T> compare) {
    this.head = insertionSort(list.head, compare);
  }

  private Node<T> insertionSort(Node<T> head, ICompare<T> compare) {

    if (head == null) {
      return head;
    }
    // p was point to a sorted list (p was a sorted list).
    Node<T> p = insertionSort(head.next, compare);

    // insert head node into sorted list p.
    if (p == null) {
      return head;
    }
    Node<T> q = new Node<>(null, p);
    while (q.next != null && compare.comp(head.info, q.next.info) > 0) {
      q = q.next;
    }

    // insert at head
    if (q.next == p) {
      head.next = p;
      return head;
    }
    // insert at tail
    if (q.next == null) {
      this.tail.next = head;
      this.tail = head;
      head.next = null;
      return p;
    }
    // insert between head and this.tail
    Node<T> temp = q.next;
    q.next = head;
    head.next = temp;
    return p;
  }

  @Override
  public Iterator<T> iterator() {
    return new NodeIterator<>(head);
  }

  public class NodeIterator<T> implements Iterator<T> {
    Node<T> currentNode;

    public NodeIterator(Node<T> head) {
      currentNode = head;
    }

    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    @Override
    public T next() {
      T info = currentNode.info;
      currentNode = currentNode.next;
      return info;
    }

  }

  @Override
  public void add(T x) {
    insertToTail(x);
  }

}