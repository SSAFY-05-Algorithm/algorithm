package codingTest;

import java.io.*;
import java.util.*;

public class BJ2141 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Set<Integer> set = new HashSet<>();
		int N = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> distance = new HashMap<>();
//		int[][] distance = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			distance.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		List<Integer> keySort = new ArrayList<>(distance.keySet());
		Collections.sort(keySort);
		boolean[] v = new boolean[N];
		int[] sum = new int[N];
		Arrays.fill(sum, 0);
		
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<N; j++) {
				if(v[i]) {
					sum[i] += Math.abs(keySort.get(i) - keySort.get(j)) * distance.get(keySort.get(j)); 
				} else {
					sum[i] += Math.abs(keySort.get(j) - keySort.get(i)) * distance.get(keySort.get(j));
				}
			}
			v[i] = true;
		}
		int min = Integer.MAX_VALUE;
		for(int i : sum)
			if(min > i)
				min = i;
		
		for(int i=0; i<N; i++) {
			if(sum[i] == min) {
				System.out.println(keySort.get(i));
			}
		}
	}
}
