package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17135 {
	public static int[][] castle;
	public static int cols;
	public static int rows;
	public static int range;
	
	public static void move() {
		for(int col=cols-1; col>=0; col--) {
			for(int row=0; row<rows; row++) {
				castle[col][row] = castle[col+1][row];
			}
		}
	}
	
	public static boolean isRemainEnemy() {
		for(int col=0; col<cols; col++) {
			for(int row=0; row<rows; row++) {
				if(castle[col][row] == 1)
					return true;
			}
		}
		return false;
	}
	
	public static boolean attack(int col, int row, int cnt) {
		if(col == 0 || row == 0 || cnt == 0) return false;
		if(castle[col][row] == 1) {
			castle[col][row] = 0;
			return false;
		}
		attack(col, row-1, cnt-1);
		attack(col-1, row, cnt-1);
		attack(col, row+1, cnt-1);
	}
	
	public static void game() {
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		cols = Integer.parseInt(st.nextToken());
		rows = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		
		castle = new int[cols+1][rows];
		
		for(int col=0; col<cols; col++) {
			st = new StringTokenizer(br.readLine());
			for(int row=0; row<rows; row++) {
				castle[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}

}
