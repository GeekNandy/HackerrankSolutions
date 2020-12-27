import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Pair implements Comparable<Pair> {
    int index;
    int val;
    
    Pair(int i, int v) {
        index = i;
        val = v;
    }
    
    public int compareTo(Pair p) {
        if(this.val>p.val) return 1;
        else if(this.val==p.val) {
            if(this.index>p.index) return 1;
            else return -1;
        }
        else return -1;
    }
}

public class JimAndTheOrders {

    static int[] jimOrders(int[][] orders) {
        int n = orders.length;
        List<Pair> al = new ArrayList<>();
        int[] sol = new int[n];
        for(int i=0; i<n; i++) {
            int o = orders[i][0];
            int t = orders[i][1];
            al.add(new Pair(i+1, o+t));
        }
        Collections.sort(al);
        for(int i=0; i<n; i++) {
            sol[i] = (al.get(i)).index;
        }
        return sol;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] orders = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] ordersRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int ordersItem = Integer.parseInt(ordersRowItems[j]);
                orders[i][j] = ordersItem;
            }
        }

        int[] result = jimOrders(orders);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
