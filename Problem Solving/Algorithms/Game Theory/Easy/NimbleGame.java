import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int r = 0;
            for (int i = 0; i < n; i++) 
                if (scanner.nextInt() % 2 == 1) r ^= i;
            System.out.println((r != 0)?"First":"Second");
        }
    }
}
