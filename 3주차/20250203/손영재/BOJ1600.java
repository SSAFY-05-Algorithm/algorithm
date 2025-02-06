//[BOJ1600] 말이 되고픈 원숭이

//1. 문제 분석 
//맵에서 원숭이가 출발점에서 도착점까지 이동하는 최단 거리를 구하는 문제
//원숭이는 기본적으로 상하좌우로만 이동 가능하지만, 장기말처럼 "L"자 형태로도 이동할 수 있다

//2. 문제 조건 분석
//R과 C는 최대 30이고, K는 최대 30
//맵은 R×C 크기이며, 벽은 1, 비어있는 곳은 0
//(R-1, C-1) 위치까지 가야 한다

//3. 문제 해결 아이디어
//중심 아이디어
// BFS를 사용하여 최단 거리
// 방문 상태를 3차원 배열

//고려사항1
//말처럼 이동할 때마다 K값을 증가시켜서 이동할 수 있는 횟수를 제한

//고려사항2
//BFS에서 큐에 각 위치를 추가할 때, 말처럼 이동할 수 있을 경우와 그렇지 않은 경우를 구분해서 큐에 추가

//고려사항3
//맵의 경계를 벗어나지 않도록 처리

//4. 코드 
//java 코드 
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Monkey{
	int r;
	int c;
	int len;
	int cnt;
	public Monkey(int r,int c,int len,int cnt) {
		this.r=r;
		this.c=c;
		this.len=len;
		this.cnt=cnt;
	}
}

public class BOJ1600 {

	static int C;
	static int R;
	static int K;
	static int maze[][];
	static int dr[]= {-1,0,1,0,-2,-1,1,2,2,1,-1,-2};
	static int dc[]= {0,1,0,-1,1,2,2,1,-1,-2,-2,-1};
	static boolean [][][]visited;
	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		K=Integer.parseInt(br.readLine());
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		
		
		C=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		maze = new int [R][C];
		visited=new boolean [K+1][R][C];
		for (int i = 0; i < R; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				maze[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}
	public static int bfs() {
		Queue<Monkey> q=new LinkedList<>();
		q.add(new Monkey(0,0,0,0));
		visited[0][0][0]=true;
		
		while(!q.isEmpty()) {
			Monkey now=q.poll();
			if(now.r==R-1&&now.c==C-1) {
				return now.len;
			}
			for (int i = 0; i < 12; i++) {
				int nr=now.r+dr[i];
				int nc=now.c+dc[i];
				
				if(nr<0||nc<0||nr>=R||nc>=C||maze[nr][nc]!=0) continue;
				//일반적인 이동
				if(i<4) {
					if(!visited[now.cnt][nr][nc]) { //방문한적이 없을때
						visited[now.cnt][nr][nc]=true;
						q.add(new Monkey(nr,nc,now.len+1,now.cnt));
					}
				}
				//말처럼 이동
				else {
					if(now.cnt==K) continue; //더이상 말처럼 못 뛸때
					if(!visited[now.cnt+1][nr][nc]) {
						visited[now.cnt+1][nr][nc]=true;
						q.add(new Monkey(nr,nc,now.len+1,now.cnt+1));
					}
					
				}
			}
		}
		return -1;
		
	}

}

//5. 시간 복잡도 계산
//초기화 O(NM), BFS O(NM) 즉, O(NM)

//6. 느낀점
//일반적인 이동과 말처럼 이동하는 경우를 구분해서 처리해야 했고, 이를 위해 상태를 나타내는 cnt 값을 추가했다.