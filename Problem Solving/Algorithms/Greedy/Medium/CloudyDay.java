import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Event implements Comparable<Event> {
    int type;
    long x;
    long pr;
    int index;

    public Event(int type, long x, long pr, int index) {
        super();
        this.type = type;
        this.x = x;
        this.pr = pr;
        this.index = index;
    }

    @Override
    public int compareTo(Event e) {
        if (x != e.x) {
            return Long.compare(x, e.x);
        }
        return Integer.compare(type, e.type);
    }

}


public class Solution {

    static long maximumPeople(long[] p, long[] x, long[] y, long[] r) {
        long free = 0;
        long[] sum = new long[y.length];
        ArrayList<Event> al = new ArrayList<>();
        HashSet<Integer> clouds = new HashSet<>();
        for (int i = 0; i < p.length; i++) {
            al.add(new Event(1, x[i], p[i], i));
        }

        for (int i = 0; i < y.length; i++) {
            al.add(new Event(0, y[i] - r[i], -1, i));
            al.add(new Event(2, y[i] + r[i], -1, i));
        }

        Collections.sort(al);

        for (Event e : al) {
            if (e.type == 0) {
                clouds.add(e.index);
            } else if (e.type == 1) {
                if (clouds.isEmpty()) {
                    free += e.pr;
                } else {
                    if (clouds.size() == 1) {
                        for (int q : clouds) {
                            sum[q] += e.pr;
                        }
                    }
                }
            } else {
                clouds.remove(e.index);
            }
        }

        long mx = 0;
        for (long i : sum) {
            mx = Math.max(mx, i);
        }
        return free + mx;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] p = new long[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long pItem = Long.parseLong(pItems[i]);
            p[i] = pItem;
        }

        long[] x = new long[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long xItem = Long.parseLong(xItems[i]);
            x[i] = xItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] y = new long[m];

        String[] yItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long yItem = Long.parseLong(yItems[i]);
            y[i] = yItem;
        }

        long[] r = new long[m];

        String[] rItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long rItem = Long.parseLong(rItems[i]);
            r[i] = rItem;
        }

        long result = maximumPeople(p, x, y, r);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
