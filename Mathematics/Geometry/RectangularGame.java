import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        long x = 0, y = 0, z = 0;
        int n = scanner.nextInt();
        while(n-->0) {
            long x0 = scanner.nextInt();
            long y0 = scanner.nextInt();
            if(z == 0) {
                x = x0;
                y = y0;
                z = 1;
            }
            else {
                if(x0<x) x = x0;
                if(y0<y) y = y0;
            }
        }
        System.out.println((long)x*y);
        scanner.close();
    }
}
