import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();

        int[] children = new int[length];
        int[] candies = new int[length];

        // seed
        children[0] = scan.nextInt();
        candies[0] = 1;

        // search forward sequences
        for(int i = 1; i < length; i++) {
            children[i] = scan.nextInt();
            candies[i] = 1;
            if (children[i] > children[i - 1]) 
                candies[i] = candies[i - 1] + 1;   
        }

        // search reverse sequences
        for(int i = length - 1; i > 0; i--) {
            if (children[i] < children[i - 1]) candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
        }

        long sum = 0;
        for(int i= 0; i < candies.length; i++) sum += candies[i];

        System.out.println(sum);
    }
}
