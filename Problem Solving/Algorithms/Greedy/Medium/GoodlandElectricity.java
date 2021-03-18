import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int pylons(int k, int[] a) {
        int min_towers = 0;
        int village = 0;
        int provider = village + k - 1;
        while(village < a.length) {            
            if(a[provider] == 1) {
                village = provider + k;
                provider = village + k - 1;                
                if(provider >= a.length) {
                    provider = a.length - 1;
                }
                min_towers++;
            } else {
                provider--;
                if(provider < village - k + 1 || provider < 0) {
                    return -1;
                }
            }
        }
        return min_towers;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pylons(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
