import java.util.*;
public class Main {

	static int R,C;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N;
		R = sc.nextInt();
		C = sc.nextInt();
		N = sc.nextInt();
		int[][] arr = new int[N][2];
		for(int i = 0; i < N ; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		int[] X = new int[2];
		X[0] = sc.nextInt();
		X[1] = sc.nextInt();
		
		int ans = 0;
		for(int i = 0; i < N ;i ++) {
			ans += solve(arr[i],X);
		}
		System.out.println(ans);
	}
	private static int solve(int[] is, int[] x) {
		int map  = (R+C) *2;
		
		if(is[0] == x[0]) return Math.abs(is[1]-x[1]);
		else if(is[0]>x[0]){
			return solve(x,is);
		}
		else if(is[0] == 1 && x[0] == 2){
			int tmp = is[1] + x[1] + C;
			return Math.min(tmp,map-tmp);
		}
		else if(is[0] == 1 && x[0] == 3){
			int tmp = is[1] + x[1];
			return Math.min(tmp,map-tmp);
		}
		else if(is[0] == 1 && x[0] == 4){
			int tmp = R-is[1] + x[1] ;
			return Math.min(tmp,map-tmp);
		}
		else if(is[0] == 2 && x[0] == 3){
			int tmp = is[1] + C- x[1] ;
			return Math.min(tmp,map-tmp);
		}
		else if(is[0] == 2 && x[0] == 4){
			int tmp = R-is[1] + C- x[1] ;
			return Math.min(tmp,map-tmp);
		}
		else if(is[0] == 3 && x[0] == 4){
			int tmp = is[1] + R + x[1] ;
			return Math.min(tmp,map-tmp);
		}
		else {
			return 0;
		}
		
	}

}
