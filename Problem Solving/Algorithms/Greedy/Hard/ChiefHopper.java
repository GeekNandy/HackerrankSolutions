import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        int[] heights = new int[N];
        for (int i=0; i<N; i++) {
            heights[i] = sc.nextInt();
        }
        double at_least_needed = 0;
        double energy = 0;
        for (int i=N-1; i>=0; i--) {
            energy = Math.ceil((at_least_needed + heights[i])/2.0);
            at_least_needed = energy;
        }
        System.out.println((int) energy);
    }
}
