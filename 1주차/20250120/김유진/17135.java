import java.io.*;
import java.util.*;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }

    static int calDist(Point p1, Point p2){
        return Math.abs(p2.i-p1.i)+Math.abs(p2.j-p1.j);
    }
}

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] fields = new int[N][M];

        for(int i=0;i<N;i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(backTracking(0, new LinkedList<>(), N, M, D, fields));
    }

    static int backTracking(int col, List<Integer> group, int N, int M, int D, int[][] fields){
        int max = 0;

        if(group.size() == 3){
            return calculate(group, N, M, D, fields);
        }

        if(col == M){
            return 0;
        }

        //포함
        group.add(col);
        max = Math.max(max, backTracking(col+1, group, N, M, D, fields));
        group.remove(group.size()-1);

        //미포함
        max = Math.max(max, backTracking(col+1, group, N, M, D, fields));

        return max;
    }

    static int calculate(List<Integer> group, int N, int M, int D, int[][] fields){
        Set<Integer> enemies = new HashSet<>();

        boolean[][] visited = new boolean[N][M];
        for(int i=N;i>0;i--){
            Set<Point> attackedEnemyThisTurn = new HashSet<>();
            for(int soldier:group){
                Point p = new Point(i, soldier);

                Point enemy = getCloseEnemy(p, M, D, fields, visited);
                if(enemy == null){
                    continue;
                }

                int enemyNumber = (enemy.i-1)*M+enemy.j;

                enemies.add(enemyNumber);
                attackedEnemyThisTurn.add(enemy);
            }

            for(Point p:attackedEnemyThisTurn){
                visited[p.i][p.j] = true;
            }
        }

        return enemies.size();
    }

    static Point getCloseEnemy(Point arrow, int M, int D, int[][] fields, boolean[][] visited){
        int left = arrow.j-D+1;
        left = left<0?0:left;

        int right = arrow.j+D;
        right = right>=M?M-1:right;

        int up = arrow.i-D;
        up = up<0?0:up;

        Point enemy = null;
        int minDist = Integer.MAX_VALUE;
        for(int i=arrow.i-1;i>=up;i--){
            for(int j=left;j<=right;j++){
                if(fields[i][j] == 0){
                    continue;
                }

                if(visited[i][j]){
                    continue;
                }

                Point p = new Point(i, j);
                int dist = Point.calDist(arrow, new Point(i, j));

                if(dist > D){
                    continue;
                }

                if(minDist>dist){
                    minDist = dist;
                    enemy = p;
                } else if (minDist == dist){
                    if(enemy.j > p.j){
                        enemy = p;
                    }
                }
            }
        }

        return enemy;
    }

}
