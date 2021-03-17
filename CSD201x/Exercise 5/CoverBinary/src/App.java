public class App {
    public static void main(String[] args) throws Exception {
        MyStack stack = coverToBinary(-77);

        stack.foreach(new MyStack.Func() {
            @Override
            public void handle(int value) {
                System.out.print(value);
            }
        });
    }

    static MyStack coverToBinary(int x) {
        x = Math.abs(x);
        MyStack stack = new MyStack();
        if (x == 0) {
            stack.push(0);
        }
        while (x > 0) {
            int du = x % 2;
            stack.push(du);
            x = x / 2;
        }

        return stack;
    }
}
