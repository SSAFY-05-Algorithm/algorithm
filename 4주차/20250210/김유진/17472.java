import java.util.*;
import java.io.*;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

class Road implements Comparable<Road>{
    int start;
    int end;
    int dist;

    public Road(int start, int end, int dist){
        this.start=start;
        this.end=end;
        this.dist=dist;
    }

    public int compareTo(Road r){
        return Integer.compare(this.dist, r.dist);
    }
}

class Main{
    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] fields = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandCnt = doNumbering(fields, N, M);

        PriorityQueue<Road> queue = new PriorityQueue<>();
        for(int i=0; i<N;i++){
            for(int j=0;j<M;j++){
                if(fields[i][j] == 0){
                    continue;
                }

                int start = fields[i][j];
                //상
                if(i-1 >= 0 && fields[i-1][j] == 0){
                    for(int r = i-2; r>=0; r--){
                        if(fields[r][j] > 0 ){
                            int dist = i-1-r;
                            if(dist < 2 ){
                                break;
                            }

                            queue.add(new Road(start, fields[r][j] ,dist));
                            break;
                        }
                    }
                }

                //하
                if(i+1<N && fields[i+1][j] == 0){
                    for(int r = i+2; r<N; r++){
                        if(fields[r][j] > 0){
                            int dist = r-1-i;

                            if(dist<2){
                                break;
                            }

                            queue.add(new Road(start, fields[r][j], dist));
                            break;
                        }
                    }
                }

                //좌
                if(j-1 >= 0 && fields[i][j-1] == 0){
                    for(int c = j-2; c>=0; c--){
                        if(fields[i][c] > 0){
                            int dist = j-1-c;
                            if(dist<2){
                                break;
                            }

                            queue.add(new Road(start, fields[i][c], dist));
                            break;
                        }
                    }
                }

                //우
                if(j+1<M && fields[i][j+1] == 0){
                    for(int c = j+2; c<M; c++){
                        if(fields[i][c] >0){
                            int dist = c-1-j;
                            if(dist<2){
                                break;
                            }

                            queue.add(new Road(start, fields[i][c], dist));
                            break;
                        }
                    }
                }
            }
        }

        //kruskal
        int[] parents = new int[8];
        for(int i=2;i<8;i++){
            parents[i] = i;
        }

        int sum = 0;
        while(!queue.isEmpty()){
            Road road = queue.poll();

            int start = findParent(parents, road.start);
            int end = findParent(parents, road.end);

            //cycle 생성됨 -> x
            if(start == end){
                continue;
            }

            sum+=road.dist;
            union(parents, start, end);
        }


        //연결 다 됐는지 확인
        if(!isMstCreated(parents, islandCnt)){
            sum = -1;
        }

        System.out.print(sum);
    }

    static boolean isMstCreated(int[] parents, int islandCnt){
        int parent = findParent(parents, 2);

        for(int idx = 3; idx<2+islandCnt; idx++){
            int nowParent = findParent(parents, idx);

            if(parent != nowParent){
                return false;
            }
        }

        return true;
    }

    static void union(int[] parents, int node1, int node2){
        node1 = findParent(parents, node1);
        node2 = findParent(parents, node2);

        if(node1<node2){
            parents[node2] = parents[node1];
        } else {
            parents[node1] = parents[node2];
        }
    }

    static int findParent(int[] parents, int idx){
        if(parents[idx] == idx){
            return idx;
        }

        return findParent(parents, parents[idx]);
    }

    static int doNumbering(int[][] fields, int N, int M){
        int idx = 2;

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(fields[i][j] !=1){
                    continue;
                }

                cnt++;
                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(i, j));
                fields[i][j] = idx;
                while(!queue.isEmpty()){
                    Point now = queue.poll();

                    for(int[] dir: dirs){
                        int newI=now.i+dir[0];
                        int newJ=now.j+dir[1];

                        if(newI<0 || newI==N || newJ<0 || newJ==M){
                            continue;
                        }


                        if(fields[newI][newJ] != 1){
                            continue;
                        }

                        fields[newI][newJ] = idx;
                        queue.add(new Point(newI, newJ));
                    }
                }

                idx++;
            }
        }

        return cnt;
    }
}
