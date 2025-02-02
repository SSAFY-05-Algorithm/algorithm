package codingTest;

import java.io.*;
import java.util.*;

public class BJ7576 {
	static int[][] tomato;
	static boolean[][] v;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int M;
	static int N;
	
	//테스트 출력
	public static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(tomato[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//bfs
	public static int bfs(ArrayList<int[]> sp) {
		Queue<int[]> q = new ArrayDeque<>();
		
		//시작점들을 큐에 삽입
		for(int start=0; start<sp.size(); start++) {
			int[] tmp = sp.get(start);
			q.add(tmp);
			v[tmp[1]][tmp[0]] = true;
		}
		//지난 날짜
		int days = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			days++;
			//큐에 들어있는 값들을 돌았는데 더이상 넣을 값이 없었을 경우 날짜 1 감소
			boolean flag = false;

			for(int k=0; k<size; k++) {
				int[] tmp = q.poll();
				
				for(int i=0; i<4; i++) {
					int tx = tmp[0] + dx[i];
					int ty = tmp[1] + dy[i];
					//사방탐색
					if(tx >= 0 && tx < M && ty >= 0 && ty < N) {
						if(!v[ty][tx] && tomato[ty][tx] != -1) {
							q.add(new int[] {tx, ty});
							v[ty][tx] = true;
							//토마토 익히기
							tomato[ty][tx] = 1;
							flag = true;
						}
					}
				}
			}
			if(!flag)
				days--;
		}
		
		boolean flag = true;
		// 익지 않은 토마토가 존재할 경우 -1 리턴
		// 다 익었을 경우 전부 익는데 걸린 날짜 리턴
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tomato[i][j] == 0)
					return -1;
			}
		}
		return days;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<int[]> sp = new ArrayList<>();	//startPoint
		
		M = Integer.parseInt(st.nextToken());	//x
		N = Integer.parseInt(st.nextToken());	//y
		
		tomato = new int[N][M];
		v = new boolean[N][M];
		
		//입력 받으면서 출발점은 리스트에 삽입
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				tomato[i][j] = tmp;
				if(tmp == 1)
					sp.add(new int[] {j, i});
			}
		}
		System.out.println(bfs(sp));
	}
}
