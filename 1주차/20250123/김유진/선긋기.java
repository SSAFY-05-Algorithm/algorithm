import java.util.*;
import java.io.*;

class Line implements Comparable<Line>{
	int start;
	int end;
	
	public Line(int start, int end) {
		this.start=start;
		this.end=end;
	}
	
	public int compareTo(Line l) {
		int res = Integer.compare(this.start, l.start);
		
		if(res!=0) {
			return res;
		}
		
		return Integer.compare(l.end, this.end);
	}
}

class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Line> lines = new ArrayList<>();
		
		while(N-->0) {
			String[] line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			
			lines.add(new Line(start, end));
		}
		Collections.sort(lines);
		
		Stack<Line> stack = new Stack<>();
		for(Line line:lines) {
			if(stack.isEmpty()) {
				stack.push(line);
				
				continue;
			}
			
			Line peekLine = stack.peek();
			if(!canMerge(peekLine, line)) {
				stack.push(line);
				
				continue;
			}
			
			stack.pop();
			stack.push(mergeLines(peekLine, line));
		}
		
		int sum = 0;
		
		for(Line line:stack) {
			int dist = line.end-line.start;
			
			sum+=dist;
		}
		
		System.out.print(sum);
	}
	
	static boolean canMerge(Line l1, Line l2) {
		if(l1.end<l2.start) {
			return false;
		}
		
		return true;
	}
	
	static Line mergeLines(Line l1, Line l2) {
		return new Line(l1.start, Math.max(l1.end, l2.end));
	}
}
