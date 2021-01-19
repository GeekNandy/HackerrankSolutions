import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        SherlockAndMiniMax solver = new SherlockAndMiniMax();
        solver.solve(1, in, out);
        out.close();
    }
}

class SherlockAndMiniMax {
public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        int min = in.readInt();
        int max = in.readInt();
        int best = -1;
        int at = -1;
        Arrays.sort(array);
        int candidate = Integer.MAX_VALUE;
        for (int i : array)
            candidate = Math.min(candidate, Math.abs(min - i));
        if (candidate > best || candidate == best && at > min) {
            at = min;
            best = candidate;
        }
        candidate = Integer.MAX_VALUE;
        for (int i : array)
            candidate = Math.min(candidate, Math.abs(max - i));
        if (candidate > best || candidate == best && at > max) {
            at = max;
            best = candidate;
        }
        for (int i = 1; i< count; i++) {
            int current = (array[i] + array[i - 1]) / 2;
            if (current < min || current > max) {
                continue;
            }
            candidate = current - array[i - 1];
            if (candidate > best || candidate == best && at > current) {
                at = current;
                best = candidate;
            }
        }
        out.printLine(at);
    }
}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar>= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars<= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void close() {
        writer.close();
    }

    public void printLine(int i) {
        writer.println(i);
    }
}

class IOUtils {

    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i< size; i++)
            array[i] = in.readInt();
        return array;
    }

}
