package codingTest;

import java.util.*;

public class BJ1339 {
	static int[][] map;
	static String[][] A;
	static Set<String> alphabet;	//알파벳 사용
	static Map<String, Integer> alpha;
	static int N;
	static int maxSize;
	static int SIZE = 10;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new String[N][SIZE];
		for(int i=0; i<N; i++) {
			Arrays.fill(A[i], "0");
		}
		
		alphabet = new HashSet<>();
		maxSize = 0;
		
		//배열에 단어를 하나씩 저장
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			if(tmp.length() > maxSize) maxSize = tmp.length();
			int k = 0;
			for(int j=9; j>=9-tmp.length(); j--) {
				if(j==9) A[i][j] = tmp.substring(tmp.length());
				else {
					A[i][j] = tmp.substring(tmp.length()-k,tmp.length()-k+1);
					alphabet.add(A[i][j]);
				}
				k++;
			}
			A[i][9] = "0";
		}
		
		Iterator it = alphabet.iterator();
		// 중복을 제거한 알파벳 저장
		alpha = new HashMap<>();
		ArrayList<String> al = new ArrayList<>();
		
		// cnt : 문제에서 사용한 알파벳 개수
		int cnt = 0;
		// alpha에 알파벳+인덱스 저장, al에 알파벳 저장
		while(it.hasNext()) {
			String tmp = (String)it.next();
			alpha.put(tmp, cnt);
			al.add(tmp);
			cnt++;
		}
		
		// map : 문제에서 어느 자리에 해당 알파벳이 사용되었는지 저장
		map = new int[cnt][SIZE];
		for(int i=0; i<cnt; i++) {
			Arrays.fill(map[i], 0);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<SIZE; j++) {
				if(A[i][j].equals("0")) {
					continue;
				}
				map[alpha.get(A[i][j])][j] += 1;
			}
		}
		
		// cmp : map에 저장된 숫자를 int형으로 변환해서 저장
		int[] cmp = new int[cnt];
		for(int i=0; i<cnt; i++) {
			cmp[i] = arrayToString(map[i]);
		}
		
		//cmp를 기반으로 가장 큰 수부터 9부터 0까지 할당, 해당 값을 alpha에 저장
		boolean[] v = new boolean[cnt];
		for(int i=0; i<cnt; i++) {
			int max = 0;
			int idx = 0;
			for(int j=0; j<cnt; j++) {
				if(!v[j]) {
					if(max < cmp[j]) {
						max = cmp[j];
						idx = j;
					}
				}
			}
			v[idx] = true;
			alpha.put(al.get(idx), 9-i);
		}
		
		//저장된 값을 활용해서 문제에 대입 후 계산
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += alphabetToNum(A[i]);
		}
		System.out.println(sum);
		sc.close();
	}
	
	static int arrayToString(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<SIZE; i++) {
			sb.append(arr[i]);
		}
		return Integer.parseInt(sb.toString());
	}
	
	static int alphabetToNum(String[] arr) {
		StringBuffer sb = new StringBuffer();
		for(String i : arr) {
			if(i.equals("0")) continue;
			sb.append(alpha.get(i));
		}
		return Integer.parseInt(sb.toString());
	}
}
