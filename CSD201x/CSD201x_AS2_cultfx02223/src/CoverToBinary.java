public class CoverToBinary {

  private CoverToBinary() {

  }

  public static String cover(int x) {
    boolean negative = x < 0;
    x = Math.abs(x);
    if (x < 2) {
      return negative ? "-" + x : "" + x;
    }
    MyStack<Integer> mStack = new MyStack<>();

    while (x > 0) {
      int m = x % 2;
      x = x / 2;
      mStack.push(m);
    }

    StringBuilder result = new StringBuilder();
    if (negative) {
      result.append("-");
    }
    while (!mStack.isEmpty()) {
      result.append(mStack.pop().toString());
    }
    return result.toString();
  }

  public static String coverRecur(int x) {

    return x >= 0 ? coverRecurPositive(x) : "-" + coverRecurPositive(-x);
  }

  private static String coverRecurPositive(int x) {
    if (x < 2) {
      return String.valueOf(x);
    }
    return coverRecur(x / 2) + x % 2;
  }
}
