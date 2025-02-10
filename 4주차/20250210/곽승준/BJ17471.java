package test;

import java.util.*;
import java.io.*;


public class BJ17471 {
    static int[] p;
    static List<int[]> adj;
    static int N;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        adj = new ArrayList<int[]>();
        
        N = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for(int j=0; j<size; j++) {
                int[] tmp = new int[2];
                int currentN = Integer.parseInt(st.nextToken());
                tmp[0] = i;
                tmp[1] = currentN;
                adj.add(tmp);
            }
        }
        
//        for(int i=0; i<adj.size(); i++)
//            System.out.println(Arrays.toString(adj.get(i)));

        powerset(new boolean[N], 0, 0);
        if(ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
    
    static void powerset(boolean[] sel, int k, int idx) {
        if(k==sel.length) {
            for(int i=1; i<=(N%2==0?N/2:(N+1)/2); i++)
            if(idx==i) {
                
                if(bfs(sel, true) && bfs(sel, false)) {
//                    System.out.println(Arrays.toString(sel));
                    int sum1 = 0, sum2 = 0;
                    for(int j=0; j<N; j++) {
                        if(sel[j])
                            sum1+=p[j];
                        else
                            sum2+=p[j];
                    }
                    int sum = Math.abs(sum1-sum2);
                    if(ans > sum)
                        ans = sum;
                }
            }
            return;
        }
        sel[k] = true;
        powerset(sel, k+1, idx+1);
        sel[k] = false;
        powerset(sel, k+1, idx);
    }
    
    static boolean bfs(boolean[] sel, boolean f) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N];
        
        for(int i=0; i<N; i++) {
            v[i] = sel[i];
        }
        
        for(int i=0; i<sel.length; i++) {
            if(sel[i]==f) {
                q.add(i+1);
                v[i] = !v[i];
                break;
            }
        }
        
        while(!q.isEmpty()) {
            int tmp = q.poll();
            for(int i=0; i<adj.size(); i++) {
                if(adj.get(i)[0] != tmp || v[adj.get(i)[1]-1] == !f) continue;
                
                q.add(adj.get(i)[1]);
                v[adj.get(i)[1]-1] = !v[adj.get(i)[1]-1];
            }
        }
        
        for(int i=0; i<N; i++) {
            if(v[i] == f)
                return false;
        }
        return true;
    }

}
