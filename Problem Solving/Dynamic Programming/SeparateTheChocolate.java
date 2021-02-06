import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int T = 0;
    static int D = 1;
    static int U = -1;
    
    static class Cell {
        int piece;  //0..1
        int groupIndex; //0...
        
        public Cell(int piece, int groupIndex) {
            this.piece = piece;
            this.groupIndex = groupIndex;
        }
        
        public String toString() {
            if(piece == 0) {
                return "" + (char)('a' + groupIndex);
            } else {
                return "" + (char)('A' + groupIndex);
            }
        }
        
        public boolean equals(Object o) {
            Cell other = (Cell)o;
            return piece == other.piece && groupIndex == other.groupIndex;
        }
        
        public int hashCode() {
            return 31 * piece + groupIndex;
        }

        public Cell copy() {
            return new Cell(piece, groupIndex);
        }
    }
    
    static class LineState {
        Cell[] cells;
        boolean isHidingGroup = false;

        public LineState(Cell[] cells) {
            this.cells = cells.clone();
        }
        
        public LineState(int template, int width) {
            cells = new Cell[width];
            for (int i = 0; i < width; i++) {
                cells[i] = new Cell(template % 2, -1);
                template /= 2;
            }
        }
        
        public boolean isMatch(int[] constraint) {
            for (int i = 0; i < cells.length; i++) {
                if(constraint[i] >= 0 && constraint[i] != cells[i].piece) {
                    return false;
                }
            }
            return true;
        }
        
        public int countOnes() {
            int c = 0;
            for (int i = 0; i < cells.length; i++) {
                c += cells[i].piece;
            }
            return c;
        }
        
        public boolean isOfTwoPieces() {
            if(isHidingGroup && isUniform()) return true;
            for (int i = 0; i < cells.length; i++) {
                if(cells[i].groupIndex > 0) return false;
            }
            return true;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Cell c: cells) {
                sb.append(c);
            }
            if(isHidingGroup) sb.append("!");
            return sb.toString();
        }
        
        public boolean equals(Object o) {
            LineState other = (LineState)o;
            return toString().equals(other.toString());
        }
        
        public LineState copy() {
            Cell[] cs = new Cell[cells.length];
            for (int i = 0; i < cs.length; i++) {
                cs[i] = cells[i].copy();
            }
            LineState copy = new LineState(cs);
            copy.isHidingGroup = isHidingGroup;
            return copy;
        }
        
        boolean isUniform() {
            int p = cells[0].piece;
            for (int i = 1; i < cells.length; i++) {
                if(cells[i].piece != p) return false;
            }
            return true;
        }
        
        public void revalidate() {
            int g0 = 20;
            int g1 = 20;

            for (int i = 0; i < cells.length; i++) {
                int g = cells[i].groupIndex;
                if(g >= 0) continue;
                int p = cells[i].piece;
                if(p == 0) {
                    for (int j = i; j < cells.length && cells[j].piece == p && cells[j].groupIndex < 0; j++) {
                        cells[j].groupIndex = g0;
                    }
                    g0++;
                } else {
                    for (int j = i; j < cells.length && cells[j].piece == p && cells[j].groupIndex < 0; j++) {
                        cells[j].groupIndex = g1;
                    }
                    g1++;
                }
            }
            
            int[] m0 = new int[cells.length + 20];
            int[] m1 = new int[cells.length + 20];
            for (int i = 0; i < m0.length; i++) {
                m0[i] = -1;
                m1[i] = -1;
            }
            g0 = 0;
            g1 = 0;
            
            for (int i = 0; i < cells.length; i++) {
                int g = cells[i].groupIndex;
                if(g < 0) continue;
                if(cells[i].piece == 0) {
                    if(m0[g] >= 0) {
                        cells[i].groupIndex = m0[g];
                    } else {
                        m0[g] = g0;
                        cells[i].groupIndex = g0;
                        g0++;
                    }
                } else {
                    if(m1[g] >= 0) {
                        cells[i].groupIndex = m1[g];
                    } else {
                        m1[g] = g1;
                        cells[i].groupIndex = g1;
                        g1++;
                    }
                }
            }
            
        }
    }
    
    int width;
    int height;
    int diff;
    int[][] constraint;
    
    Map<String, LineState> states = new HashMap<>();
    Map<String, Set<String>> transfers = new HashMap<>();
    Map<String, Map<Integer,Long>> counts = new HashMap<>();
    
    public void setSize(int width, int height, int diff) {
        this.width = width;
        this.height = height;
        this.diff = diff;
    }
    
    public void setConstraint(int[][] constraint) {
        this.constraint = constraint;
    }

    
    public List<LineState> createState() {
        List<LineState> states = new ArrayList<>();
        int t = 0;
        Cell[] cells = new Cell[width];
        int[] way = new int[width + 1];
        int[] wayCount = new int[width + 1];
        Cell[][] ways = new Cell[width + 1][width + 1];
        ways[0][0] = new Cell(0, 0);
        ways[0][1] = new Cell(1, 0);
        wayCount[0] = 2;
        way[t] = -1;
        while(true) {
            while(way[t] == wayCount[t] - 1) {
                if(t == 0) return states;
                t--;
            }
            way[t]++;
            cells[t] = ways[t][way[t]];
            t++;
            wayCount[t] = 0;
            if(t < width) {
                int p = cells[t-1].piece;
                ways[t][0] = new Cell(p,  cells[t-1].groupIndex); //same
                wayCount[t]++;
                List<Cell> gStack = new ArrayList<>();
                int g = -1;
                for (int i = 0; i < t; i++) {
                    int j = gStack.indexOf(cells[i]);
                    if(j >= 0) {
                        while(gStack.size() > j + 1) gStack.remove(gStack.size() - 1);
                    } else {
                        gStack.add(cells[i]);
                        if(cells[i].piece == 1 - p) {
                            if(cells[i].groupIndex > g) g = cells[i].groupIndex;
                        }
                    }
                }
                for (Cell c: gStack) {
                    if(c.piece == 1 - p) {
                        ways[t][wayCount[t]] = c;
                        wayCount[t]++;
                    }
                }
                ways[t][wayCount[t]] = new Cell(1-p,  g+1);
                wayCount[t]++;
            } else {
                states.add(new LineState(cells));
            }
            way[t] = -1;
        }
    }
    
    //to - template
    public LineState transfer(LineState from, LineState to) {
        if(from.isHidingGroup) {
            if(width == 1 && from.cells[0].piece == to.cells[0].piece) {
                to = to.copy();
                to.isHidingGroup = true;
                to.revalidate();
                return to;
            }
            return null;
        }
        for (int i = 0; i < from.cells.length - 1; i++) {
            int p = from.cells[i].piece;
            if(p == from.cells[i + 1].piece && p == to.cells[i].piece
                     && p == to.cells[i + 1].piece) {
                return null; //square 2x2
            }
        }
        from = from.copy();
        to = to.copy();
        for (int i = 0; i < from.cells.length; i++) {
            to.cells[i].groupIndex = -1;
        }
        for (int i = 0; i < from.cells.length; i++) {
            int p = from.cells[i].piece;
            int g1 = from.cells[i].groupIndex;
            int g2 = to.cells[i].groupIndex;
            if(p == to.cells[i].piece && g1 != g2) {
                if(g2 >= 0) {
                    for (int j = 0; j < from.cells.length; j++) {
                        int ga = (g1 < g2) ? g1 : g2;
                        int gb = (g1 > g2) ? g1 : g2;
                        if(p == from.cells[j].piece && gb == from.cells[j].groupIndex) {
                            from.cells[j].groupIndex = ga;
                        }
                        if(p == to.cells[j].piece && gb == to.cells[j].groupIndex) {
                            to.cells[j].groupIndex = ga;
                        }
                    }
                } else {
                    to.cells[i].groupIndex = g1;
                    int j = i + 1;
                    while(j < from.cells.length && to.cells[j].piece == p) {
                        to.cells[j].groupIndex = g1;
                        j++;
                    }
                    j = i - 1;
                    while(j >= 0 && to.cells[j].piece == p) {
                        to.cells[j].groupIndex = g1;
                        j--;
                    }
                }
            }
        }
        Set<Cell> accounted = new HashSet<>();
        for (int i = 0; i < from.cells.length; i++) {
            if(from.cells[i].piece == to.cells[i].piece) {
                accounted.add(from.cells[i]);
            }
        }
        Set<Cell> unaccounted = new HashSet<>();
        for (int i = 0; i < from.cells.length; i++) {
            if(!accounted.contains(from.cells[i]) && !unaccounted.contains(from.cells[i])) {
                unaccounted.add(from.cells[i]);
            }
        }
        if(unaccounted.size() > 1) return null;
        to.revalidate();
        if(unaccounted.size() == 1) {
            if(!to.isUniform()) {
                return null;
            } else {
                to.isHidingGroup = true;
            }
        }        
        return to;
    }
    
    public void build() {
        int p2 = 1;
        for (int i = 0; i < width; i++) p2 *= 2;
        List<LineState> states = createState();
        for (LineState s: states) {
            this.states.put(s.toString(), s);
            if(s.isUniform()) {
                LineState s1 = s.copy();
                s1.isHidingGroup = true;
                this.states.put(s1.toString(), s1);
            }
        }
        for (LineState s: this.states.values()) {
            Set<String> ts = new HashSet<>();
            for (int i = 0; i < p2; i++) {
                LineState t = new LineState(i, width);
                t = transfer(s, t);
                if(t != null) ts.add(t.toString());
            }
            transfers.put(s.toString(), ts);
        }
        for (int i = 0; i < p2; i++) {
            LineState t = new LineState(i, width);
            t.revalidate();
            if(!t.isMatch(constraint[0])) continue;
            Map<Integer, Long> v = new HashMap<>();
            v.put(t.countOnes(), 1l);
            counts.put(t.toString(), v);
        }
        for (int i = 0; i < height - 1; i++) {
            counts = addRow(counts, constraint[i+1]);
        }
        long sum = sum(counts, diff, width * height);
        System.out.println(sum);
    }
    
    Map<String, Map<Integer,Long>> addRow(Map<String, Map<Integer,Long>> counts, int[] cs) {
        Map<String, Map<Integer,Long>> next = new HashMap<>();
        for (String s: counts.keySet()) {
            Map<Integer, Long> vs = counts.get(s);
            for (String n: transfers.get(s)) {
                LineState t = states.get(n);
                if(!t.isMatch(cs)) continue;
                int dk = t.countOnes();
                if(!next.containsKey(n)) {
                    Map<Integer, Long> v = new HashMap<>();
                    for (int k: vs.keySet()) {
                        v.put(k + dk, vs.get(k));
                    }
                    next.put(n, v);
                } else {
                    Map<Integer, Long> v = next.get(n);
                    for (int k: vs.keySet()) {
                        if(!v.containsKey(k + dk)) {
                            v.put(k + dk, vs.get(k));
                        } else {
                            v.put(k + dk, v.get(k + dk) + vs.get(k));
                        }
                    }
                }
            }
        }
        return next;
    }
    
    long sum(Map<String, Map<Integer,Long>> counts, int diff, int size) {
        long result = 0;
        for (String s: counts.keySet()) {
            LineState state = states.get(s);
            if(state.isOfTwoPieces()) {
                for (int k: counts.get(s).keySet()) {
                    int k1 = size - k;
                    if(Math.abs(k - k1) <= diff) {
                        long d = counts.get(s).get(k);
                        result += d;
                    }
                }
            }
        }
        return result;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt(), k = in.nextInt();
        if(m == 0 || n == 0) {
            System.out.println(1);
            return;
        }

        setSize(n, m, k);
        int[][] cs = new int[m][n];
        for (int i = 0; i < m; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                char ch = s.charAt(j);
                cs[i][j] = ch == 'T' ? T : ch == 'D' ? D : U;
            }
        }
        setConstraint(cs);
        build();
    }

    public static void main(String[] args) {
        new Solution().run();
    }
}
