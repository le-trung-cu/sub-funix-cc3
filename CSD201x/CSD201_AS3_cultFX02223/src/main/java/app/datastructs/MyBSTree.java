package app.datastructs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class MyBSTree<T extends Comparable<T>> {

  private Node<T> root;
  private int nodeCount;

  // count number of products
  public int count() {
    return nodeCount;
  }

  // count number of products
  public int count(Node<T> root) {
    if (root == null)
      return 0;
    return 1 + count(root.left) + count(root.right);
  }

  public void BFT() {
    BFT(this.root);
  }

  // Breadth-First traverse a tree.
  public void BFT(Node<T> root) {
    if (root == null) {
      return;
    }
    MyQueue<Node<T>> queue = new MyQueue<>();
    queue.enqueue(root);
    while (!queue.isEmpty()) {
      Node<T> current = queue.dequeue();
      if (current.left != null) {
        queue.enqueue(current.left);
      }
      if (current.right != null) {
        queue.enqueue(current.right);
      }
      System.out.println(current.value.toString());
    }
  }

  // Insert value into tree return true if inserted and false if value is exists.
  public boolean insert(T value) {
    if (value == null || contains(value)) {
      return false;
    }
    this.root = insert(this.root, value);
    this.nodeCount++;
    return true;
  }

  // Insert helper method
  private Node<T> insert(Node<T> root, T value) {
    // Insert node as a leaf node.
    if (root == null)
      return new Node<>(value);
    // compare current value of current node to value.
    int cmp = value.compareTo(root.value);

    // Insert in right subtree.
    if (cmp > 0) {
      root.right = insert(root.right, value);

      // Insert in left subtree.
    } else if (cmp < 0) {
      root.left = insert(root.left, value);
    } else {
      // Value is exists in tree not insert.
      return root;
    }
    return root;
  }

  // Call mehtod inOrder(ArrayList<Person1> t, Node p) for copy all elements from
  // tree to arraylist
  // Then call method balance(ArrayList<Person1> list, int f, int l) để Insett
  // ArrayList<Person1> into tree
  public void balance() {
    ArrayList<T> inOrder = new ArrayList<>();
    inOrder(inOrder, this.root);
    clear();
    balance(inOrder, 0, inOrder.size());
  }

  // Balance helper method.
  // Insert mid values in range[h,l] into tree.
  private void balance(ArrayList<T> list, int h, int l) {
    if (h >= l) {
      return;
    }
    int mid = (h + l - 1) / 2;
    T value = list.get(mid);
    insert(value);
    balance(list, h, mid);
    balance(list, mid + 1, l);
  }

  // return min value on tree
  private Node<T> findMin(Node<T> root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  // Return max value.
  // private Node<T> findMax(Node<T> root) {
  //   while (root.right != null) {
  //     root = root.right;
  //   }
  //   return root;
  // }

  // Check if value is exists in tree.
  public boolean contains(T value) {
    return search(value) != null;
  }

  public void inOrder() {
    ArrayList<T> list = new ArrayList<>();
    inOrder(list, this.root);
    for (T t : list) {
      System.out.println(t.toString());
    }
  }

  // balance a tree
  // step 1: traverse inorder tree and copy all item on tree to an arraylist
  // step 2: insert all items of list to a tree
  public void inOrder(ArrayList<T> t, Node<T> root) {
    if (root == null)
      return;
    inOrder(t, root.left);
    t.add(root.value);
    inOrder(t, root.right);
  }

  // search a Node
  // return null if given code does not exists
  public T search(T value) {
    return search(this.root, value);
  }

  // delete a node has value.
  // Remove value in key, return false if tree has not exists value
  public boolean delete(T value) {
    if (value == null || !contains(value)) {
      return false;
    }
    this.root = delete(this.root, value);
    this.nodeCount--;
    return true;
  }

  // Remove helper method
  private Node<T> delete(Node<T> root, T value) {
    if (root == null)
      return root;
    int cmp = value.compareTo(root.value);
    // Remove in right subtree.
    if (cmp > 0)
      root.right = delete(root.right, value);
    // Remove in left subtree.
    else if (cmp < 0)
      root.left = delete(root, value);
    // Found node to remove.
    else {
      // In situation node's left or node's right is null
      // We just need swap current node with right child or left child.
      if (root.left == null)
        return root.right;
      if (root.right == null)
        return root.left;
      // If removing node has both left child and right child
      // We can swap removing node with node has smallest value on right subtree
      // or swap removing node with node has biggest value on left subtree.
      else {
        Node<T> temp = findMin(root.right);
        root.value = temp.value;
        delete(root.right, temp.value);
      }
    }

    return root;
  }

  // Search helper method.
  private T search(Node<T> root, T value) {
    if (root == null)
      return null;
    int cmp = value.compareTo(root.value);
    if (cmp > 0)
      return search(root.right, value);
    if (cmp < 0)
      return search(root.left, value);
    return root.value;
  }

  public void clear() {
    this.root = null;
  }

  public ArrayList<T> preOrder() {
    ArrayList<T> result = new ArrayList<>();
    if (this.root == null) {
      return result;
    }

    Stack<Node<T>> stack = new Stack<>();
    stack.add(this.root);

    while (!stack.empty()) {
      Node<T> current = stack.pop();
      if (current.right != null) {
        stack.add(current.right);
      }
      if (current.left != null) {
        stack.add(current.left);
      }
      result.add(current.value);
    }

    return result;
  }

  public Node<T> getRoot() {
    return this.root;
  }

  public Iterator<Node<T>> iteratorInOrder() {
    return new IteratorInOrder<>(this.root);
  }

  public Iterator<Node<T>> iteratorBFT(){
    return new IteratorBFT<>(this.root);
  }

}
