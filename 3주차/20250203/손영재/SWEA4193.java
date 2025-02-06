//[SWEA4193] 수영대회 결승전

//1. 문제 분석 
//N*N 크기의 수영장에 소용돌이(2), 섬(1), 일반 물(0)이 존재
//소용돌이는 3초마다 사라졌다 나타나는 패턴

//2. 문제 조건 분석
//소용돌이 위에서는 대기
//사방 탐색
//BFS

//3. 문제 해결 아이디어
//중심 아이디어
//BFS를 이용해 최단 시간을 찾으며, 소용돌이의 주기를 고려

//고려사항1
//소용돌이의 주기(3초)를 고려하여, 현재 시간이 3의 배수가 아닐 경우 대기

//고려사항2
//큐에서 현재 위치를 꺼낼 때 도착점에 도달하면 현재 시간을 반환

//고려사항3
//방문 여부를 기록하는 2차원 배열 사용하여 중복 방문을 방지

//4. 코드 
//java 코드 
package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Swim {
	int r,c,time;
	Swim(int r,int c,int time){
		this.r=r;
		this.c=c;
		this.time=time;
	}
}
class SWEA4193
{
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static boolean [][]visited;
	static int[][]pool;
	static int N;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N=Integer.parseInt(br.readLine());;
			pool=new int[N][N];
			visited=new boolean [N][N];
			
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pool[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int sr,sc,er,ec;
			st=new StringTokenizer(br.readLine());
			sr=Integer.parseInt(st.nextToken());
			sc=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			er=Integer.parseInt(st.nextToken());
			ec=Integer.parseInt(st.nextToken());
			
			System.out.println("#"+test_case+" "+bfs(sr,sc,er,ec));
			
			
		}
	}
	public static int bfs(int sr,int sc,int er,int ec) {
		// 행 / 열 / 시간 / 총시간 /
		Queue<Swim> q=new LinkedList<>();
		int tmp=0;
		q.add(new Swim(sr,sc,0));
		visited[sr][sc]=true;
		while(!q.isEmpty()) {
			Swim now=q.poll();
			if(now.r==er&&now.c==ec) {
				return now.time;
			}
			for (int i = 0; i < 4; i++) {
				int nr=now.r+dr[i];
				int nc=now.c+dc[i];
				//벗어나거나 섬이면 continue
				if(nr<0||nc<0||nr>=N||nc>=N||pool[nr][nc]==1) continue;
				//일반 바다이면
				if(pool[nr][nc]==0&&!visited[nr][nc]) {
					visited[nr][nc]=true;
					q.add(new Swim(nr,nc,now.time+1));
					//System.out.println("일반 : "+nr+" "+nc+now.time);
				//소용돌이이면
				}else if(pool[nr][nc]==2&&!visited[nr][nc]) {
					if((now.time-2)%3!=0) {//소용돌이 대기
						q.add(new Swim(nr-dr[i],nc-dc[i],now.time+1));
						//System.out.println("대기 : "+nr+" "+nc+now.time);
					}
					else {//소용돌이 나감
						visited[nr][nc]=true;
						q.add(new Swim(nr,nc,now.time+1));
						//System.out.println("나감 : "+nr+" "+nc+now.time+1);
					}
				}
			}
		}
		return -1;
	}
}

//5. 시간 복잡도 계산
//초기화 O(N^2),  BFS O(N^2), 합 O(2N^2) 즉, O(N^2)

//6. 느낀점
//소용돌이의 주기를 처음에 잘못이해해서 오래걸렸다. 문제를 잘 읽어야겠다.