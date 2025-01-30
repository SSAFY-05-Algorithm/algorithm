import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args ) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int max = n/5;

        for(int i=max;i>=0; i--){
            if(isPossible(n, i)){
                int res = i;
                res += (n-i*5) / 2;

                System.out.println(res);

                return;
            }
        }

        System.out.println(-1);
    }

    static boolean isPossible(int n, int i){
        n -= i*5;

        return n%2 == 0? true: false;
    }
}
