import java.util.Scanner;

public class Solution {

    static long mod = 1000000007;
    static int hehe = 314159;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        char[] a = cin.next().toCharArray();
        char[] b = cin.next().toCharArray();

        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            char cb = a[i];
            a[i] = a[j];
            a[j] = cb;
        }
        for (int i = 0, j = b.length - 1; i < j; i++, j--) {
            char cb = b[i];
            b[i] = b[j];
            b[j] = cb;
        }

        int zero[] = new int[b.length];
        int one[] = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '1') one[i] = 1;
            else zero[i] = 1;

            if (i > 0) {
                zero[i] += zero[i - 1];
                one[i] += one[i - 1];
            }
        }

        long w = 1;
        long ans = 0;
        int v[] = new int[hehe + b.length];
        for (int i = 0; i < hehe + b.length; i++) {
            if (i >= a.length || a[i] == '0') {
                if (i < b.length) v[i] = one[i];
                else {
                    if (i <= v.length - b.length) v[i] = one[b.length - 1];
                    else v[i] = one[b.length - 1] - (b.length - 1 - (v.length - i) < 0 ? 0 : one[b.length - 1 - (v.length - i)]);
                }
            } else {
                // safe to do that
                v[i] = v.length - 1 - (i + b.length) + 1;
                if (i < b.length) v[i] += zero[i];
                else v[i] += zero[b.length - 1] + (i - b.length + 1);
            }
        }

        for (int i = 0; i < v.length; i++) {
            ans = (ans + v[i] * w) % mod;
            w = w * 2 % mod;
        }

        System.out.println(ans);

        cin.close();
    }

    static class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
        final U _1;
        final V _2;

        private Pair(U key, V val) {
            this._1 = key;
            this._2 = val;
        }

        public static <U extends Comparable<U>, V extends Comparable<V>> Pair<U, V> instanceOf(U _1, V _2) {
            return new Pair<U, V>(_1, _2);
        }

        @Override
        public String toString() {
            return _1 + " " + _2;
        }

        @Override
        public int hashCode() {
            int res = 17;
            res = res * 31 + _1.hashCode();
            res = res * 31 + _2.hashCode();
            return res;
        }

        @Override
        public int compareTo(Pair<U, V> that) {
            int res = this._1.compareTo(that._1);
            if (res < 0 || res > 0) return res;
            else return this._2.compareTo(that._2);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Pair)) return false;
            Pair<?, ?> that = (Pair<?, ?>) obj;
            return _1.equals(that._1) && _2.equals(that._2);
        }
    }
}
