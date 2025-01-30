package com.ssafy.backjoon;

import java.util.*;
import java.io.*;

public class Main1092 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Integer[] cranes = new Integer[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
        	cranes[i] = Integer.parseInt(st.nextToken());
    	}
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        Integer[] boxs = new Integer[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
        	boxs[i] = Integer.parseInt(st.nextToken());
    	}
        
		Arrays.sort(cranes, Collections.reverseOrder());
		Arrays.sort(boxs, Collections.reverseOrder());
		
		int time = 0;
		boolean[] visited = new boolean[M];
		int[] crane_point = new int[N];
		if(cranes[0] < boxs[0]) {
			System.out.println(-1);
			return ;
		}

		while(M>0) {
			for (int crane_idx = 0; crane_idx < N; crane_idx++) {
				if(M==0) {
					break;
				}
				for (int j = crane_point[crane_idx]; j < boxs.length; j++) {
					if(visited[j]) continue;
					if(cranes[crane_idx] < boxs[j]) {
						crane_point[crane_idx]++;
						continue;
					}
					else if(cranes[crane_idx]>= boxs[j] ) {
						visited[j] = true;
						M--;
						break;
					}
					
				}
			}
			time++;
		}
		System.out.println(time);

	}

}
