import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int MAX = 6;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] papers = new int[MAX+1];
		
		for(int i=1;i<=MAX;i++) {
			papers[i] = Integer.parseInt(br.readLine());
		}
		
		int res = 0;
		
		//6
		res += papers[6];
		
		//5
		res += papers[5];
		
		//5- 1 남는 부분 제거
		int remains = (MAX*MAX - 5*5)*papers[5];
		papers[1] -= remains;
		
		//4
		res += papers[4];
		
		//4- 2 남는 부분 제거 
		int remains2 = Math.min(5*papers[4], papers[2]);
		papers[2] -= remains2;
		
		//4-1 남는 부분 제거 
		remains = (5*papers[4] - remains2)*4;
		papers[1] -= remains;
		
		//3
		int usedPaper3 = papers[3]==0?0:papers[3] / 4;
		res += usedPaper3;
		
		//3- 2, 1 남는 부분 제거
		int remaindPaper3 = papers[3] % 4;
		if(remaindPaper3 > 0) {
			res ++;
			
			if(remaindPaper3 == 1) {
				remains2 = 5;
			} else if(remaindPaper3 == 2) {
				remains2 = 3;
			} else if(remaindPaper3 == 3) {
				remains2 = 1;
			}
			
			//2 제거
			remains2 = Math.min(papers[2], remains2);
			papers[2] -= remains2;
			
			//1 제거
			remains = MAX*MAX - remains2*2*2 - 3*3*remaindPaper3;
			papers[1] -= remains;
		}
		
		//2
		res+= papers[2] / 9;
		
		int remaindPaper2 = papers[2] % 9;
		if(remaindPaper2 > 0) {
			res++;
			remains = (MAX*MAX - (remaindPaper2)*2*2);
			papers[1] -= remains;
		}
		
		//1
		if(papers[1] <= 0) {
			System.out.print(res);
			
			return;
		}
		
		res += papers[1] / (MAX*MAX);
		res += (papers[1] % (MAX*MAX) == 0)? 0: 1;
		System.out.print(res);
	}
}
