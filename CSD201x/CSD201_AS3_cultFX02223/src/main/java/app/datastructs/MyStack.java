package app.datastructs;

import java.util.LinkedList;

public class MyStack<T> {
  LinkedList<T> a;

  MyStack() {
    a = new LinkedList<>();
  }

  // Returns true if this collection contains no elements.
  boolean isEmpty() {
    return a.isEmpty();
  }

  //Pushes an element onto the stack represented by this list
  void push(T x) {
    a.push(x);
  }

  // Pops an element from the stack represented by this list
  public T pop() {
    return a.pop();
  }

}