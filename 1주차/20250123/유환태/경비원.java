
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int width = sc.nextInt();
		int length = sc.nextInt();
		int map[] = new int[(width+length)*2];
		int store_num = sc.nextInt();
		
		int[][] store = new int[store_num+1][2];
		for(int i = 0; i < store_num+1; i++){
			store[i][0] = sc.nextInt();
			store[i][1] = sc.nextInt();
		}
		for (int i = 0; i < map.length; i++) {
			map[i] = 0;
		}
		int seq_loc = 0;
		for(int i = 0; i < store_num+1; i++){
			if(i==store_num) {
				if(store[i][0]==1) {
					seq_loc = length+store[i][1];
				}else if(store[i][0] ==2) {
					seq_loc = length*2+width*2-store[i][1];
				}
				else if(store[i][0] ==3) {
					seq_loc = length-store[i][1];
				}
				else if(store[i][0] ==4) {
					seq_loc = length+width+store[i][1];
				}
				
			}else {
				if(store[i][0]==1) {
					map[length+store[i][1]] += 1;
				}else if(store[i][0] ==2) {
					map[length*2+width*2-store[i][1]] += 1;
				}
				else if(store[i][0] ==3) {
					map[length-store[i][1]] += 1;
				}
				else if(store[i][0] ==4) {
					map[length+width+store[i][1]] += 1;
				}
				
			}
		}
		int distance = 0;
		for (int i = 0; i < map.length; i++) {
			
			if(map[i] >= 1) {
				
				distance += Math.min(Math.abs(seq_loc - i), (width+length)*2 -  Math.abs(seq_loc - i))*map[i];
				
			}
		}
		System.out.println(distance);
			
		
	}

}
