import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void whatFlavors(int[] cost, int money) {
        Set<Integer> s = new HashSet<>();
        for(int i: cost) s.add(i);
        int ic1 = -1, ic2 = -1;
        for(int i: s) {
            if(s.contains(money-i)) {
                ic1 = i;
                ic2 = money-i;
                break;
            }
        }
        for(int i=0, ctr=0; i<cost.length; i++) {
            if(cost[i]==ic1 || cost[i]==ic2) {
                ctr++;
                System.out.print((i+1)+" ");
                if(ctr>1) break;
            }
        }
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
