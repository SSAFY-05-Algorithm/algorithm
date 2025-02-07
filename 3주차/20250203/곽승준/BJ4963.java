package codingTest;
import java.io.*;
import java.util.*;

public class BJ4963 {
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static boolean[][] v;
    static int w;
    static int h;
    
    public static void bfs(int[][] island, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {x,y});
        v[y][x] = true;
    
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            for(int i=0; i<8; i++) {
                int tx = tmp[0] + dx[i];
                int ty = tmp[1] + dy[i];

                //팔방탐색 진행            
                if(tx>=0 && tx < w && ty>=0 && ty<h) {
                    if(!v[ty][tx] && island[ty][tx]==1) {
                        v[ty][tx] = true;
                        q.add(new int[] {tx, ty});
                    }
                }
            }
        }
    }
    
//    public static void dfs(int[][] island, boolean[][] visit, int x, int y) {
//        
//        for(int i=0; i<8; i++) {
//            int tx = x + dx[i];
//            int ty = y + dy[i];
//            
//            if(tx>=0 && tx < w && ty>=0 && ty<h) {
//                if(island[ty][tx] == 1) {
//                    visit[ty][tx] = true;
//                    dfs(island, visit, tx, ty);
//                    visit[ty][tx] = false;
//                }
//            }
//        }
//    }
    
    public static int islandSearch(int[][] island) {
        //모든 배열을 돌아보며 섬인지 확인
        int cnt = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(!v[i][j] && island[i][j] == 1) {
                    bfs(island, j, i);
//                    dfs(island, v, j, i);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag = true;

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        while(true) {
            if(flag) {
                flag = false;
            } else {
                st = new StringTokenizer(br.readLine());
                w = Integer.parseInt(st.nextToken());
                h = Integer.parseInt(st.nextToken());
            }
            
            if(w==0 && h==0)
                break;
            
            int[][] island = new int[h][w];
            v = new boolean[h][w];
            
            for(int i=0; i<h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            System.out.println(islandSearch(island));
        }
    }
}
