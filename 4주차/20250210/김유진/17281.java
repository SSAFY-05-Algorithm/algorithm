import java.util.*;
import java.io.*;

public class Main {
	static final int SIZE = 9; 
	static int[][] innings;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		innings = new int[N][SIZE];
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<SIZE;j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(backTracking(new int[SIZE], 0, new boolean[SIZE]));
	}
	
	static int backTracking(int[] res, int idx, boolean[] visited) {
		if(idx == SIZE) { 
			return calculate(res);
		}
		
		int max = 0;
		for(int i=1; i<SIZE; i++) {
			//4번쩨 -> 1번 타자
			if(idx == 3) {
				res[3] = 0;
				max = Math.max(max, backTracking(res, idx+1, visited));
				break;
			}
			
			if(visited[i]) {
				continue;
			}
			
			res[idx] = i;
			visited[i] = true;
			max = Math.max(max, backTracking(res, idx+1, visited));
			visited[i] = false;
		}
		
		return max;
	}
	
	static int calculate(int[] sequence) {
		int idx = 0; // 현재 타자 idx
		int sum = 0;
		for(int r = 0; r<innings.length; r++) { //각 이닝 수행
			int outCnt = 0;
			int[] stage = new int[4]; //1~3 루 주자 카운팅
			
			while(outCnt < 3) {
				int shooter = sequence[idx]; //현재 타자
				int score = innings[r][shooter]; //현재 타자의 점수
				
				if(score == 0) {
					outCnt ++; 
				} else if(score == 4) {
					sum++;
					
					for(int i=1;i<=3;i++) {
						sum+=stage[i];
						stage[i] = 0;
					}
				} else if(score == 1) {
					//3루타가 홈으로
					sum+=stage[3];
					
					stage[3] = stage[2];
					stage[2] = stage[1];
					stage[1] = 1;
					
					
				} else if (score == 2) {
					//3, 2루타가 홈으로
					sum+=stage[3];
					sum+=stage[2];
					stage[3] = stage[1];
					stage[2] = 1;
					stage[1] = 0;
					
				} else if(score ==3) {
					//1, 2, 3루타가 홈으로 
					sum+=stage[3];
					sum+=stage[2];
					sum+=stage[1];
					
					stage[3] = 1;
					stage[2] = 0;
					stage[1] = 0;
				}
				
				idx++;
				idx = (idx == SIZE)? 0: idx;
			}
		}
		
		return sum;
	}
	
}
