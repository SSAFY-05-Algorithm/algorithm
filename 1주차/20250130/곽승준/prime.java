package codingTest;

import java.io.*;
import java.util.*;

public class test {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Set<Integer> set = new HashSet<>();
		int N = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> distance = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			distance.put(d, p);
		}
		int[] sumArr = new int[N];
		int[] revArr = new int[N];
		List<Integer> keySort = new ArrayList<>(distance.keySet());
		Collections.sort(keySort);
		
		for(int i=N-1; i>=0; i--) {
			if(i==N-1) {
				revArr[i] = distance.get(keySort.get(i));
			} else {
				revArr[i] = revArr[i+1] + distance.get(keySort.get(i));
			}
		}
		for(int i=0; i<N; i++) {
			if(i==0) {
				sumArr[i] = distance.get(keySort.get(i));
			} else {
				sumArr[i] = sumArr[i-1] + distance.get(keySort.get(i));
			}
		}
		
		int[] idx = new int[N/2];
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(sumArr[i]>=revArr[i]) {
				idx[cnt] = i;
				cnt++;
				break;
			}
		}
		
		
		
		boolean[] v = new boolean[N];
		int[] sum = new int[N];
		Arrays.fill(sum, 0);
		
		int count = 0;
		for(int i=0; i<N; i++) {
			if(count == cnt)
				break;
			if(i==idx[count]-1 || i==idx[count]) {
				for(int j=0; j<N; j++) {
					sum[i] += Math.abs(keySort.get(i) - keySort.get(j)) * distance.get(keySort.get(j));
				}
				count++;
			}
			v[i] = true;
		}
		int min = Integer.MAX_VALUE;
		for(int i : sum) {
			if(min > i)
				min = i;
		}
		
		for(int i=0; i<N; i++) {
			if(sum[i] == min) {
				System.out.println(keySort.get(i));
				break;
			}
		}
	}
}
