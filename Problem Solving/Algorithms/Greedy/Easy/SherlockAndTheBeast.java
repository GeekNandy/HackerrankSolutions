import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndTheBeast {

    static void decentNumber(int n) {
        int t = n;
        while(t>0 && t%3!=0) {
            t-=5;
        }
        if(t<0) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<t; i++) {
                sb.append("5");
            }
            for(int i=0; i<(n-t); i++) {
                sb.append("3");
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            decentNumber(n);
        }

        bufferedReader.close();
    }
}
