import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaximumPerimeterTriangle {

    static int[] maximumPerimeterTriangle(int[] sticks) {
        Arrays.sort(sticks);
        int len = sticks.length;
        int ptr1 = len-1, ptr2 = len-2, ptr3 = len-3;
        
        while(true) {
            if((sticks[ptr1]+sticks[ptr2])>sticks[ptr3]
                && (sticks[ptr3]+sticks[ptr2])>sticks[ptr1]
                && (sticks[ptr1]+sticks[ptr3])>sticks[ptr2]) break;  
            else {
                ptr1--;
                ptr2--;
                ptr3--;
                if(ptr3<0) return new int[]{-1};
            }
        }
        return new int[]{sticks[ptr3], sticks[ptr2], sticks[ptr1]};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] sticks = new int[n];

        String[] sticksItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int sticksItem = Integer.parseInt(sticksItems[i]);
            sticks[i] = sticksItem;
        }

        int[] result = maximumPerimeterTriangle(sticks);

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
