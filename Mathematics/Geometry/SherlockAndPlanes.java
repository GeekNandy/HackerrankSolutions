import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static String solve(int[][] points) {
        int xp = points[0][0], yp = points[0][1], zp = points[0][2];
        boolean x = true, y = true, z = true;
        for(int i=1; i<points.length; i++) {
            if(x && xp != points[i][0]) x=false;
            if(y && yp != points[i][1]) y=false;
            if(z && zp != points[i][2]) z=false;
        }
        return (x||y||z)?"YES":"NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int[][] points = new int[4][3];

            for (int pointsRowItr = 0; pointsRowItr < 4; pointsRowItr++) {
                String[] pointsRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int pointsColumnItr = 0; pointsColumnItr < 3; pointsColumnItr++) {
                    int pointsItem = Integer.parseInt(pointsRowItems[pointsColumnItr]);
                    points[pointsRowItr][pointsColumnItr] = pointsItem;
                }
            }

            String result = solve(points);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
