import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        int n = Integer.parseInt(br.readLine());
        
        // (위치, 인구수)를 담을 리스트
        List<long[]> info = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long X = Long.parseLong(st.nextToken());
            long A = Long.parseLong(st.nextToken());
            info.add(new long[] { X, A });
        }
        
        // 위치를 기준으로 오름차순 정렬
        info.sort(Comparator.comparingLong(a -> a[0]));
        
        // prefix 배열에 인구수 누적합을 저장
        long[] prefix = new long[n];
        prefix[0] = info.get(0)[1];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + info.get(i)[1];
        }
        
        //왼쪽 누적합 >= 오른쪽 누적합이 될 경우 출력
        for (int i = 0; i < n; i++) {
            long left = prefix[i];
            long right = prefix[n - 1] - prefix[i];
            if (left >= right) {
                System.out.println(info.get(i)[0]);
                break;
            }
        }
    }
}
