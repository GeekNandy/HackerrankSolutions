import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int finalCounter = 0;
    static boolean reachedDestination = false;
    static String countLuck(String[] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length();

        char array[][] = new char[rows][cols];
        reachedDestination = false;

        int tmpi=0, tmpj=0;

        for(int i=0;i<=rows-1;i++){
            for(int j =0;j<=cols-1;j++){
                array[i][j] = matrix[i].charAt(j);
                if(matrix[i].charAt(j) == '*'){
                    //star = new Loc(i,j);
                }
                if(matrix[i].charAt(j) == 'M'){
                    tmpi = i;
                    tmpj = j;
                }
            }
        }       
        //start at position M and check the available positions
        Integer counter =0;
        finalCounter = 0;
        move(array, tmpi, tmpj,counter);        
            //System.out.println("-------->counter:"+finalCounter);
        if (finalCounter==k)
            return "Impressed";
        else 
            return "Oops!";     

    }

    public static void move(char array[][], int row, int col,Integer counter){

        boolean up = true, down=true,left=true,right=true;
        int count=0;

        if(reachedDestination) return;

        if(row>=array.length || row <0){
            return;
        }
        if(col>=array[0].length || col<0){
            return;
        }

        if(array[row][col]=='*'){
            reachedDestination = true;
            finalCounter = counter;
            return;
        }

        //check if we can move up ^
        if(row<=0 ){
            up = false;
        }else{
            if( (array[row-1][col] == 'X')  || array[row-1][col] == 'A')
                up = false;
        }
        //check if we can move down
        if(row>=array.length-1){
            down = false;
        }else{
            if(array[row+1][col] =='X' || array[row+1][col]=='A')
                down = false;
        }
        //check left
        if(col <=0){
            left = false;
        }else{
            if(array[row][col-1] =='X' || array[row][col-1]=='A')
                left = false;
        }
        //check right
        if(col>=array[0].length-1){
            right = false;
        }else{
            if(array[row][col+1]=='X' || array[row][col+1]== 'A')
                right = false;
        }

        if(up==false && down==false && left==false && right==false){
            array[row][col] ='X';
            return;
        }

        if(up) count++;
        if(down) count++;
        if(left) count++;
        if(right) count++;
        //if there are multiple directions to go 
        if(count>=2) counter++;

        array[row][col] ='A';

        if(down) move(array,row+1,col,counter);
        if(up)   move(array,row-1,col,counter);
        if(right)move(array,row,col+1,counter);
        if(left) move(array,row,col-1,counter);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            String[] matrix = new String[n];

            for (int i = 0; i < n; i++) {
                String matrixItem = scanner.nextLine();
                matrix[i] = matrixItem;
            }

            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = countLuck(matrix, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
