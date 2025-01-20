import java.util.Scanner;

public class Main {
    static int[][] board = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                board[r][c] = sc.nextInt();
            }
        }
        dfs(0, 0, 0);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
        }
    }

    static void dfs(int r, int c, int used) {
        if (used >= ans) return;
        if (c == 10) {
            dfs(r + 1, 0, used);
            return;
        }
        if (r == 10) {
            ans = Math.min(ans, used);
            return;
        }
        if (board[r][c] == 0) {
            dfs(r, c + 1, used);
            return;
        }
        for (int size = 5; size >= 1; size--) {
            if (papers[size] > 0 && check(r, c, size)) {
                attach(r, c, size, 0);
                papers[size]--;
                dfs(r, c + 1, used + 1);
                attach(r, c, size, 1);
                papers[size]++;
            }
        }
    }

    static void attach(int r, int c, int size, int value) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = value;
            }
        }
    }

    static boolean check(int r, int c, int size) {
        if (r + size > 10 || c + size > 10) {
            return false;
        }
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (board[i][j] != 1){
                    return false;
                } 
            }
        }
        return true;
    }
}
