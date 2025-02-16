import java.util.*;
import java.io.*;

public class Main{
	static int[][] map;
	static int[] dx = {1,1,0};
	static int[] dy = {0,1,1};
	static int cases;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		cases = 0;
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, new int[] {1, 0});
		System.out.println(cases);
	}
	
	static void dfs(int way, int[] pipe) {
		if(pipe[0] == N-1 && pipe[1] == N-1) {
			cases++;
			return;
		}
		
		for(int i=0; i<dx.length; i++) {
			if(Math.abs(way-i)==2)
				continue;
			
			int tx = pipe[0] + dx[i];
			int ty = pipe[1] + dy[i];

			if(tx>=N || tx<0 || ty>=N || ty<0 || map[ty][tx]==1 || (i==1 && map[ty][tx-1]==1) || (i==1 && map[ty-1][tx]==1))
				continue;
			
			dfs(i, new int[] {tx, ty});
		}
	}
}