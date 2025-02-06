//[BOJ4963] 섬의 개수

//1. 문제 분석 
//섬의 개수를 구하여라.이때 섬은 팔방으로 이어져 있으면 같은 섬이다.
//2. 문제 조건 분석
//2차원 배열 문제로 w와 h는 50보다 작거나 같은 양의 정수
//1은 땅 0은 바다
//3. 문제 해결 아이디어
//중심 아이디어
//초기화된 배열을 처음부터 끝까지 탐색하며 bfs진행
//고려사항1
//bfs를 진행하면서 방문된 곳은 방문 배열을 통해 관리하여 재방문 방지
//고려사항2
//bfs가 끝날때마다 섬의 개수 result 증가
//고려사항3
//8방 탐색
//4. 코드 
//java 코드 
package algorithm;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4963 {
	static int dy[]= {-1,-1,0,1,1,1,0,-1};
	static int dx[]= {0,1,1,1,0,-1,-1,-1};
	static int result;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int w,h;
		while(true) {
			result=0;
			st=new StringTokenizer(bf.readLine());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
			visited=new boolean[h][w];
			 if (w == 0 && h == 0)
	                break;
			int [][] island=new int[h][w];
			for (int i = 0; i < h; i++) {
				st=new StringTokenizer(bf.readLine());
				for (int j = 0; j < w; j++) {
					island[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//맵중에서 방문하지 않고 값이 1인곳만 bfs탐색(방문배열 초기화)
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(island[i][j]==1&&!visited[i][j]) {
						bfs(island,i,j,visited);
					}
				}
			}
			System.out.println(result);
		}
	}
	public static void bfs(int [][] island, int x,int y,boolean [][]visited) {
		Queue<Point> q=new LinkedList<>();
		q.offer(new Point(x,y));
		visited[x][y]=true;
		while(!q.isEmpty()) {
			Point p=q.poll();
			for (int i = 0; i < 8; i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&nx<island.length&&ny>=0&&ny<island[0].length&&!visited[nx][ny]&&island[nx][ny]==1) {
					q.offer(new Point(nx,ny));
					visited[nx][ny]=true;
					//System.out.println(nx+" "+ny);
				}
			}
		}
		//bfs가 끝날때마다 섬의 갯수 + 1
		result++;
	}
}

//5. 시간 복잡도 계산
//BFS 최악의 경우 :  모든 값이 1인 경우
//초기화 O(NM) + 탐색 O(NM) + BFS O(NM) 
//즉, O(NM)

//6. 느낀점
//처음에 for문을 통해 모든 칸 (N × M)을 확인 → O(NM)
//bfs()가 호출될 때 최악의 경우 모든 칸을 탐색 → O(NM)
//라고 생각하고 O((NM)^2)이라고 생각했다
//뒤늦게 bfs할때 매번 모든 칸을 탐색하지 않는다는 것을 깨달았다








