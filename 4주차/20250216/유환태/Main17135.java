import java.util.*;
import java.util.function.IntPredicate;
import java.io.InputStream;
import java.io.ObjectInputFilter.Status;
import java.lang.String;

public class Main17135 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N, M, D;
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		int[][] map = new int[M][N+1];
		int[] archer = new int[3];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[j][i] = sc.nextInt();
			}
		}
		int[][] map_copy = map.clone();
		for (int i = 0; i < M; i++) {
			map[i][N] = 0;
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0;i<M-2;i++) {
			for(int j=i+1;j<M-1;j++) {
				for(int k=j+1;k<M;k++) {
					for (int k2 = 0; k2 < map.length; k2++) {
						map[k2] = map_copy[k2].clone();
					}
					int kill = 0;
					archer[0] = i;
					archer[1] = j;
					archer[2] = k;
					map[i][N] = 1;
					map[j][N] = 1;
					map[k][N] = 1;

					for(int archer_loc=N; archer_loc>0;archer_loc--) {
						
						Set<List<Integer>> killedHashSet = new HashSet<>();
						killedHashSet.clear();
						for(int c=0;c<3;c++) {
							int min_distance = Integer.MAX_VALUE;
							int min_row = 0;
							int min_col = 0;
							for(int enemy_col = archer_loc-1;enemy_col>=0;enemy_col--) {
								for(int enemy_row = M-1; enemy_row>=0;enemy_row--) {
									if(map[enemy_row][enemy_col]==1) {
										int distance = (Math.abs(enemy_col-archer_loc)+Math.abs(enemy_row-archer[c]));
										if(distance == min_distance) {
											if(min_row > enemy_row) {
												min_distance = distance;
												min_col = enemy_col;
												min_row = enemy_row;
											}
										}else {
											if(0< distance && distance <=D && min_distance > distance) {
												min_distance = distance;
												min_col = enemy_col;
												min_row = enemy_row;
												
											}
										}
										
									}
								}
							}
							if(min_distance != Integer.MAX_VALUE) {
								Integer[] array = {min_col, min_row};
								
								killedHashSet.add(Arrays.asList(array));
								
								
							}
						}
						for(List<Integer> array : killedHashSet) {

							
							int killed_row = array.get(0);
							int killed_col = array.get(1);
							map[killed_col][killed_row] = 0;
							kill++;
						}

					}
					if(max < kill) {max = kill;}
					for (int b = 0; b < M; b++) {
						map[b][N] = 0;
					}
					

				}
			}
		}
		System.out.println(max);
	}
	
}
