package com.ssafy.programmers;
import java.util.*;


public class Solution01 {
	static int count = 0;
	static Set<Integer> number = new HashSet();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String numbers = sc.next();
		for (int i = 1; i <= numbers.length(); i++) {
			create_number(numbers, 0, "", i, new boolean[numbers.length()]);
			System.out.println();
		}	
		is_decimal(number);
		
		System.out.println(count);
	}
	private static void is_decimal(Set<Integer> number) {	
		for (Integer num : number) {
//			System.out.println(num);
			if(num < 2) {
				
			}else if(num == 2) {
				count++;
			}
			
			else {
				boolean is_decimal = false;
				for (int i = 2; i < Math.sqrt(num)+1; i++) {
					if(num%i==0) {
						is_decimal = true;
					}
				}	
				if(is_decimal==false) {
					count++;
//					System.out.println("소수");
				}
			}	
		}
		return ;
	}
	public static void create_number(String numbers, int idx, String sel, int depth, boolean[] seleted) {
		if(idx == depth) {			
			number.add(Integer.parseInt(sel));
			return ;
		}
		
		for(int i=0; i < numbers.length();i++) {
			
			if(!seleted[i]) {
			 seleted[i] = true;
			 
			 create_number(numbers, idx+1,sel + numbers.charAt(i),depth,seleted);
			 seleted[i] = false;
			 
			}
			
		}
		return ;
	}
}
