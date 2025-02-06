//[SWEA7699] 수지의 수지 맞는 여행

//1. 문제 분석 
//한번도 안밟은 알파벳만 밟고 최대 몇칸까지 이동이 가능한가
//2. 문제 조건 분석
//2차원 배열 크기 R * C
//각각의 명물을 알파벳으로 나타낸다(문자형 2차원 배열)
//3. 문제 해결 아이디어
//중심 아이디어
//dfs를 사용하여 최대 거리 저장
//고려사항1
//맵 밖으로 안나가게 조건문 작성
//고려사항2
//dfs탐색중 방문 후 다시 방문배열 false로 초기화
//고려사항3
//거리를 측정하기 위해서 dfs의 인자로 넣어두기
//4. 코드 
//java 코드 
package algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


class SWEA7699
{
	static char map[][];
	static int R,C;
	public static int dr[]= {-1,0,1,0};
	public static int dc[]= {0,1,0,-1};
	static boolean visited[];
	static int max=0;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			max=0;
			st=new StringTokenizer(br.readLine());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			visited=new boolean[26];
			map=new char[R][C];
			for (int i = 0; i < R; i++) {
				String str=br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j]=str.charAt(j);
				}
			}
			int result=dfs(0,0,1);
			System.out.println("#"+test_case+" "+result);
		}
	}
	public static int dfs(int fr,int fc,int cnt) {
		visited[map[fr][fc]-'A']=true;
		max=Math.max(max, cnt);
		for (int i = 0; i < 4; i++) {
			int nr=fr+dr[i];
			int nc=fc+dc[i];
			if(nr>=R||nc>=C||nr<0||nc<0)
				continue;
			if(visited[map[nr][nc]-'A']==false) {
				visited[map[nr][nc]-'A']=true;
				dfs(nr,nc,cnt+1);
				//다시 돌려 놓기
				visited[map[nr][nc]-'A']=false;
			}
		}
		return max;
	}
}

//5. 시간 복잡도 계산
//초기화 O(R*C), DFS(???)
//여기서 DFS 시간복잡고 계산 어떻게 해야할지 감이 안잡혀요.

//6. 느낀점
//DFS가 BFS보다 확실히 코드 길이가 줄어드는구나..





