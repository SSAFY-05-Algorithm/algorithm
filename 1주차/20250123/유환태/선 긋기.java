
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<int []> point = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int x1 = Integer.parseInt(input[0]);
			int x2 = Integer.parseInt(input[1]);
			if(x1>x2) {
				int swap = x1;
				x1 = x2;
				x2 = swap; 
			}
			int[] point2_xy = {x1,x2};
			point.add(point2_xy);
			
		}
		Collections.sort(point, (a,b)->Integer.compare(a[0], b[0]));

		int left = point.get(0)[0];

		int right = point.get(0)[1];
		long distance = 0;
		for (int i = 1; i < N; i++) {
			if(point.get(i)[0] <= right && point.get(i)[0] >= left) {
				if(point.get(i)[1] > right) {
					right = point.get(i)[1];
				}
			}else if(point.get(i)[0] > right) {
				distance += right - left;
				left = point.get(i)[0];
				right =point.get(i)[1];
			}
			
			 
		}
		distance += (right - left);

		
		System.out.println(distance);
	}

}
