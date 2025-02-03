import java.util.*;
import java.io.*;

class Point {
    int i, j, k;

    public Point(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }
}

public class Main {
    static int K, W, H;
    static int[][] fields;
    static boolean[][][] visited;
    static Queue<Point> queue;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] specialMoves = {
        {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
        {-1, -2}, {1, -2}, {-1, 2}, {1, 2}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        fields = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue = new LinkedList<>();
        visited = new boolean[H][W][K + 1];

        int res = calculate();
        System.out.print(res);
    }

    static int calculate() {
        queue.add(new Point(0, 0, 0));
        visited[0][0][0] = true;

        int turn = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point now = queue.poll();

                if (now.i == H - 1 && now.j == W - 1) {
                    return turn;
                }

                // Standard moves
                for (int[] dir : directions) {
                    int ni = now.i + dir[0];
                    int nj = now.j + dir[1];

                    if (isInside(ni, nj) && fields[ni][nj] == 0 && !visited[ni][nj][now.k]) {
                        visited[ni][nj][now.k] = true;
                        queue.add(new Point(ni, nj, now.k));
                    }
                }

                // Special moves
                if (now.k < K) {
                    for (int[] move : specialMoves) {
                        int ni = now.i + move[0];
                        int nj = now.j + move[1];

                        if (isInside(ni, nj) && fields[ni][nj] == 0 && !visited[ni][nj][now.k + 1]) {
                            visited[ni][nj][now.k + 1] = true;
                            queue.add(new Point(ni, nj, now.k + 1));
                        }
                    }
                }
            }
            turn++;
        }

        return -1;
    }

    static boolean isInside(int i, int j) {
        return i >= 0 && i < H && j >= 0 && j < W;
    }
}
