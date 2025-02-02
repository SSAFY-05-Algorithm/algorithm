package codingTest;

import java.io.*;
import java.util.*;

public class SWEA7733 {
	static int map[][];
	static boolean v[][];
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int maxDay;
	
	//bfs
	public static void bfs(int x, int y, int day) {
		Queue<int[]> q = new ArrayDeque<>();
		//시작 노드를 큐에 넣고 방문배열에 체크
		q.add(new int[] {x, y});
		v[y][x] = true;
		
		while(!q.isEmpty()) {
			int tmp[] = q.poll();
			int tx = tmp[0];
			int ty = tmp[1];
			
			//사방탐색
			for(int i=0; i<4; i++) {
				int ix = tx + dx[i];
				int iy = ty + dy[i];
				
				//현재 날짜보다 큰 경우에 bfs 탐색
				if(ix < N && ix >= 0 && iy < N && iy >= 0) {
					if(map[iy][ix] > day && !v[iy][ix]) {
						q.add(new int[] {ix, iy});
						v[iy][ix] = true;
					}
				}
			}
		}
	}
	
	//모든 날짜에서의 치즈 상태를 탐색하는 메서드
	public static int cheeseSearch() {
		int maxPiece = 1;
		//최대 날짜보다 적을 경우에만 탐색
		for(int day=1; day<=maxDay; day++) {
			v = new boolean[N][N];
			int piece = 0;
			//0,0부터 돌면서 새로운 치즈를 만날 경우 bfs로 해당 조각들은 전부 true로 표시
			// 다음 탐색때 같은 치즈를 다시 탐색하지 않고 조각에 추가하지 않기 위해
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!v[i][j] && map[i][j] > day) {
						bfs(j, i, day);
						piece++;
					}
				}
			}
			if(maxPiece < piece)
				maxPiece = piece;
		}
		return maxPiece;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=1; test_case<T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxDay = 0;
			
			// 치즈를 배열에 넣으면서 최대 날짜 탐색
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(maxDay < map[i][j])
						maxDay = map[i][j];
				}
			}
			//출력
			System.out.printf("#%d %d\n", test_case, cheeseSearch());
			
		}
	}

}
