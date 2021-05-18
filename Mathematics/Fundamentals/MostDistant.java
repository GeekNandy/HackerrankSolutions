import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    public static double solve(List<List<Integer>> coordinates) {
        int minx = 0;
        int maxx = 0;
        int miny = 0;
        int maxy = 0;
        
        for(int i = 0; i < coordinates.size(); i++) {
           minx = Math.min(minx, (coordinates.get(i)).get(0));
           maxx = Math.max(maxx, (coordinates.get(i)).get(0));
           miny = Math.min(miny, (coordinates.get(i)).get(1));
           maxy = Math.max(maxy, (coordinates.get(i)).get(1));
        }
        
        return Math.max(Math.max (maxx-minx, maxy-miny),
        Math.sqrt(Math.pow(Math.max(Math.abs(maxx),Math.abs(minx)),2) + 
        Math.pow(Math.max(Math.abs(maxy),Math.abs(miny)),2)));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> coordinates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] coordinatesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> coordinatesRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int coordinatesItem = Integer.parseInt(coordinatesRowTempItems[j]);
                coordinatesRowItems.add(coordinatesItem);
            }

            coordinates.add(coordinatesRowItems);
        }

        double result = Result.solve(coordinates);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
