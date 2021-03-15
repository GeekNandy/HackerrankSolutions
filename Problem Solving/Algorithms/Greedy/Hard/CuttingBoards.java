import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int q = sc.nextInt();
        while(q-->0) {
            long cost = 0;
            int x, y = x = 1;
            int m = sc.nextInt();
            int n = sc.nextInt();
            ArrayList<Long> arrayY = new ArrayList<>();
            ArrayList<Long> arrayX = new ArrayList<>();
            for (int i = 0; i < m - 1; i++) arrayY.add(sc.nextLong());
            for (int i = 0; i < n - 1; i++) arrayX.add(sc.nextLong());
            Collections.sort(arrayX);
            Collections.reverse(arrayX);
            Collections.sort(arrayY);
            Collections.reverse(arrayY);
            int sizeX = arrayX.size();
            int sizeY = arrayY.size();
           while (sizeX>0 || sizeY>0){
               if ((sizeX>0 && sizeY==0) || ((sizeX>0) && (arrayX.get(x - 1)>arrayY.get(y - 1)))){
                   cost += y*arrayX.get(x - 1);
                   x++;
                   sizeX--;
               }
               else if (sizeY>0){
                   cost += x*arrayY.get(y - 1);
                   y++;
                   sizeY--;
               }
               cost = cost%((int)Math.pow(10,9)+7);
           }
           System.out.println(cost%((int)Math.pow(10,9)+7));
        }
    }
}
