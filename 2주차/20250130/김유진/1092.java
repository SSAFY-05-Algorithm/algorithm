import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args ) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] machines = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            machines[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] boxes = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(machines);
        Arrays.sort(boxes);

        if(machines[N-1] < boxes[M-1]){
            System.out.print(-1);

            return;
        }

        int cnt = 0;
        int sec = 1;
        boolean[] done = new boolean[M];
        while(true){
            boolean[] visited = new boolean[N];

            for(int j = M-1; j>=0; j--){
                if(done[j]){
                    continue;
                }

                for(int i = N-1; i>=0; i--){
                    if(machines[i] < boxes[j]){
                        break;
                    }

                    if(visited[i]){
                        continue;
                    }

                    visited[i] = true;
                    done[j] = true;
                    cnt++;
                    break;
                }
            }

            if(cnt == M){
                break;
            }

            sec++;
        }

        System.out.print(sec);
    }
}
