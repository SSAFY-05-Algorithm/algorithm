package codingTest;

import java.io.*;
import java.util.*;

//pair 클래스 생성 후 다시 진행
class Pair {
    int x, y;
    int time;
    
    Pair() {};
    Pair(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class SWEA4193_3 {
    static int N;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int sx;
    static int sy;
    static int ex;
    static int ey;
    
    public static int bfs() {
        Queue<Pair> q = new ArrayDeque<>();
        
        q.add(new Pair(sx, sy, 1));
        v[sy][sx] = true;
        
        Pair p = new Pair();
        while(!q.isEmpty()) {
            p = q.poll();
            
            for(int i=0; i<dx.length; i++) {
                int tx = p.x + dx[i];
                int ty = p.y + dy[i];
                
                if(tx == ex && ty == ey)
                    return p.time;
                
                if(tx >= N || tx < 0|| ty >= N || ty < 0 || v[ty][tx] || map[ty][tx] == 1)
                    continue;
                
                if(map[ty][tx] == 0) {
                    q.add(new Pair(tx, ty, p.time+1));
                    v[ty][tx] = true;
                }
                
                if(map[ty][tx] == 2) {
                    if(p.time%3 == 0) {
                        q.add(new Pair(tx, ty, p.time+1));
                        v[ty][tx] = true;
                    } else
                        q.add(new Pair(p.x, p.y, p.time+1));
                }
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int test_case = 1; test_case<T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            v = new boolean[N][N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp != 1 && tmp != 2)
                        map[i][j] = 0;
                    else
                        map[i][j] = tmp;
                }
            }
            
            st = new StringTokenizer(br.readLine());
            sy = Integer.parseInt(st.nextToken());
            sx = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ey = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            
            System.out.printf("#%d %d\n", test_case, bfs());
        }
    }
}
