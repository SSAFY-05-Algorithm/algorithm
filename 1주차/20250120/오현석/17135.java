import java.util.*;

public class Main {
    public static int n,m,d;
    public static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();
        map = new int[n][m];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        /*
        for(int x = 0; x<m-2 ; x++) {
            for(int y = x+1; y<m-1 ; y++) {
                for(int z = y+1; z<m ; z++) {
                    ans = Math.max(ans, kill_Score(x,y,z,0));
                }
            }
        }
        */
        ans = Math.max(ans, kill_Score(0,2,5,0));
        System.out.print(ans);

    }

    private static int kill_Score(int ax, int ay, int az, int lv) {
        int ks = 0;
        if(lv >=20) {
            return 0;
        }
        int[][] x = new int[3][2];
        x[0] = check(ax,lv);
        x[1] = check(ay,lv);
        x[2] = check(az,lv);
        for(int[] chk :x) {
            if(!(chk[0] ==-1)) {
                if(map[chk[0]][chk[1]] == 1) {
                    ks+=1;
                    map[chk[0]][chk[1]] = 0;
                }
            }
        }
        ks += kill_Score(ax,ay,az,lv+1);
        for(int[] chk :x) {
            if(!(chk[0] ==-1)) {
                map[chk[0]][chk[1]] = 1;
            }
        }
        return ks;
    }

private static int[] check(int a, int lv) {
    for(int i = 0; i<d;i++) {
        int x = a - i;
        for(int j = 0; j<i; j++) {
            if(lv + j <n && x>=0 && x<m) {
                if(map[lv + j][x] == 1) {
                    return new int[]{lv + j,x};
                }
            }
            x++;
        }
        for(int j = i; j>=0;j--) {
            if(lv + j <n && x>=0 && x<m) {
                if(map[lv + j][x] == 1) {
                    return new int[]{lv + j,x};
                }
            }
            x++;
        }
        
    }
    return new int[]{-1,-1};
}
}

