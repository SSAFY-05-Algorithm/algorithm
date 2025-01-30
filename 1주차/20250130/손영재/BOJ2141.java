import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N=Integer.parseInt(bf.readLine());
		ArrayList<Integer[]> position=new ArrayList<>();
		int x,a;
//		int X[]=new int[N]; //마을 위치
//		int A[]=new int[N]; //사람 수
		long middle=0;
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(bf.readLine());
			
			x=Integer.parseInt(st.nextToken());
			a=Integer.parseInt(st.nextToken());
			middle+=a;
			position.add(new Integer[] {x,a});
		}
		position.sort(Comparator.comparingInt(p->p[0]));
		middle=(middle+1)/2;
		long sum=0;
		int result = 0;
		for (int i = 0; i < position.size(); i++) {
			sum+=position.get(i)[1];
			if(sum>=middle) {
				result=position.get(i)[0];
				break;
				}
			
		}
		System.out.println(result);
		
	}

}
