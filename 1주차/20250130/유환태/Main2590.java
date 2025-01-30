package com.ssafy.backjoon;

import java.util.*;

public class Main2590 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[] papers = new int[6];
		int count = 0;
		for (int i = 0; i < papers.length; i++) {
			papers[i] = sc.nextInt();
		}
		count += papers[5];
		for (int i = 0; i < papers[4]; i++) {
			count += 1;
			papers[0] -= Math.min(papers[0],11);
		}
		for (int i = 0; i < papers[3]; i++) {
			count += 1;
			int rest = 36 - 16;
			rest -= Math.min(papers[1],5)*4;
			papers[1] -= Math.min(papers[1],5);
			papers[0] -= Math.min(rest, papers[0]);
		}
		if(papers[2]>4) {
			count += papers[2]/4;
			papers[2] -= 4*(papers[2]/4);
			
			if(papers[2] == 3) {
				int rest = 9;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],1);
				papers[0] -= Math.min(rest, papers[0]);
			}
			if(papers[2] == 2) {
				int rest = 18;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],3);
				papers[0] -= Math.min(rest, papers[0]);
			}
			if(papers[2] == 1) {
				int rest = 27;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],5);
				papers[0] -= Math.min(rest, papers[0]);
			}
		}else {
			if(papers[2] == 3) {
				int rest = 9;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],1);
				papers[0] -= Math.min(rest, papers[0]);
			}
			if(papers[2] == 2) {
				int rest = 18;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],3);
				papers[0] -= Math.min(rest, papers[0]);
			}
			if(papers[2] == 1) {
				int rest = 27;
				count += 1;
				rest -= Math.min(papers[1],5)*4;
				papers[1] -= Math.min(papers[1],5);
				papers[0] -= Math.min(rest, papers[0]);
			}
		}
		
		if(papers[1]>9) {
			count += papers[1]/9;
			papers[1] -= 9*(papers[1]/9);

		}else {
	
			count += 1;
			papers[0] -= Math.min(36 - papers[1]*4, papers[0]);
			papers[1] = 0;
			
		}
		
		if(papers[0]>36) {
			count += papers[0]/36;
			papers[0] -= 36*(papers[0]/36);
		
		}else {
			count += 1;
			papers[0] = 0;
		}
		System.out.println(count);
	}

}
