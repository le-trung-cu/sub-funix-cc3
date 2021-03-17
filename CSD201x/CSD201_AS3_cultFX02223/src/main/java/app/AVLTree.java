package app;

import java.util.ArrayList;
import java.util.Stack;

public class AVLTree<K extends Comparable<K>, V> {
  public class Node {
    public int bf;
    public K key;
    public V value;
    public int height;

    public Node left, right;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return key.toString() + ": " + value.toString();
    }
  }

  private Node root;

  private int nodeCount = 0;

  public int height() {
    if (root == null)
      return 0;
    return root.height;
  }

  public int size() {
    return nodeCount;
  }

  public boolean empty() {
    return size() == 0;
  }

  // Check if 1 key exists in the tree.
  public boolean contains(K key) {
    if (empty() || key == null)
      return false;
    return contains(this.root, key);
  }

  // Contains helper method
  private boolean contains(Node root, K key) {
    if (root == null) {
      return false;
    }
    // Compare current value to the value in the node.
    int cmp = key.compareTo(root.key);
    // Find on right subtree.
    if (cmp > 0)
      return contains(root.right, key);
    // Find on left subtree.
    if (cmp < 0)
      return contains(root.left, key);
    // Found value in tree.
    return true;
  }

  // Get node's value by key return node's value if exists key in tree,
  // else return null if not found.
  public V get(K key) {
    return get(this.root, key);
  }

  // Get helper method
  private V get(Node root, K key) {
    if (root == null)
      return null;
    int cmp = key.compareTo(root.key);
    if (cmp > 0)
      return get(root.right, key);
    if (cmp < 0)
      return get(root.left, key);
    return root.value;
  }

  // insert into tree then return true if inserted
  // and return false if key exists in tree
  public boolean insert(K key, V value) {
    if (!contains(key)) {
      this.root = insert(this.root, key, value);
      this.nodeCount++;
      return true;
    }
    return false;
  }

  // Insert helper method
  public Node insert(Node root, K key, V value) {
    if (root == null) {
      return new Node(key, value);
    }
    // Compare current key to the key in the node
    int cmp = key.compareTo(root.key);

    // Insert node in right subtree.
    if (cmp > 0) {
      root.right = insert(root.right, key, value);

      // Insert node in left subtree.
    } else {
      root.left = insert(root.left, key, value);
    }

    // Update balance factor and height values.
    update(root);

    return balance(root);
  }

  public boolean remove(K key) {
    if (contains(key)) {
      this.root = remove(this.root, key);
      this.nodeCount--;
      return true;
    }
    return false;
  }

  private Node remove(Node root, K key) {
    if (root == null)
      return null;
    int cmp = key.compareTo(root.key);

    if (cmp > 0) {
      root.right = remove(root.right, key);
    } else if (cmp < 0) {
      root.left = remove(root.left, key);
    }
    // Found the node to remove.
    else {
      // If current node's left is null, swap current node with right child.
      if (root.left == null)
        return root.right;
      // Else if current node's right is null, swap current node with left child.
      else if (root.right == null)
        return root.left;
      // Else: in this situation current node has both left child and right child.
      else {
        // When removing a node from a binary tree with two links the
        // successor of the node being removed can either be the largest
        // value in the left subtree or the smallest value in the right
        // subtree. I will remove from the subtree with
        // the greatest height in hopes that this may help with balancing.

        // Remove from left subtree
        if (root.left.height > root.right.height) {
          // Swap value and key of successor into removed node.
          Node successor = findNodeHasKeyMax(root.left);
          root.key = successor.key;
          root.value = successor.value;

          // Remove leaf node
          root.left = remove(root.left, successor.key);

          // Remove from left subtree
        } else {
          // Swap value and key of successor into removed node.
          Node successor = findNodeHasKeyMin(root.right);
          root.key = successor.key;
          root.value = successor.value;

          // Remove leaf node
          root.right = remove(root.right, successor.key);
        }
      }

    }

    update(root);
    return balance(root);
  }

  private Node findNodeHasKeyMax(Node root) {
    while (root.right != null) {
      root = root.right;
    }
    return root;
  }

  private Node findNodeHasKeyMin(Node root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  public ArrayList<K> preorder() {
    ArrayList<K> result = new ArrayList<>();
    if (this.root == null) {
      return result;
    }

    Stack<Node> stack = new Stack<>();
    stack.add(this.root);

    while (!stack.empty()) {
      Node current = stack.pop();
      if (current.right != null) {
        stack.add(current.right);
      }
      if (current.left != null) {
        stack.add(current.left);
      }
      result.add(current.key);
    }

    return result;
  }

  // #region Re-Balance tree
  // Re-balance a node if its balance factor is +2 or -2
  private Node balance(Node root) {
    if (root.bf == -2) {
      if (root.left.bf <= 0)
        return leftLeftCase(root);
      else
        return leftRightCase(root);
    } else if (root.bf == +2) {
      if (root.right.bf >= 0)
        return rightRightCase(root);
      else
        return rightLeftCase(root);
    }

    return root;
  }

  // Update node's height and balance factor
  private void update(Node root) {
    // Height node left
    int leftHeight = (root.left != null) ? root.left.height : -1;
    // Height node right
    int rightHeight = (root.right != null) ? root.right.height : -1;

    // Update this node's height.
    root.height = 1 + Math.max(leftHeight, rightHeight);
    // Update balance factor.
    root.bf = rightHeight - leftHeight;
  }

  // Left left case balance.
  private Node leftLeftCase(Node root) {
    return rightRotate(root);
  }

  // Right right case balance.
  private Node rightRightCase(Node root) {
    return leftRotate(root);
  }

  // Left right case balance.
  private Node leftRightCase(Node root) {
    root.left = leftRotate(root.left);
    return leftLeftCase(root);
  }

  // Right left case balance.
  private Node rightLeftCase(Node root) {
    root.right = rightRotate(root.right);
    return rightRightCase(root);
  }

  // Right rotate tree
  private Node rightRotate(Node root) {
    Node newParent = root.left;
    root.left = newParent.right;
    newParent.right = root;

    // Update root's height and balance factor
    // and then update newParent's height and balance factor.
    // Order is important.
    update(root);
    update(newParent);
    return newParent;
  }

  // Left rotate tree
  private Node leftRotate(Node root) {
    Node newParent = root.right;
    root.right = newParent.left;
    newParent.left = root;

    // Update root's height and balance factor
    // and then update newParent's height and balance factor.
    // Order is important.
    update(root);
    update(newParent);
    return newParent;
  }
  // #endregion
}
