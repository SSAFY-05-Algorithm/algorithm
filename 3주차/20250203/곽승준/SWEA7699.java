package codingTest;
import java.io.*;
import java.util.*;

public class SWEA7699 {
	static boolean[] alpha;
	static int R;
	static int C;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int maxSize;
	
	public static void dfs(char[][] tour, boolean v[], int idx, int x, int y) {
		maxSize = Math.max(maxSize, idx);
		
		for(int i=0; i<4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx >= 0 && tx < C && ty >= 0 && ty < R) {
				if(!v[tour[ty][tx]-'A']) {
					v[tour[ty][tx]-'A'] = true;
					dfs(tour, v, idx+1, tx, ty);
					v[tour[ty][tx]-'A'] = false;
				}
			}
		}
	}
	
//	public static int bfs(char[][] tour) {
//		Queue<int[]> q = new ArrayDeque<>();
//		
//		q.add(new int[] {0,0});
//		int cnt = 1;
//		alpha[tour[0][0]-'A'] = true;
//		
//		while(!q.isEmpty()) {
//			int size = q.size();
//			cnt++;
//			boolean flag = false;
//			for(int s=0; s<size; s++) {
//				int[] tmp = q.poll();
//				
//				for(int i=0; i<4; i++) {
//					int tx = tmp[0] + dx[i];
//					int ty = tmp[1] + dy[i];
//					
//					if(tx >= 0 && tx < C && ty >= 0 && ty < R) {
//						if(!alpha[tour[ty][tx]-'A']) {
//							q.add(new int[] {tx, ty});
//							alpha[tour[ty][tx]-'A'] = true;
//							flag = true;
//						}
//					}
//				}
//			}
//			if(!flag)
//				cnt--;
//			System.out.println(Arrays.toString(alpha));
//		}
//		
//		return cnt;
//	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case<=N; test_case++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			char[][] tour = new char[R][C];
			alpha = new boolean[26];
			
			maxSize = 0;
			
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				for(int j=0; j<C; j++) {
					tour[i][j] = tmp.charAt(j);
				}
			}
			boolean[] tmp = new boolean[26];
			tmp[tour[0][0]-'A'] = true;
			dfs(tour, tmp, 1, 0, 0);
			System.out.printf("#%d %d\n",test_case, maxSize);
		}
	}

}
