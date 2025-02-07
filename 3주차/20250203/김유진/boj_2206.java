import java.util.*;
import java.io.*;

class Point{
    int x;
    int y;
    boolean broke;

    public Point(int x, int y, boolean broke){
        this.x=x;
        this.y=y;
        this.broke = broke;
    }
}

class Main{
    static int N;
    static int M;
    static boolean[][] fields;
    static int[][] dps = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        fields = new boolean[N+1][M+1];

        for(int i=1;i<=N;i++){
            char[] row = br.readLine().toCharArray();
            for(int j=1;j<=M;j++){
                boolean value;

                if(row[j-1] == '0'){
                    value = true;
                } else {
                    value = false;
                }

                fields[i][j] = value;
            }
        }

        System.out.print(bfs());
    }

    static int bfs(){
    int result = 1;
    boolean[][][] visited = new boolean[N+1][M+1][2]; //0: 벽 파괴x 1: 벽 파괴
    Point start = new Point(1, 1, false);
    visited[1][1][0] = true;

    Queue<Point> queue = new LinkedList<>();
    queue.add(start);
    while(!queue.isEmpty()){
        Queue<Point> nextQueue = new LinkedList<>();

        while(!queue.isEmpty()){
            Point point = queue.poll();
            boolean broke = point.broke;

            if(point.x == N && point.y == M){
                return result;
            }

            for(int[] dp : dps){
                int newX = dp[0] + point.x;
                int newY = dp[1] + point.y;

                if(newX <= 0 || newX > N || newY <= 0 || newY > M){
                    continue;
                }
                
                //이미 방문한 경우 제외
                if(visited[newX][newY][broke?1:0]){
                    continue;
                }
                
                //벽 파괴하는 경우
                if(!fields[newX][newY]){
                    if(broke){
                        continue;
                    }

                    nextQueue.add(new Point(newX, newY, true));
                    visited[newX][newY][1] = true;
                    
                    continue;
                } 
                
                //파괴 안 하는 경우
                nextQueue.add(new Point(newX, newY, broke));
                visited[newX][newY][broke?1:0] = true; 
            }
        }

        queue = nextQueue;
        result++;
    }

    return -1;
}

}
