import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Cin in = new Cin();
        int t = in.nextInt();
        while(t-->0) {
            int n = in.nextInt();
            int[] num = in.nextArr(n);
            int g = num[0];
            boolean ok = false;
            for(int i = 1; i < n; i++) {
                g = gcd(g,num[i]);
                if(g == 1) {
                    ok = true;
                    break;
                }
            }
            out.print((ok)?"YES\n":"NO\n");
        }
        out.close();
    }
    static int gcd(int a,int b) {
        while(b != 0) {
            int t = b;
            b = a%b;
            a = t;
        }
        return a;
    }

    static PrintWriter out = new PrintWriter(System.out);
    static class Cin {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while(!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        String nextLine() {
            StringBuilder strb = new StringBuilder();
            try {
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()) {
                    strb.append(st.nextToken());
                    strb.append(" ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
            return strb.toString().trim();
        }
        int nextInt() {return Integer.parseInt(next());}
        long nextLong() {return Long.parseLong(next());}
        int[] nextArr(int n) {
            int[] r = new int[n];
            for(int i = 0; i < n; i++) r[i] = nextInt();
            return r;
        }
    }
}
