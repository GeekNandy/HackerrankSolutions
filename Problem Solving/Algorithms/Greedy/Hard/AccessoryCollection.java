import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static String acessoryCollection(int L, int A, int N, int D) {
        long bestsum = 0;
        if(D<2 || N>L) bestsum = (long)L*A;
        else if(A<D || D>N) return "SAD";
        else { 
            for(int i = (int)Math.ceil((L-N+1.0)/(A-D+1.0)); i <= (N-1)/(D-1); i++) {
            int used = N-i*(D-2)-1;
            long sum = (long)used*A;
            if(D==2 && used>i) i=used;
            long num = (L-used)/i;
            sum += (num*i*(2*A-1-num))/2; 
            used += num*i;
            sum += (L-used)*(A-num-1);
            if(sum>bestsum) bestsum=sum;
            }
        }
        return (bestsum>0)?(""+bestsum):"SAD";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(scanner.nextLine().trim());

        for (int TItr = 0; TItr < T; TItr++) {
            String[] LAND = scanner.nextLine().split(" ");

            int L = Integer.parseInt(LAND[0].trim());

            int A = Integer.parseInt(LAND[1].trim());

            int N = Integer.parseInt(LAND[2].trim());

            int D = Integer.parseInt(LAND[3].trim());

            String result = acessoryCollection(L, A, N, D);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
