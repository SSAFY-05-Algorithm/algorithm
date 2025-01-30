package com.ssafy.backjoon;

import java.util.*;

public class Main1339 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] alpha = new int[26];
		
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for(int j = 0; j < s.length(); j++) {
				alpha[s.charAt(j)-'A'] += Math.pow(10, s.length() - j - 1);
			}
		}
		Arrays.sort(alpha);

		int sum = 0;
		int count = 9;
		for (int i = 25; i >= 16; i--) {
			sum = sum + alpha[i]*count;
			count--;
		}
		System.out.println(sum);
	}

}
