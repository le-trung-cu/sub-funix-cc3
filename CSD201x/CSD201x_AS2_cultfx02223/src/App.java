import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.commons.io.output.TeeOutputStream;

public class App {
    public static void main(String[] args) throws Exception {
        String fileLog = "console_output.txt";
        try {
            FileOutputStream fos = new FileOutputStream(fileLog);
            Runtime.getRuntime().addShutdownHook( new Thread(){
                @Override
                public void run() {
                        try {
                    fos.flush();
                }
                catch (Throwable t) {
                    // Ignore
                }
                }
            });
            //we will want to print in standard "System.out" and in "file"
            TeeOutputStream myOut=new TeeOutputStream(System.out, fos);
            
            PrintStream ps = new PrintStream(myOut, true); //true - auto-flush after println
            System.setOut(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        applicationLoop();
        
    }

    public static void applicationLoop() {
        Scanner scanner = new Scanner(System.in);
        OperationToProduct service = new OperationToProduct();
        MyList<Product> list = new MyList<>();
        MyQueue<Product> queue = new MyQueue<>();
        MyStack<Product> stack = new MyStack<>();
        String fileName = "Data.txt";
        while (true) {
            try {

                showMenu();
                int menuItem = scanner.nextInt();
                if (menuItem == 0) {
                    break;
                }

                switch (menuItem) {
                    case 1:
                        service.getAllItemsFromFile(fileName, list);
                        service.displayAll(list);
                        break;
                    case 2:
                        service.addLast(list);
                        break;
                    case 3:
                        service.displayAll(list);
                        break;
                    case 4:
                        service.writeAllItemsToFile(fileName, list);
                        break;
                    case 5:
                        service.searchByCode(list);
                        break;
                    case 6:
                        service.deleteByCode(list);
                        break;
                    case 7:
                        service.sortByCode(list);
                        break;
                    case 8:
                        if(!list.isEmpty()){
                            Product first = list.get(0);
                            System.out.println("Quantity = " + first.getQuantity() + " => " + CoverToBinary.cover(first.getQuantity()));
                        }else{
                            System.out.println("Is empty!");
                        }
                        
                        break;
                    case 9:
                        service.getAllItemsFromFile(fileName, stack);
                        service.displayAll(stack);
                        break;
                    case 10:
                        service.getAllItemsFromFile(fileName, queue);
                        service.displayAll(queue);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void showMenu() {

        System.out.println("Choose one of this options:");

        System.out.println("Product list:");

        System.out.println("1. Load data from file and display");

        System.out.println("2. Input & add to the end.");

        System.out.println("3. Display data");

        System.out.println("4. Save product list to file.");

        System.out.println("5. Search by ID");

        System.out.println("6. Delete by ID");

        System.out.println("7. Sort by ID.");

        System.out.println("8. Convert to Binary");

        System.out.println("9. Load to stack and display");

        System.out.println("10. Load to queue and display.");

        System.out.println("Exit:");

        System.out.println("0. Exit");
    }
}
