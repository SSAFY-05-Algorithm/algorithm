import java.util.*;
import java.io.*;

class Main{
    static int n = 10;
    //1, 2, 3, 4, 5
    static int[][] fields = new int[n][n];
    static int min = Integer.MAX_VALUE;
    static int[] counts = {
            0, 5, 5, 5, 5, 5
    };
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<n;j++) {
                int value = Integer.parseInt(st.nextToken());
                fields[i][j] = value;

                if(value == 1) {
                    result++;
                }
            }
        }

        if(result == 0){
            System.out.print(0);

            return;
        }

        backTracking2(0, 0, 0);

        if(min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void backTracking2(int i, int j, int used){
        if(result == 0){
            min = Math.min(min, used);

            return;
        }

        if(j>=n){
            backTracking2(i+1, 0, used);

            return;
        }

        if(i>=n || j>=n){
            return;
        }

        if(fields[i][j] == 0){
            backTracking2(i, j+1, used);

            return;
        }

        for(int p=1;p<=5;p++){
            if(isPossible(i, j, p)){
                counts[p]--;
                attach(i, j, p);
                result-=p*p;

                backTracking2(i, j+p, used+1);

                counts[p]++;
                detach(i, j, p);
                result+=p*p;
            }
        }
    }

    static void attach(int i, int j, int paper) {
        for(int row=i; row<=i+paper-1;row++) {
            for(int col=j;col<=j+paper-1;col++) {
                fields[row][col] = 0;
            }
        }
    }

    static void detach(int i, int j, int paper) {
        for(int row=i; row<=i+paper-1;row++) {
            for(int col=j;col<=j+paper-1;col++) {
                fields[row][col] = 1;
            }
        }
    }

    static boolean isPossible(int i, int j, int paper) {
        int endI = i+paper-1;
        int endJ = j+paper-1;

        if(endI >= n || endJ>=n) {
            return false;
        }

        if(counts[paper]<1) {
            return false;
        }

        for(int row=i; row<=endI; row++) {
            for(int col=j; col<=endJ; col++) {
                if(fields[row][col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
