package com.ssafy.boj;

import java.util.*;
import java.io.*;

public class Main17471 {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] per_num = new int[N];
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			per_num[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			for (int j = 0; j < idx; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
//		for (ArrayList<Integer> arrayList : graph) {
//			for (int i = 0; i < arrayList.size(); i++) {
//				System.out.print(arrayList.get(i) +" ");
//			}
//			System.out.println();
//		}
		divide(0,0,new boolean[N]);
	}

	private static void divide(int i, int k, boolean[] bs) {
		// TODO Auto-generated method stub
		if(k==bs.length) {
			calc(bs);
			return ;
		}
		bs[i] = true;
		divide(i+1,k+1,bs);
		bs[i] = false;
		divide(i+1,k+1,bs);
	}

	private static void calc(boolean[] bs) {
		// TODO Auto-generated method stub
		ArrayList<Integer> groupA = new ArrayList<>();
		ArrayList<Integer> groupB = new ArrayList<>();
		for (int i = 0; i < bs.length; i++) {
			if(bs[i]) {
				groupA.add(i);
			}else {
				groupB.add(i);
			}
		}
		if(!groupA.isEmpty()&&!groupB.isEmpty()) {
			System.out.println(groupA + " " + groupB);
			System.out.println(check(groupA, new boolean[N]) + " " + check(groupB, new boolean[N]));
			
		}
		
		
	}

	private static boolean check(ArrayList<Integer> group, boolean[] bs) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(group.get(0));
		int count = 0;

		while(!queue.isEmpty()) {

			int cur = queue.poll();
			if(group.contains(cur)&&!bs[cur]) {
				bs[cur] = true;
				count++;
				for (Integer next : graph.get(cur)) {
					queue.offer(next-1);
				}
			}	
		}
		System.out.println(Arrays.toString(bs));
		
		if(count == group.size()) {
			return true;
		}else {
			return false;
		}
	}

}
