import java.util.*;

public class Solution {

    static void solve(final String s, final StringBuilder sb, final int index) {
        if (s.length() == index) return;
        System.out.println(sb.append(s.charAt(index)));
        Solution.solve(s, sb, index + 1);
        sb.setLength(sb.length() - 1);
        Solution.solve(s, sb, index + 1);
    }

    public static void main(final String[] args) {
        try (final Scanner in = new Scanner(System.in)) {
            for (int t = Integer.parseInt(in.nextLine()); t > 0; t--) {
                in.nextLine();
                Solution.solve(in.nextLine(), new StringBuilder(), 0);
            }
        }
    }
    
}
