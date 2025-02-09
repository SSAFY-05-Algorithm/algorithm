package codingTest;

import java.util.*;
import java.io.*;

//BFS 탐색시 사용
class Pair {
	int x, y;
	public Pair() {};
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

//프림 사용
class Node {
	int sn, en;
	int size;
	public Node() {};
	public Node(int sn, int en, int size) {
		this.sn = sn;
		this.en = en;
		this.size = size;
	}
}

public class BJ17472 {
	static int M, N;
	static int[][] map;
	static List<Node> node;
	static boolean[][] v;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//y
		M = Integer.parseInt(st.nextToken());
		//x
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		v = new boolean[M][N];
		node = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 전체 섬을 노드로 만들기 전에 모든 섬에 번호 부여
		int nodeSize = islandNumbering();
		// 각 섬에서 갈 수 있는 경로를 모두 검색해서 노드화
		mapToNode();
		//프림 알고리즘을 통해 최소 다리의 개수 탐색
		prim(nodeSize);
	}
	
	//모든 섬에 번호 부여
	private static int islandNumbering() {
		int cnt = 1;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !v[i][j]) {
					bfs(j, i, cnt);
					cnt++;
				}
			}
		}
		//cnt = 노드 개수 - 프림 알고리즘에서 사용
		return cnt;
	}
	
	//BFS를 통해 섬 번호 변경
	private static void bfs(int x, int y, int node) {
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(x, y));
		v[y][x] = true;
		map[y][x] = node;
		
		Pair p;
		while(!q.isEmpty()) {
			p = q.poll();
			
			for(int i=0; i<dx.length; i++) {
				int tx = p.x + dx[i];
				int ty = p.y + dy[i];
				
				if(tx>=N || tx <0 || ty>=M || ty<0 || v[ty][tx] || map[ty][tx]==0)
					continue;
				
				if(map[ty][tx]==1) {
					q.add(new Pair(tx, ty));
					v[ty][tx] = true;
					map[ty][tx] = node;
				}
			}
		}
	}
	
	//맵에서 최소 다리의 개수 탐색 후 노드로 변경
	private static void mapToNode() {
		int sn;
		int en;
		for(int y=0; y<M; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]==0)
					continue;
				sn = map[y][x];
				for(int i=0; i<dx.length; i++) {
					int tx = x + dx[i];
					int ty = y + dy[i];
					int cnt = 0;
					
					L:while(true) {
						//맵을 벗어나거나 같은 섬일 경우
						if(tx>=N || tx<0 || ty>=M || ty<0 || map[ty][tx] == sn)
							break;
						//다른 섬과 만났을때
						if(map[ty][tx]!=0) {
							//다리의 길이가 1인 경우
							if(cnt<2)
								break;
							en = map[ty][tx];
							for(int j=0; j<node.size(); j++) {
								//이미 연결된 다리가 존재할 경우
								if((node.get(j).sn == en && node.get(j).en == sn) || (node.get(j).sn == sn && node.get(j).en == en)) {
									//이전 다리 값보다 짧은 거리를 발견한 경우
									if(node.get(j).size > cnt)
										node.get(j).size = cnt;
									break L;
								}
							}
							node.add(new Node(sn, en, cnt));
							break;
						}
						tx += dx[i];
						ty += dy[i];
						cnt++;
					}
				}
			}
		}
	}
	
	//프림 알고리즘
	private static void prim(int V) {
		int[][] adjMat = new int[V][V];
		
		//노드 -> 인접 행렬로 변경
		for(int i=0; i<node.size(); i++) {
			adjMat[node.get(i).sn][node.get(i).en] = node.get(i).size;
			adjMat[node.get(i).en][node.get(i).sn] = node.get(i).size;
		}
		
		//거리 배열
		int[] dist = new int[V];
		Arrays.fill(dist,  Integer.MAX_VALUE);
		//방문 배열
		boolean[] visited = new boolean[V];
		
		//1부터 탐색
		dist[0] = 0;
		dist[1] = 0;
		visited[0] = true;
		
		for(int step = 0; step<V-1; step++) {
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;
			
			for(int i=0; i<dist.length; i++) {
				if(dist[i]<minD && !visited[i]) {
					minIdx = i;
					minD = dist[i];
				}
			}
			//모든 섬이 연결되어 있지 않은 경우
			if(minIdx == -1) {
				System.out.println(-1);
				return;
			}
			visited[minIdx] = true;
			
			//방문하지 않은 노드 중 현재 거리보다 짧은 간선 검색
			for(int i=1; i<V; i++) {
				if(adjMat[minIdx][i]!=0 && !visited[i] && adjMat[minIdx][i]<dist[i])
					dist[i] = adjMat[minIdx][i];
			}
		}
		
		int sum = 0;
		for(int i=0; i<dist.length; i++)
			sum += dist[i];
		System.out.println(sum);
	}
	
	//디버깅
	//탐색한 노드 출력
//	static void printNode() {
//		for(int i=0; i<node.size(); i++) {
//			System.out.println(node.get(i).sn+" "+node.get(i).en+" "+node.get(i).size);
//		}
//	}
	
	//전체 맵 출력
//	static void print() {
//		for(int i=0; i<M; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
	
}
