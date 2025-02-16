import java.util.*;
import java.io.*;
 
 
public class Solution {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
 
            List<int[]> points = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int field = Integer.parseInt(st.nextToken());
                    if (field == 1) {
                        points.add(new int[]{i, j});
                    }
                }
            }
 
            int res = calc(points, N);
            sj.add(String.format("#%d %d", t, res));
        }
 
        System.out.print(sj);
    }
 
    static int calc(List<int[]> points, int N) {
        boolean[][] visited = new boolean[N][N];
 
        for (int[] point : points) {
            visited[point[0]][point[1]] = true;
        }
 
        return backTracking(points, 0, 0, 0, visited, N)[1];
    }
 
    static int[] backTracking(List<int[]> points, int idx, int core, int sum, boolean[][] visited, int N) {
        if (idx == points.size()) {
            return new int[]{core, sum};
        }
 
        int[] now = points.get(idx);
 
        int tempCore = 0;
        int tempLength = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            if (!isPossible(dir, now, visited, N)) {
                continue;
            }
 
            //방문
            int length = visit(dir, now, visited, N, true);
            int[] tempRes = backTracking(points, idx + 1, core + 1, sum + length, visited, N);
            //방문 풀기
            visit(dir, now, visited, N, false);
 
            //tempRes 비교
            if (tempCore < tempRes[0]) {
                tempCore = tempRes[0];
                tempLength = tempRes[1];
            } else if (tempCore == tempRes[0]) {
                if (tempLength > tempRes[1]) {
                    tempLength = tempRes[1];
                }
            }
        }
 
        int[] nonVisitRes = backTracking(points, idx + 1, core, sum, visited, N);
        //tempRes 비교
        if (tempCore < nonVisitRes[0]) {
            tempCore = nonVisitRes[0];
            tempLength = nonVisitRes[1];
        } else if (tempCore == nonVisitRes[0]) {
            if (tempLength > nonVisitRes[1]) {
                tempLength = nonVisitRes[1];
            }
        }
 
        return new int[]{tempCore, tempLength};
    }
 
    static int visit(int[] dir, int[] p, boolean[][] visited, int N, boolean flag) {
        int length = 0;
        int d = 1;
        while (true) {
            int newI = dir[0] * d + p[0];
            int newJ = dir[1] * d + p[1];
 
            if (newI < 0 || newI >= N || newJ < 0 || newJ >= N) {
                return length;
            }
 
            visited[newI][newJ] = flag;
            length++;
            d++;
        }
    }
 
    static boolean isPossible(int[] dir, int[] p, boolean[][] visited, int N) {
        int d = 1;
        while (true) {
            int newI = dir[0] * d + p[0];
            int newJ = dir[1] * d + p[1];
 
            if (newI < 0 || newI >= N || newJ < 0 || newJ >= N) {
                return true;
            }
 
            if (visited[newI][newJ]) {
                return false;
            }
 
            d++;
        }
    }
}
