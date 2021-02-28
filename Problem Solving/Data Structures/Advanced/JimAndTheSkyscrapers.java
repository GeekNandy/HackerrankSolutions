import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static long solve(int[] arr) {
        long[] count = new long[1000001];
        long result = 0;
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(300001);

        int tmp;
        for (int i = 0; i < n; i++) {
            if (arr[i] > stack.peek()) {
                while(arr[i] > stack.peek()) {
                    tmp = stack.pop();
                    result += ((count[tmp] - 1) * count[tmp]);
                    count[ tmp ] = 0;
                }
                if (stack.peek() != arr[i]) {
                    stack.push(arr[i]);
                }
                count[ arr[i] ]++;
            } else if (arr[i] == stack.peek()) {
                count[ arr[i] ]++;
            } else {
                stack.push(arr[i]);
                count[ arr[i] ] = 1;
            }
        }

        while(!stack.empty()) {
            tmp = stack.pop();
            if (count[tmp] > 1) {
                result += ((count[tmp] - 1) * count[tmp]);
            }
            count[tmp] = 0;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr]);
            arr[arrItr] = arrItem;
        }

        long result = solve(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
