import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//인접 행렬로 하라는데요?
//dfs로 하라는데요? 간단해서
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		//인구
		int[] population = new int[N+1];
		//인접리스트
		List<Integer>[] relations = new List[N+1];
		String[] populationArr = br.readLine().split(" ");
		for(int i=1;i<=N;i++) {
			population[i] = Integer.parseInt(populationArr[i-1]);
			relations[i] = new ArrayList<Integer>();
		}
		
		for(int i=1;i<=N;i++) {
			String[] inputs = br.readLine().split(" ");
			
			for(int j=1;j<inputs.length;j++) {
				relations[i].add(Integer.parseInt(inputs[j]));
			}
		}
		
		System.out.print(execute(1, N, new LinkedList<Integer>(), new LinkedList<Integer>(), relations, population));
	}
	
	//두 그룹으로 나누는 경우
	static int execute(int idx, int N, LinkedList<Integer> group1, LinkedList<Integer>group2, List<Integer>[] relations, int[] population){
		if(idx == N+1) {
			if(!isConnected(group1, relations, N) || !isConnected(group2, relations, N)) {
				return Integer.MAX_VALUE;
			}
			
			return calMin(group1, group2, population);
		}
		
		int min = Integer.MAX_VALUE;
		group1.add(idx);
		min = Math.min(min, execute(idx+1, N, group1, group2, relations, population));
		group1.pollLast();
		
		group2.add(idx);
		min = Math.min(min, execute(idx+1, N, group1, group2, relations, population));
		group2.pollLast();
		
		if(idx==1) {
			return min==Integer.MAX_VALUE?-1:min;
		}else {
			return min;
		}
	}
	
	//인구차이 최솟값 구하기 
	static int calMin(List<Integer> group1, List<Integer> group2, int[] populations) {
		int sum1 = 0;
		for(int g:group1) {
			sum1+=populations[g];
		}
		
		int sum2=0;
		for(int g:group2) {
			sum2+=populations[g];
		}
		
		return Math.abs(sum1-sum2);
	}
	
	static boolean isConnected(List<Integer> group, List<Integer>[] relations, int N) {
		if(group.isEmpty()) {
			return false;
		}
		
		boolean[] isThisGroup = new boolean[N+1];
		for(int g:group) {
			isThisGroup[g] = true;
		}
		
		Queue<Integer> go = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int start = group.get(0);
		visited[start] = true;
		go.add(start);
		int cnt = 0;
		
		while(!go.isEmpty()) {
			int now = go.poll();
			cnt++;
			
			for(int next:relations[now]) {
				if(visited[next]) {
					continue;
				}
				
				if(!isThisGroup[next]) {
					continue;
				}
				
				visited[next] = true;
				go.add(next);
			}
		}
		
		return cnt==group.size();
	}
}
