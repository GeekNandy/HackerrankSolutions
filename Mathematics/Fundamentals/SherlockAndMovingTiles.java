import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class SherlockAndMovingTiles {

    static double[] movingTiles(int l, int s1, int s2, long[] queries) {
        int len = queries.length;
        double D = Math.sqrt(2);
        D = D*l;
        double[] sol = new double[len];
        for(int i=0; i<len; i++) {
            sol[i] = (D-Math.sqrt(2*queries[i]))/Math.abs(s1-s2);
        }
        return sol;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lS1S2 = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int l = Integer.parseInt(lS1S2[0]);

        int s1 = Integer.parseInt(lS1S2[1]);

        int s2 = Integer.parseInt(lS1S2[2]);

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        long[] queries = new long[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            long queriesItem = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            queries[queriesItr] = queriesItem;
        }

        double[] result = movingTiles(l, s1, s2, queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
