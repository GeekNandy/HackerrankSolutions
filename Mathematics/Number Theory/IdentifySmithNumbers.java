import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static int solve(int n) {
        int sod = 0, sof = 0, c = n;
        String digs = "";
        while(c>0) {
            sod+=(c%10);
            c/=10;
        }
        for(int i=2; (n/i)>0;) {
            if(n%i!=0) i++;
            else {
                digs+=(""+i);
                n/=i;
            }
        }
        for(int i=0; i<digs.length(); i++) {
            sof+=(digs.charAt(i) - '0');
        }
        return (sod!=sof)?0:1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = solve(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
