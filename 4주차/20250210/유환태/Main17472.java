
import java.util.*;
import java.io.*;

public class Main17472 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited_dfs;
	static int land_count;
	static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[] dist;
	static boolean[] visited;
	static int[][] adj;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited_dfs = new boolean[N][M];
		int sum= 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt(); 
			}
		}
		land_count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1&&!visited_dfs[i][j]) {
					land_count++;
					land_check(i,j);
					
				}
			}
		
		}
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map.length; j++) {
//				System.out.print(map[i][j]+ " ");
//			}
//			System.out.println();
//		}
		
		adj = new int[land_count+1][land_count+1];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]!=0) {
					bridge_check(i,j);
				}
			}
		}
//		for (int i = 0; i < adj.length; i++) {
//			for (int j = 0; j < adj.length; j++) {
//				System.out.print(adj[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		
		int[] dist = new int[land_count+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visit_prim = new boolean[land_count+1];
		dist[1] = 0;
		for(int step = 1; step < land_count+1; step++) {
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;	
			for (int i = 1; i < dist.length; i++) {
				if (dist[i] < minD && !visit_prim[i]) {
					minIdx = i;
					minD = dist[i];
				}
			}
			if(minIdx == -1) {
				sum = -1;
				break;
			}
			visit_prim[minIdx] = true;
			
			for (int i = 1; i < land_count+1; i++) {

				if (adj[minIdx][i] >1 && !visit_prim[i] && adj[minIdx][i] < dist[i]) {
					dist[i] = adj[minIdx][i];
				}
			}
		}
		if(sum != -1) {
			for (int i = 1; i < dist.length; i++) {
				sum += dist[i];
			}
			
			System.out.println(sum);
		}else {
			System.out.println(sum);
		}
		
		
	}
	private static void bridge_check(int i, int j) {
		// TODO Auto-generated method stub
		int start_idx = map[i][j];
		for (int i1 = 0; i1 < directions.length; i1++) {
			int end_idx = 0;
			int count = 1;
			int next_x = i + directions[i1][0];
			int next_y = j + directions[i1][1];
			while(isIn(next_x,next_y)) {
				if(map[next_x][next_y]!=0) {
					end_idx = map[next_x][next_y];
					break;
				}
				next_x += directions[i1][0];
				next_y += directions[i1][1];
				count++;
			}
			if(adj[start_idx][end_idx]==0 && count>2) {
				adj[start_idx][end_idx] = count -1;
			}else if (count>2){
				adj[start_idx][end_idx] = Math.min(adj[start_idx][end_idx], count-1);
			}
			
		}
	}
	private static void land_check(int i, int j) {
		// TODO Auto-generated method stub
		visited_dfs[i][j] = true;
		map[i][j] = land_count;
		for (int j2 = 0; j2 < directions.length; j2++) {
			int next_x = i + directions[j2][0];
			int next_y = j + directions[j2][1];
			if(isIn(next_x, next_y)&& map[next_x][next_y]==1&&!visited_dfs[next_x][next_y]) {
				land_check(next_x,next_y);
			}
		}
	}
	private static boolean isIn(int x, int y) {
		// TODO Auto-generated method stub
		if(x>=0&&y>=0&&x<N&&y<M) {
			return true;
		}
		return false;
	}

}
