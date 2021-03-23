package app.datastructs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorPreOrder<T> implements Iterator<Node<T>> {
    private MyStack<Node<T>> stack; 
    private Node<T> current;

    
    public IteratorPreOrder(Node<T> root) {
        this.current = root;
        stack = new MyStack<>();
        if(root != null){
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (!stack.isEmpty() ) {
            current = stack.pop();
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
            return current;
        }
        
        return null;
    }
    
}
