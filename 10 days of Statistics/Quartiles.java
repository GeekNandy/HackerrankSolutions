import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    public static int calcMedian(List<Integer> sortedArr, int start, int end) {
        int len = end - start + 1;
        return ((len%2 != 0)?(sortedArr.get((start + end)/2)):(sortedArr.get((start + end)/2) + sortedArr.get((start + end)/2 + 1))/2);
    }
    
    public static List<Integer> quartiles(List<Integer> arr) {
        Collections.sort(arr);
        int len = arr.size();
        int mid = len/2, start = 0, end = len - 1;
        List<Integer> al = new ArrayList<>();
        al.add(calcMedian(arr, start, mid - 1));
        if(len%2 != 0) {
            al.add(arr.get(mid));
            al.add(calcMedian(arr, mid + 1, end));
        }
        else {
            al.add((arr.get(mid) + arr.get(mid - 1))/2);
            al.add(calcMedian(arr, mid, end));
        }
        return al;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] dataTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int dataItem = Integer.parseInt(dataTemp[i]);
            data.add(dataItem);
        }

        List<Integer> res = Result.quartiles(data);

        for (int i = 0; i < res.size(); i++) {
            bufferedWriter.write(String.valueOf(res.get(i)));

            if (i != res.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
