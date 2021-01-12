import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int powerSum(int X, int N) {
        int upto = (int) (Math.pow(X, 1.0/N)) +1;
        int[] arr = new int[X+1];
        arr[0] = 1;
        for(int index = 1; index <= upto; ++index){
            int pow = (int)Math.pow(index, N);
            for(int j = X; j >= pow; j--){
                arr[j] += arr[j-pow];
            }
        }
        return arr[X];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
