import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        Map<Integer, Integer> freqs = new HashMap<>();
        int mode = Integer.MIN_VALUE, sum = 0;
        int[] arr = new int[N];
        float median = 0, mean = 0;
        for(int i = 0; i < N; i++) {
            int num = s.nextInt();
            arr[i] = num;
            sum += num;
            if(freqs.containsKey(num)) {
                int v = freqs.get(num);
                v += 1;
                freqs.put(num, v);
            }
            else freqs.put(num, 1);
            int curFreq = freqs.get(num);
            int modeFreq = (freqs.containsKey(mode))?(freqs.get(mode)):0;
            if(curFreq > modeFreq) mode = num;
            else if(curFreq == modeFreq) mode = Math.min(mode, num);
        }
        mean = ((float) sum)/((float) N);
        System.out.println(mean);
        //median = ((float) mode + 2.0f*mean)/3.0f; -> empirical formula doesn't work because mode isn't taken the proper conventional way
        Arrays.sort(arr);
        median = (N%2 != 0)?(arr[N/2]):((arr[N/2] + arr[(N/2) - 1])/2.0f);
        System.out.println(median);
        System.out.println(mode);
    }
}
