//[SWEA7733] 치즈도둑
//
//1. 문제 분석 
//치즈도둑 문제는 크기가 N x N인 2차원 배열위 치즈들이 있고, 각각의 치즈들은
//특정 날짜마다 사라지게 된다. 이때 어떤 날짜에서 치즈들의 덩어리 개수가 가장 많은 날짜를 구하여라

//2. 문제 조건 분석
//N이 2~100사이 이고, 맛있는 정도는 1에서100사이이다. Int를 사용해도 충분히 계산 가능하다
//3. 문제 해결 아이디어
//중심 아이디어
//하루가 지날때마다 사라진 치즈를 제외하고 bfs하고 탐색이 종료되면 덩어리의 갯수를 증가시킨다.
//고려사항 1)
//맵 밖으로 나가지 않도록 조건문 작성
//고려사항 2)
//방문 배열 사용해서 같은곳 재탐색 방지
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
class cheese{
	int x;
	int y;
	cheese(int x,int y) {
		this.x=x;
		this.y=y;
	}
}

class SWEA7733
{
	static int map[][];
	static boolean visited[][];
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static int N;
	static int result;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;


		for(int test_case = 1; test_case <= T; test_case++)
		{
			result=0;
			N=Integer.parseInt(br.readLine());
			visited=new boolean[N][N];
			map=new int [N][N];
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int max=0;
			//k : 맛있는 정도
			//맛있는 정도가 0일때부터 새로운 방문배열 만들어서 bfs탐색
			for(int k=0;k<100;k++) {
				result=0;
				visited=new boolean[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[i][j]>k) { //맛있는 정도가 k초과일때 bfs
							bfs(i,j,k);
						}
					}
				}
				max=Math.max(max, result);
			}
			System.out.println("#"+test_case+" "+max);
		}
		
	}
	//맛있는 정도 k보다 큰 곳들만 방문하여 result의 갯수 증가
	public static void bfs(int r,int c,int taste) {
		Queue<cheese> q=new LinkedList<>();
		q.add(new cheese(r,c));
		if(visited[r][c]==true) return;
		visited[r][c]=true;
		result++;
		while(!q.isEmpty()) {
			cheese ch=q.poll();
			for (int i = 0; i < 4; i++) {
				int nr=ch.x+dr[i];
				int nc=ch.y+dc[i];
				if(nr>=0&&nc>=0&&nr<N&&nc<N&&map[nr][nc]>taste&&!visited[nr][nc]) {
					visited[nr][nc]=true;
					q.add(new cheese(nr,nc));
				}
			}
		}
	}
}

//5. 시간 복잡도 계산
// bfs안에서 크기 N x N의 배열을 탐색한다. 최악의 경우 O(N^2)의 시간이 걸린다.
// 그리고 2중 for을 통해 맵을 방문O(N^2) 하기 때문에 총 O(N^4)의 시간이 소요된다.
//6. 느낀점
// K를 100까지 고정적으로 탐색하지, 미리 최대값 찾아서 거기까지만 맵을 방문하게 된다면 시간을 더 줄일 수 있을것 같다. 





