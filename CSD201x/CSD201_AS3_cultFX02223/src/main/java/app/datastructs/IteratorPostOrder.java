package app.datastructs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorPostOrder<T> implements Iterator<Node<T>> {

    private Node<T> lastNodeVisited;
    private Node<T> current;
    private MyStack<Node<T>> stack;

    public IteratorPostOrder(Node<T> root) {
        this.current = root;
        stack = new MyStack<>();
    }

    @Override
    public boolean hasNext() {
        return this.current != null || !stack.isEmpty();
    }

    @Override
    public Node<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (!stack.isEmpty() || this.current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node<T> peekNode = stack.peek();
                // if right child exists and traversing node
                // from left child, then move right
                if(peekNode.right != null && lastNodeVisited != peekNode.right){
                    current = peekNode.right;
                }else {
                    lastNodeVisited = stack.pop();
                    return peekNode;
                }
            }
        }

        return null;
    }

}
