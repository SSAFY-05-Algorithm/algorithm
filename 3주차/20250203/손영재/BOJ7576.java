//문제 [BOJ7576]  토마토

//### 1. 문제 분석 
//토마토가 바이러스라고 생각하고 사방으로 바이러스를 퍼트린다. 이때 모든 바이러스를
//며칠만에 퍼트리는지 구하시오.
//### 2. 문제 조건 분석
//지도의 크기가 N x M인 2차원 배열 (N,M은 최대 1000)
//맵의 값이 -1인 경우 토마토가 없고 1인 경우 토마토가 익었고 0인 경우 토마토가 익지 않은 상태
//### 3. 문제 해결 아이디어
//중심 아이디어
//일단 맵의 정보를 모두 입력 받은 다음 값이 1인 좌표들을 ArrayList에 저장하고
//순차적으로 bfs탐색
//고려사항 1)
//처음부터 모든 토마토가 익어있는 경우 0출력
//고려사항 2)
//맵을 모두 탐색하여 현재 어떤 상태인지 확인하는 함수 chk작성
//고려사항 3)
//토마토가 언제 익었는지 정보를 저장하기 위한 tomato 클래스 작성
//고려사항 4)
//가장 마지막에 실행된 bfs의 날짜가 최소 날짜


//4. 코드 
//java 코드 
package algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ7576 {
	public static int [][] map;
	public static boolean [][] visited;
	public static int dr[]= {-1,0,1,0};
	public static int dc[]= {0,1,0,-1};
	public static int N,M;
	public static List<tomato> t;
	public static void main(String[] args) throws IOException  {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		t=new ArrayList<>();
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		visited=new boolean[N][M];
		map=new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					t.add(new tomato(i,j,0));
				}
			}
		}
		if(chk(map)) {
			System.out.println(0);
			return;
		}
		int result=bfs(t);
		if(chk(map)) {
			System.out.println(result);
		}
		else
		{
			System.out.println(-1);
		}
	}
	//토마토 클래스에 방문 날짜 선언
	static class tomato{
		int x;
		int y;
		int day;
		public tomato(int x,int y,int day) {
			this.x=x;
			this.y=y;
			this.day=day;
		}
	}
	public static int bfs(List<tomato> t) {
		Queue<tomato> q=new LinkedList<>();
		q.addAll(t);
		int day=0;
		while(!q.isEmpty()) {
			tomato p=q.poll();
			day=p.day;
			for (int i = 0; i < 4; i++) {
				int nr=p.x+dr[i];
				int nc=p.y+dc[i];
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&map[nr][nc]==0) {
					map[nr][nc]=1;
					//다음날에 방문하는 토마토들을 day+1
					q.add(new tomato(nr,nc,day+1));	
				}
			}
			
		}
		//마지막에 방문된 곳이 가장 최근에 방문한 날짜
		return day;
	}
	static boolean chk(int [][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0)
					return false;
			}
		}
		return true;
	}

}

//5. 시간 복잡도 계산
//초기화 O(NM), 초기 확인 O(NM), BFS O(NM), 최종 확인 O(NM)의 합 O(4NM)
//즉, O(N*M)
//6. 느낀점
//멋쟁이 토마토







