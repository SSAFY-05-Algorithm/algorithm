import java.util.*;
import java.io.*;

enum Dir{
    horizontal, vertical, diagonal;
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] fields = new int[N+1][N+1];
        int[][][] dp = new int[N+1][N+1][3];

        StringTokenizer st;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                Arrays.fill(dp[i][j], -1);
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[N][N][0]=1;
        dp[N][N][1]=1;
        dp[N][N][2]=1;

        int res = search(dp, fields,1, 2, Dir.horizontal, N);

        System.out.print(res);
    }

    static int search(int[][][] dp, int[][] fields, int i, int j, Dir dir, int N){
        if(i>N || j>N){
            return 0;
        }

        //벽 있는 경우
        if(fields[i][j] == 1){
            return 0;
        }
        if(dir == Dir.diagonal){
            if(fields[i-1][j] == 1){
                return 0;
            }

            if(fields[i][j-1] == 1){
                return 0;
            }
        }

        if(dp[i][j][dir.ordinal()] >= 0){
            return dp[i][j][dir.ordinal()];
        }

        int sum = 0;
        sum+= search(dp, fields, i+1, j+1, Dir.diagonal, N);

        if(dir != Dir.horizontal){
            sum += search(dp, fields, i + 1, j, Dir.vertical, N);
        }

        if(dir != Dir.vertical){
            sum += search(dp, fields, i, j + 1, Dir.horizontal, N);
        }

        dp[i][j][dir.ordinal()] = sum;
        return sum;
    }
}
