
import java.util.Iterator;

import app.datastructs.MyBSTree;
import app.datastructs.Node;

public class TestTravel {
    static MyBSTree<Integer> tree;

    public static void mainTest(String[] args) {
        tree = new MyBSTree<>();
        tree.insert(6);
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(8);
        tree.insert(7);
        tree.insert(10);
        tree.insert(9);
        inOrder();
        postOrder();
        preOrder();
        BFT();
    }

    private static void inOrder(){
        System.out.println("in order");
        Iterator<Node<Integer>> iterator = tree.iteratorInOrder();
        while (iterator.hasNext()) {
            Node<Integer> i = iterator.next();
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static void postOrder(){
        System.out.println("post order");
        Iterator<Node<Integer>> iterator = tree.iteratorPostOrder();
        while (iterator.hasNext()) {
            Node<Integer> i = iterator.next();
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    private static void preOrder(){
        System.out.println("pre order");
        Iterator<Node<Integer>> iterator = tree.iteratorPreOrder();
        while (iterator.hasNext()) {
            Node<Integer> i = iterator.next();
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static void BFT(){
        System.out.println("BFS order");
        Iterator<Node<Integer>> iterator = tree.iteratorBFT();
        while (iterator.hasNext()) {
            Node<Integer> i = iterator.next();
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
