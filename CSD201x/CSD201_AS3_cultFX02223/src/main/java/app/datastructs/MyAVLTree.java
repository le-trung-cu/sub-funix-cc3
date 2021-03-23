package app.datastructs;

// Ignore this file.
public class MyAVLTree<T extends Comparable<T>> {

  private Node<T> root;
  private int nodeCount;

  // Insert value into tree return true if inserted and false if value is exists.
  public boolean insert(T value) {
    if (value == null || !contains(value)) {
      return false;
    }
    this.root = insert(this.root, value);
    this.nodeCount++;
    return true;
  }

  public boolean remove(T value) {
    if (value == null || !contains(value)) {
      return false;
    }
    this.root = remove(this.root, value);
    this.nodeCount--;
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

    // Update Height and Balance Factor of current node.
    // Bottom up.
    update(root);

    // Re-Balance tree.
    return balance(root);
  }

  // Remove helper method
  private Node<T> remove(Node<T> root, T value) {
    if (root == null)
      return root;
    int cmp = value.compareTo(root.value);
    // Remove in right subtree.
    if (cmp > 0)
      root.right = remove(root.right, value);
    // Remove in left subtree.
    else if (cmp < 0)
      root.left = remove(root, value);
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
      // We will decide by height of child nodes
      else {
        // Swap removing node with node has biggest value on left subtree.
        if (root.left.height > root.right.height) {
          Node<T> temp = findMax(root.left);
          root.value = temp.value;
          remove(root.left, temp.value);
        }
        // Swap removing node with node has smallest value on right subtree.
        else {
          Node<T> temp = findMax(root.right);
          root.value = temp.value;
          remove(root.right, temp.value);
        }
      }
    }

    update(root);
    return balance(root);
  }

  private Node<T> findMax(Node<T> root) {
    while (root.right != null) {
      root = root.right;
    }
    return root;
  }

  // Re-Balance tree.
  private Node<T> balance(Node<T> root) {
    if (root.bf == -2) {
      if (root.left.bf <= 0) {
        return leftLeftCase(root);
      } else {
        return leftRightCase(root);
      }
    } else if (root.bf == +2) {
      if (root.right.bf >= 0) {
        return rightRightCase(root);
      } else {
        return rightLeftCase(root);
      }
    }
    return null;
  }

  private Node<T> rightLeftCase(Node<T> root) {
    root.right = rightRotate(root.right);
    return rightRightCase(root);
  }

  private Node<T> rightRightCase(Node<T> root) {
    return leftRotate(root);
  }

  private Node<T> leftRightCase(Node<T> root) {
    root.left = leftRotate(root.left);
    return leftLeftCase(root);
  }

  private Node<T> leftLeftCase(Node<T> root) {
    return rightRotate(root);
  }

  private Node<T> rightRotate(Node<T> root) {
    Node<T> newParent = root.left;
    root.left = newParent.right;
    newParent.right = root;

    // Update node after rotate.
    // Order is important.
    update(root);
    update(newParent);

    return newParent;
  }

  private Node<T> leftRotate(Node<T> root) {
    Node<T> newParent = root.right;
    root.right = newParent.left;
    newParent.left = root;

    // Update node after rotate.
    // Order is important.
    update(root);
    update(newParent);

    return newParent;
  }

  // Update Height and Balance Factor of node
  private void update(Node<T> root) {
    // Update height
    int leftHeight = (root.left != null) ? root.left.height : -1;
    int rightHeight = (root.right != null) ? root.right.height : -1;
    root.height = 1 + Math.max(leftHeight, rightHeight);

    // Update Balance Factor
    root.bf = leftHeight - rightHeight;
  }

  // Check if value is exists in tree.
  public boolean contains(T value) {
    return contains(this.root, value);
  }

  // Contains helper method.
  private boolean contains(Node<T> root, T value) {
    if (root == null)
      return false;
    int cmp = value.compareTo(root.value);
    if (cmp > 0)
      return contains(root.right, value);
    if (cmp < 0)
      return contains(root.left, value);
    return true;
  }

  public void clear() {
    this.root = null;
  }

  public Node<T> getRoot() {
    return this.root;
  }

}
