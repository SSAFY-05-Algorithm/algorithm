package codingTest;

import java.util.*;
import java.io.*;

public class SWEA4193 {
    static int[][] map;
    static boolean[][] v;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static int bfs(int[] s, int[] e) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(s);
        v[s[0]][s[1]] = true;
        int cnt = 2;
        int ans = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            ans++;
            for(int k=0; k<size; k++) {
                int[] tmp = q.poll();
                if(tmp[0] == e[0] && tmp[1] == e[1]) {
                    return ans-1;
                }
                // 사방탐색
                for(int i=0; i<4; i++) {
                    int tx = tmp[0] + dx[i];
                    int ty = tmp[1] + dy[i];
                    
                    if(tx>=0 && tx<N && ty>=0 && ty<N) {
                        if(!v[ty][tx]) {
                            //회오리 사라지면 전진 || 벽이 아니면 전진
                            if((map[ty][tx] == 2 && cnt == 0)||map[ty][tx] == 0) {
                                q.add(new int[] {tx, ty});
                                v[ty][tx] = true;
                            //회오리 만나면 다시 큐 삽입
                            } else if (map[ty][tx] == 2) {
                                q.add(new int[] {tmp[0], tmp[1]});
                            }
                        }
                    }
                }
            }

            if(cnt==0)
                cnt=2;
            else
                cnt--;
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
            
            //시작과 끝 노드
            int[] start = new int[2];
            int[] end = new int[2];
            
            st = new StringTokenizer(br.readLine());
            for(int i=1; i>=0; i--) {
                start[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1; i>=0; i--) {
                end[i] = Integer.parseInt(st.nextToken());
            }
            
            System.out.printf("#%d %d\n", test_case, bfs(start, end));
        }
    }
}
