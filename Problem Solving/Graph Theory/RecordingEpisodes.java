import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int z = 0; z < q; z++) {
            int n = sc.nextInt();
            int[] sl = new int[n];
            int[] el = new int[n];
            int[] sr = new int[n];
            int[] er = new int[n];
            ArrayList<HashSet<Integer>> outward = new ArrayList<HashSet<Integer>>();
            ArrayList<HashSet<Integer>> inward = new ArrayList<HashSet<Integer>>();
            int minimum = 0;
            int anslow = 1;
            int anshigh = 1;
            for (int i = 0; i < n; i++) {
                outward.add(new HashSet<Integer>());
                outward.add(new HashSet<Integer>());
                inward.add(new HashSet<Integer>());
                inward.add(new HashSet<Integer>());
                sl[i] = sc.nextInt();
                el[i] = sc.nextInt();
                sr[i] = sc.nextInt();
                er[i] = sc.nextInt();
                for (int j = minimum; j < i; j++) {
                    if ((sl[i] >= sl[j] && sl[i] <= el[j])||(sl[j] >= sl[i] && sl[j] <= el[i])) {
                        outward.get(i*2).add(j*2+1);
                        outward.get(j*2).add(i*2+1);
                        inward.get(i*2+1).add(j*2);
                        inward.get(j*2+1).add(i*2);
                    }
                    if ((sl[i] >= sr[j] && sl[i] <= er[j])||(sr[j] >= sl[i] && sr[j] <= el[i])) {
                        outward.get(i*2).add(j*2);
                        outward.get(j*2+1).add(i*2+1);
                        inward.get(i*2+1).add(j*2+1);
                        inward.get(j*2).add(i*2);
                    }
                    if ((sr[i] >= sl[j] && sr[i] <= el[j])||(sl[j] >= sr[i] && sl[j] <= er[i])) {
                        outward.get(i*2+1).add(j*2+1);
                        outward.get(j*2).add(i*2);
                        inward.get(i*2).add(j*2);
                        inward.get(j*2+1).add(i*2+1);
                    }
                    if ((sr[i] >= sr[j] && sr[i] <= er[j])||(sr[j] >= sr[i] && sr[j] <= er[i])) {
                        outward.get(i*2+1).add(j*2);
                        outward.get(j*2+1).add(i*2);
                        inward.get(i*2).add(j*2+1);
                        inward.get(j*2).add(i*2+1);
                    }
                }
                while (!scc2sat(outward, inward, minimum)) {
                    for (int j : outward.get(minimum*2)) {
                        inward.get(j).remove(minimum*2);
                    }
                    for (int j : inward.get(minimum*2)) {
                        outward.get(j).remove(minimum*2);
                    }
                    for (int j : outward.get(minimum*2+1)) {
                        inward.get(j).remove(minimum*2+1);
                    }
                    for (int j : inward.get(minimum*2+1)) {
                        outward.get(j).remove(minimum*2+1);
                    }
                    minimum++;
                    break;
                }
                if (i-minimum > anshigh-anslow) {
                    anshigh = i+1;
                    anslow = minimum+1;
                }
            }
            System.out.println(anslow+" "+anshigh);
        }
    }

    public static boolean scc2sat(ArrayList<HashSet<Integer>> outward, ArrayList<HashSet<Integer>> inward, int minimum) {
        ArrayDeque<Integer> l = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[outward.size()];
        for (int i = minimum*2; i < outward.size(); i++) {
            visit(outward,i,visited,l);
        }
        int[] components = new int[outward.size()];
        for (int i = 0; i < outward.size(); i++)
            components[i] = -1;
        while (!l.isEmpty()) {
            int i = l.removeFirst();
            assign(inward, components, i, i);
        }
        for (int i = minimum*2; i < outward.size(); i+=2) {
            if (components[i]==components[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static void visit(ArrayList<HashSet<Integer>> outward, int u, boolean[] visited, ArrayDeque<Integer> l) {
        if (!visited[u]) {
            visited[u] = true;
            for (int v : outward.get(u))
                visit(outward,v,visited,l);
            l.addFirst(u);
        }
    }

    public static void assign(ArrayList<HashSet<Integer>> inward, int[] components, int u, int root) {
        if (components[u] == -1) {
            components[u] = root;
            for (int v : inward.get(u))
                assign(inward, components, v, root);
        }
    }
}
