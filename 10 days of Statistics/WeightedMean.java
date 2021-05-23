import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    
    public static void weightedMean(List<Integer> X, List<Integer> W) {
        int len = X.size();
        double num = 0.0, den = 0.0;
        for(int i = 0; i < len; i++) {
            num += (X.get(i) * W.get(i));
            den += W.get(i);
        }
        double wtm = (double) num/den;
        System.out.printf("%.1f", wtm);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] valsTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> vals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int valsItem = Integer.parseInt(valsTemp[i]);
            vals.add(valsItem);
        }

        String[] weightsTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> weights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int weightsItem = Integer.parseInt(weightsTemp[i]);
            weights.add(weightsItem);
        }

        Result.weightedMean(vals, weights);

        bufferedReader.close();
    }
}
