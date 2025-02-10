package algorithm_mine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17472 {
	static int N, M;
	static int[][] adjMat;
	static boolean[][] visited;
	static int[][] map;
	static int island_num = 2;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// adjMat[s][e]=w;
		// 이런식으로 저장되어야한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					dfs(i, j);
					island_num++;
				}
			}
		}
		adjMat = new int[island_num][island_num];
		for (int i = 0; i < adjMat.length; i++) {
			Arrays.fill(adjMat[i], Integer.MAX_VALUE);
		}
		
		// 각각 섬별 거리 정해주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 섬이면
				if (map[i][j] >= 2) {
					for (int k = 0; k < 4; k++) {
						find_I(i, j, k);
					}
				}
			}
		}

		int[] dist = new int[island_num-2];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean v[] = new boolean[island_num-2]; // 방문배열
		dist[0] = 0;
		//여기서 모든 노드를 방문했는지 확인하기 배열 인덱스 오류 나기전에 처리하기
		for (int k = 0; k < island_num - 2; k++) {
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;
			for (int i = 0; i < dist.length; i++) {
				if (dist[i] < minD && !v[i]) {
					minIdx = i;
					minD = dist[i];
				}
			}
			if(minIdx==-1) {
				System.out.println(minIdx);
				return;
			}
			v[minIdx] = true;
			for (int i = 0; i < island_num-2; i++) {
				
                if (!v[i] && adjMat[minIdx][i] < Integer.MAX_VALUE) {
                    dist[i] = Math.min(dist[i], adjMat[minIdx][i]);
                }
			}
		}
		
		
		
        int result = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            result += dist[i];
        }
        System.out.println(result);



	}

	// 다른 섬 찾기
	public static void find_I(int r, int c, int dir) {
		int basic = map[r][c];
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		int len = 0;
		while (true) {
			if (nr >= N || nc >= M || nr < 0 || nc < 0||map[nr][nc] == basic)
				break;

			if (map[nr][nc] >= 2 && map[nr][nc] != basic) {
				if (len >= 2 && len < adjMat[basic-2][map[nr][nc]-2]) {
					adjMat[basic-2][map[nr][nc]-2] = len;
					adjMat[map[nr][nc]-2][basic-2] = len;
				}
				break;
			}
			if (map[nr][nc] == 0)
				len++;
			nr = nr + dr[dir];
			nc = nc + dc[dir];
		}

	}

	// 섬 번호 정해주기
	public static void dfs(int r, int c) {
		map[r][c] = island_num;
		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= N || nc >= M || nr < 0 || nc < 0)
				continue;
			if (!visited[nr][nc] && map[nr][nc] == 1) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}
