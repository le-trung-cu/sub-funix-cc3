package app;

import java.io.IOException;
import java.util.Scanner;

import app.datastructs.Graph;

public class App {
    public static void showMenu() {

        System.out.println("Choose one of this options:");

        System.out.println("Person Tree:");

        System.out.println("1.  Insert a new Person.");

        System.out.println("2.  In-order traverse");

        System.out.println("3.  Breadth-First traversal");

        System.out.println("4.  Search by Person ID");

        System.out.println("5.  Delete by Person ID");

        System.out.println("6.  Balancing Binary Search Tree ");

        System.out.println("7.  DFS_Graph");

        System.out.println("8.  Dijkstra from A -> E");

        System.out.println("9.  BFS_Graph");

        System.out.println("10.  Post-order traverse");

        System.out.println("11. Pre-order traverse");

        // System.out.println("12. seed data for test");

        System.out.println("Exit:");

        System.out.println("0. Exit");

        System.out.print("choice = ");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyPerson personManager = new MyPerson();

        Graph graph = new Graph();

        int chose;
        do {
            showMenu();
            chose = scanner.nextInt();
            switch (chose) {
            case 1:
                personManager.insert();
                break;
            case 2:
                personManager.inOrder();
                break;
            case 3:
                personManager.BFT();
                break;
            case 4:
                personManager.search();
                break;
            case 5:
                personManager.delete();
                break;
            case 6:
                personManager.balance();
                break;
            case 7:
                try {
                    graph.setWeights("graph-weighted.txt");
                    System.out.print("Start at (0 - 6): ");
                    graph.DFS(scanner.nextInt());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 8:
                // Dijkstra from A (0) -> E(4)
                try {
                    graph.setWeights("graph-weighted.txt");
                    graph.displayWeights();
                    graph.dijkstra(0, 4);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 9:
                try {
                    graph.setWeights("graph-weighted.txt");
                    System.out.print("Start at (0 - 6): ");
                    graph.BFS(scanner.nextInt());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 10:
                personManager.postOrder();
                break;
            case 11:
                personManager.preOrder();
                break;
            // case 12:
            //     personManager.seedDataForTest();
            default:
                break;
            }
        } while (chose != 0);
    }
}
