import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(bf.readLine());
		
		int cnt=-1;
		int x=n/5;
		int y;
		while(x>=0)
		{
			if((n-(5*x))%2==0) {
				y=(n-(5*x))/2;
				cnt=x+y;
				break;
			}
			else {
				x--;
			}
		}
		
		System.out.println(cnt);
	
	
	}

}
