package codingTest;
import java.io.*;
import java.util.*;

public class SWEA7699_2 {
	static int R;
	static int C;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int maxSize;
	
	public static void dfs(char[][] tour, int v, int idx, int x, int y) {
		maxSize = Math.max(maxSize, idx);
		
		for(int i=0; i<4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx >= 0 && tx < C && ty >= 0 && ty < R) {
				if((v &(1<<tour[ty][tx]-'@')) == 0) {
					v |= 1<<tour[ty][tx]-'@';
					dfs(tour, v, idx+1, tx, ty);
					v &= ~(1<<tour[ty][tx]-'@');
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case<=N; test_case++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			char[][] tour = new char[R][C];
			
			maxSize = 0;
			
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				for(int j=0; j<C; j++) {
					tour[i][j] = tmp.charAt(j);
				}
			}
			int tmp = 0;
			tmp |= 1 << (tour[0][0]-'@');
			dfs(tour, tmp, 1, 0, 0);
			System.out.printf("#%d %d\n",test_case, maxSize);
		}
	}

}
