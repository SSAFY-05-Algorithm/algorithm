//import java.awt.print.Printable;
import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] map = new int[n+2][n+2];
		for(int i = 0; i<n+2 ; i++) {
			map[i][0] =1; map[i][n+1] = 1; //가로
			map[0][i] =1; map[n+1][i] = 1; //세로
		}
		int apple = sc.nextInt();
		for(int i = 0 ; i<apple ; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = 2;
		}
		int L = sc.nextInt();
		Queue<Direction> directions = new LinkedList<>();
		for(int i = 0; i < L;i++){
			int t = sc.nextInt();
			char d = sc.next().charAt(0);
			directions.offer(new Direction(t, d));
		}
		//PrintMap(map);
		int[] x = {0,-1,0,1};
		int[] y = {1,0,-1,0};
		int ans = 0;
		int d = 0;
		Point p = new Point(1, 1);
		Queue<Point> snake = new LinkedList<>();
		snake.offer(new Point(p.x, p.y));
		map[1][1] = 1;
		//PrintMap(map);
		while(true) {
			if(!directions.isEmpty() && ans == directions.peek().get_time()) {
				d += directions.peek().get_move();
				if(d<0)d = 3;
				if(d>3)d = 0;
				directions.poll();
			}
			ans += 1;
			p.x+=x[d];
			p.y+=y[d];
			if(map[p.x][p.y]==1) {
				break;
			}
			else if(map[p.x][p.y]==2) {
				snake.offer(new Point(p.x, p.y));
				map[p.x][p.y] =1;
			}
			else {
				snake.offer(new Point(p.x, p.y));
				map[p.x][p.y] =1;
				map[snake.peek().x][snake.peek().y] = 0;
				snake.poll();
			}
			//System.out.println(ans);
			//PrintMap(map);
		}
		System.out.println(ans);
		
	}
//	private static void PrintMap(int[][] map) {
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map.length; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();
//	}
	static class Direction{
		int time;
		char direction;
		Direction(int time,char direction){
			this.time = time; this.direction = direction;
		}
		int get_time() {return time;}
		int get_move() {return direction == 'L' ? 1: -1;}
	}
	static class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x=x; this.y=y;
		}
	}
	
	

}
