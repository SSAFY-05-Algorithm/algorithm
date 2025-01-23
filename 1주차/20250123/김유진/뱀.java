import java.util.*;
import java.io.*;

class Point{
	int i;
	int j;
	
	public Point(int i, int j) {
		this.i=i;
		this.j=j;
	}
}

class TimeDirect{
	int time;
	char direct;
	
	public TimeDirect(int time, char direct) {
		this.time = time;
		this.direct = direct;
	}
}

enum Direct{
	up(-1, 0), down(1, 0), left(0, -1), right(0, 1);
	
	int di;
	int dj;
	
	private Direct(int di, int dj) {
		this.di=di;
		this.dj=dj;
	}
}

class Main{
	static int N;
	static int[][] fields;
	static Deque<Point> snake;
	static List<TimeDirect> timeDirects;
	static int time=0;
	static boolean keep = true;
	static Direct nowDirect = Direct.up;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		fields = new int[N+1][N+1];
		while(K-->0) {
			String[] appleDirect = br.readLine().split(" ");
			
			int appleI = Integer.parseInt(appleDirect[0]);
			int appleJ = Integer.parseInt(appleDirect[1]);
			
			fields[appleI][appleJ] = 1;
		}
		
		timeDirects = new ArrayList<>();
		timeDirects.add(new TimeDirect(0, 'D'));
		
		int L = Integer.parseInt(br.readLine());
		while(L-->0) {
			String[] nextDirect = br.readLine().split(" ");
			
			int time = Integer.parseInt(nextDirect[0]);
			char direct = nextDirect[1].charAt(0);
			
			timeDirects.add(new TimeDirect(time, direct));
		}
		
		timeDirects.add(new TimeDirect(Integer.MAX_VALUE, timeDirects.get(timeDirects.size()-1).direct));
		
		snake = new LinkedList<>();
		snake.add(new Point(1, 1));
		fields[1][1] = -1;
		
		for(int i=1;i<timeDirects.size();i++) {
			TimeDirect start = timeDirects.get(i-1);
			TimeDirect end = timeDirects.get(i);
			
			nowDirect = getNextDirect(nowDirect, start.direct);
			
			execute(start.time, end.time);
			
			if(!keep) {
				System.out.print(time);
				
				return;
			}
		}
	}
	
	static void execute(int start, int end) {
		for(int t = start; t<end; t++) {
			time++;
			Point head = snake.peekLast();
			
			int nextI = head.i + nowDirect.di;
			int nextJ = head.j + nowDirect.dj;
			
			if(nextI<1 || nextI>N || nextJ<1 || nextJ>N) {
				keep = false;
				
				return;
			}
			
			if(fields[nextI][nextJ] == -1) {
				keep = false;
				
				return;
			}
			
			snake.add(new Point(nextI, nextJ));
			if(fields[nextI][nextJ] != 1) { //사과 x
				Point tail = snake.pollFirst();
				fields[tail.i][tail.j] = 0;
			}
			
			fields[nextI][nextJ]= -1;
		}
	}
	
	static Direct getNextDirect(Direct nowDirect, char nextDirect) {
		if(nextDirect == 'L') {
			switch(nowDirect) {
			case up:
				return Direct.left;
			case down:
				return Direct.right;
			case right:
				return Direct.up;
			case left:
				return Direct.down;
			}
		}
		
		//D
		switch(nowDirect) {
		case up:
			return Direct.right;
		case down:
			return Direct.left;
		case right:
			return Direct.down;
		case left:
			return Direct.up;
		}
		
		return null;
	}
}
