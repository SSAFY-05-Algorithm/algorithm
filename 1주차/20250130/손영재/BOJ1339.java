import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(bf.readLine());
		String[] str=new String[N];
		int weight[]=new int[26];
		for (int i = 0; i < N; i++) {
			str[i]=bf.readLine();
			for(int j=0;j<str[i].length();j++) {
				weight[(int)(str[i].charAt(j)-'A')]+=Math.pow(10,str[i].length()-j-1);
			}
		}
		
		List<Character> alpha=new ArrayList<>();
		for(char c='A';c<='Z';c++) {
			if(weight[c-'A']>0) {
				alpha.add(c);
			}
		}
		
		alpha.sort((a,b)->Integer.compare(weight[b-'A'], weight[a-'A']));
		
		int num=9;
		for(int i=0;i<alpha.size();i++) {
			weight[alpha.get(i)-'A']=num--;
		}
		int result=0;
		for(int i=0;i<str.length;i++) {
			String tmp="";
			for(int j=0;j<str[i].length();j++) {
				tmp=tmp+weight[str[i].charAt(j)-'A'];
			}
			result+=Integer.parseInt(tmp);
//			System.out.println(tmp);
		}
		System.out.println(result);
		
		
	}
}
