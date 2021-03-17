package app.datastructs;

import java.util.LinkedList;

public class MyQueue<T> {

  LinkedList<T> a = null;

  public MyQueue() {
    a = new LinkedList<>();
  }

  // append element into this queue.
  public void enqueue(T obj) {
    a.add(obj);
  }

  // Returns true if this collection contains no elements.
  public boolean isEmpty() {
    return a.isEmpty();
  }

  /**
   * retrieves and removes the head of this queue. 
   * @return the head of this list, or null if this list is empty
   */
  public T dequeue() {
    return a.poll();
  }

  /**
   * Retrieves, but does not remove, the head element of this Queue, or returns
   * {@code null} if this queue is empty.
   *
   * @return the head element of this queue, or {@code null} if this list is empty
   */
  public T front() {
    return a.peekFirst();
  }

}