package com.ssafy.backjoon;

import java.util.*;

public class Main14916 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int price = sc.nextInt();
		
		for (int i = 0; i <= price/5; i++) {
			int now_price = price - i*5;
			if(now_price%2==0) {
				min = Math.min(min, now_price/2+i);
			}
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}

}
