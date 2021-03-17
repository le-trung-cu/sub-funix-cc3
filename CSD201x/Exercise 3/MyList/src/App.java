public class App {
    public static void main(String[] args) throws Exception {
        MyList t = new MyList();
        int[] a = { 7, 2, 9, 8, 6, 3 };
        t.addMany(a);
        System.out.print("\n Traverse: ");
        t.traverse();
        System.out.print("\n Search(8):");
        t.search(8);
        System.out.println();
    }
}
