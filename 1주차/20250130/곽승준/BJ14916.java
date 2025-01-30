package codingTest;

import java.util.Scanner;

public class BJ14916 {
	static int result;
	static void recursive(int num, int n) {
		if(num==0) {
			if(result > n)
				result = n;
			return;
		}
		else if(num<=1)
			return;
		
		recursive(num-5, n+1);
		if(num < 10) {
			recursive(num-2, n+1);
		}
		return;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		result = Integer.MAX_VALUE;
		recursive(num, 0);
		if(num==1|| num==3)
			System.out.println("-1");
		else
			System.out.println(result);
	}
}
