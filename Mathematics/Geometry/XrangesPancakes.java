import java.util.Scanner;

public class Solution {
  public static void main(final String[] args) {
    try (final Scanner in = new Scanner(System.in)) {
      int type = 0, a = 0;
      for (int n = in.nextInt(), m = in.nextInt(); m > 0; m--) {
        type ^= in.nextInt() - 1;
        a += (2 * type - 1) * in.nextInt() + n;
        a %= n;
      }
      System.out.println(type + 1 + " " + a);
    }
  }
}
