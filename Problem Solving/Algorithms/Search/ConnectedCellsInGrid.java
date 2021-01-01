import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    static int rs(int[][] matrix , int i , int j) {
        if(i<0 || j<0 || i >= matrix.length || j >= matrix[i].length) return 0;
        if(matrix[i][j] == 0) return 0;
        int size = 1;
        matrix[i][j] = 0;
        for(int r = i-1; r<=i+1;r++) {
            for(int c = j-1; c <= j+1; c++) {
                if(r != i || c != j) size += rs(matrix,r,c);
            }
        }
        return size;
    }

    static int connectedCell(int[][] matrix) {
        int max = 0, n = matrix.length, m = matrix[0].length;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(matrix[i][j] == 1) {
                    int size = rs(matrix, i, j);
                    max = Math.max(size, max);
                }
            }
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
