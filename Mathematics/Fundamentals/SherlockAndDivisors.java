import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            long n=sc.nextLong();
            int count=(n%2!=0)?0:1;
            for(long i=2;i<=Math.sqrt(n);i++) {
                if(n%i==0 && i%2==0) count++;
                if(n%(n/i)==0 && ((n/i)!=i) && ((n/i)%2==0)) count++;
            }
            System.out.println(count);
        }
    }
}
