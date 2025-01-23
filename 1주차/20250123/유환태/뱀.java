
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N][N];
		int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
		
		Queue<int[]> snape = new LinkedList<>(); 
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = 0;
			}
		}
		
		
		for (int i = 0; i < K; i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			map[col-1][row-1] = 1;
		}
		
		int Dic_count = sc.nextInt();
		int[] Dic_time = new int[Dic_count];
		String[] Dic = new String[Dic_count];
		
		for (int i = 0; i < Dic_count; i++) {
			Dic_time[i] = sc.nextInt();
			Dic[i] = sc.next();
		}
		
		int snape_x = 0;
		int snape_y = 0;
		int direction_idx = 0;
		int time = 0;
		int time_idx = 0;
		int[] base = {0,0};
		snape.offer(base);
		while(true) {
			time++;
			
			snape_x += direction[direction_idx][0];
			snape_y += direction[direction_idx][1];
			if(snape_x >= N || snape_y >= N || snape_x < 0 || snape_y < 0 ) {
				break;
			}
			
			int[] location = {snape_x, snape_y};
			boolean snape_conflict = false;
			for(int[] snape_body: snape) {
				if(Arrays.equals(snape_body,location)) {
					snape_conflict = true;
				}
			}
			
			if(snape_conflict==true) break;
			if(map[snape_x][snape_y]==1) {
				map[snape_x][snape_y] = 0;
				snape.offer(location);
			}else {
				snape.offer(location);
				snape.poll();
			}
			if(time_idx < Dic_count) {
				
				if(time == Dic_time[time_idx]) {
					
					if(Dic[time_idx].equals("L")) {
		
						direction_idx -= 1;
						if(direction_idx == -1) {
							direction_idx = 3;
						}
					}else {
						
						direction_idx += 1;
						if(direction_idx == 4) {
							direction_idx = 0;
						}
					}
					time_idx++;
				}
			}
		}
		System.out.println(time);
	}	

}
