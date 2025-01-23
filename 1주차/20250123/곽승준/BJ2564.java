package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2564 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int shop[] = new int[N+1];
        for(int i=0; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int way = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            
            switch(way) {
            //북쪽
            case 1:
                shop[i] = distance;
                break;
            //남쪽
            case 2:
                shop[i] = width*2 + height - distance;
                break;
            //서쪽
            case 3:
                shop[i] = width*2 + height*2 - distance;
                break;
            //동쪽
            case 4:
                shop[i] = width + distance;
                break;
            }
        }
        
        int sum = 0;
        for(int i=0; i<N; i++) {
            int root1 = Math.abs(shop[i] - shop[shop.length-1]);
            int root2 = width*2 + height*2 - Math.abs(shop[i] - shop[shop.length-1]);
            
            sum += root1 > root2 ? root2 : root1;
        }
        System.out.println(sum);
    }

}
