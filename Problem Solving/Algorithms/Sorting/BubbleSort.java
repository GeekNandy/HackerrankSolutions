import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void countSwaps(int[] a) {
        int n = a.length;
        int ctr = 0;
        boolean flag = true;
        for(int i=1; ; i++) {
            if(i>=n) {
                if(flag) break;
                flag = true;
                n--;
                i=1;
            }
            if(a[i-1]>a[i]) {
                flag = false;
                a[i-1] = a[i-1]+a[i];
                a[i] = a[i-1]-a[i];
                a[i-1] = a[i-1]-a[i];
                ctr++;
            }
        }
        System.out.println("Array is sorted in "+ctr+" swaps.");
        System.out.println("First Element: "+a[0]);
        System.out.println("Last Element: "+a[a.length-1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
