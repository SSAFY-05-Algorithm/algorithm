//[BOJ2206] 벽 부수고 이동하기

//1. 문제 분석 
//N*M크기의 2차원 배열안에서 벽(값이 1)을 한번만 뚫으면서 목적지까지 가는데의 최단거리를 구하라

//2. 문제 조건 분석
//N과 M은 최대 1000
//벽은 한번밖에 못 뚫는다
//사방 탐색

//3. 문제 해결 아이디어
//중심 아이디어
//3차원 방문배열 사용해서 뚫은 경우와 뚫지 않은 경우로 나누어 맵 탐색

//고려사항1
//길이와 벽 뚫은 여부를 타나낼 수 있도록 Maze 클래스 작성

//고려사항2
//벽 안뚫은 상태에서 0 지나가기 / 벽 뚫은 상태에서 0 지나가기 / 벽 안뚫은 상태에서 벽 뚫기
//총 세가지 경우로 나누어 생각

//4. 코드 
//java 코드 
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Maze{
    int x;
    int y;
    boolean chk;
    int len;
    public Maze(int x,int y,boolean chk,int len) {
        this.x=x;
        this.y=y;
        this.chk=chk;
        this.len=len;
    }
}

public class BOJ2206 {
	static boolean visited[][][];
    static int map[][];
    static int N;
    static int M;
    public static int dr[]= {-1,0,1,0};
    public static int dc[]= {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        visited=new boolean[2][N][M];
        for (int i = 0; i < N; i++) {
            String str=br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j]=str.charAt(j)-'0';
            }
        }
        System.out.println(bfs());
    }

	public static int bfs() {
	    Queue<Maze> q=new LinkedList<>();
	    q.add(new Maze(0,0,false,0));
	    visited[0][0][0]=true;
	    while(!q.isEmpty()) {
	        Maze m=q.poll();
	        if(m.x==N-1&&m.y==M-1) {
	        	return m.len+1;
	        }
	        for (int i = 0; i < 4; i++) {
	            int nr=m.x+dr[i];
	            int nc=m.y+dc[i];
	            //벽 바깥세계
	            if(nr>=N||nc>=M||nr<0||nc<0) continue;
	            //벽 안뚫은 상태에서 0 지나가기
	            if(map[nr][nc]==0&&!m.chk) {
	            	if(!visited[0][nr][nc]) {//안지나 간곳이면
	            		visited[0][nr][nc]=true;
	            		q.add(new Maze(nr,nc,false,m.len+1));
	            	}
	            }//벽 뚫은 상태에서 0 지나가기
	            else if(map[nr][nc]==0&&m.chk) {
	            	if(!visited[1][nr][nc]) {//안지나 간곳이면
	            		visited[1][nr][nc]=true;
	            		q.add(new Maze(nr,nc,true,m.len+1));
	            	}
	            }//벽 안뚫은 상태에서 벽 뚫기
	            else if(map[nr][nc]==1&&!m.chk) {
	            	if(!visited[0][nr][nc]) {
	            		visited[1][nr][nc]=true;
	            		q.add(new Maze(nr,nc,true,m.len+1));
	            	}
	            }

	        }
	    }
	    return -1;
	}
}

//5. 시간 복잡도 계산
//초기화 O(NM), BFS O(NM),  합 O(2NM)
//즉, O(NM)

//6. 느낀점
//벽을 부쉈는지 여부에 따라 3차원 방문 배열(visited[2][N][M])을 사용해야 한다는 점이 새롭다.




