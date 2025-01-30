package com.ssafy.swea;
import java.util.*;
import java.io.*;

public class Route {
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i=1; i<=T; i++) {
			min = Integer.MAX_VALUE;
			int N = sc.nextInt();
			int[][] points = new int[N+2][2];
			
			// 0 = 회사 1 = 집 2~N+1 = 고객
			for (int j = 0; j < N+2; j++) {
				points[j][0] = sc.nextInt();
				points[j][1] = sc.nextInt();
			}
			recursive(points, 0, new int[N][2], new boolean[N]);
			System.out.printf("#%d %d\n",i,min);
		}
	}
	
	public static void recursive(int[][] points, int idx, int[][] sel, boolean[] visited) {
		
		if(idx==sel.length) {
			int sum = 0;
			sum += Math.abs(points[0][0]-sel[0][0])+Math.abs(points[0][1]-sel[0][1]);
			sum += Math.abs(points[1][0]-sel[sel.length-1][0])+Math.abs(points[1][1]-sel[sel.length-1][1]);
			for(int i=0;i<sel.length-1;i++) {
				sum += Math.abs(sel[i][0]-sel[i+1][0])+Math.abs(sel[i][1]-sel[i+1][1]);
			}
			min = Math.min(min, sum);
			return ;
		}
		
		
		
		for (int i = 2; i < points.length; i++) {
			if(!visited[i-2]) {
				visited[i-2] = true;
				sel[idx][0] = points[i][0];
				sel[idx][1] = points[i][1];
				recursive(points, idx+1, sel, visited);
				visited[i-2] = false;
				
			}
			
		}
		
	}

}
