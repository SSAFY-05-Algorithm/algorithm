import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 26개 알파벳 각각의 가중치 합
        int[] arr = new int[26];
        
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            
            // 뒤에서부터 1, 10, 100 단위로 가중치를 곱해나감
            int placeValue = 1;
            for (int j = str.length() - 1; j >= 0; j--) {
                char c = str.charAt(j);
                arr[c - 'A'] += placeValue;
                placeValue *= 10;
            }
        }
        
        // 오름차순 정렬 후 뒤에서부터 9부터 할당
        Arrays.sort(arr);
        
        int ans = 0;
        int digit = 9;
        for (int i = 25; i >= 0 && arr[i] != 0; i--) {
            ans += arr[i] * digit;
            digit--;
            
            if (digit < 0) break;
        }
        
        System.out.println(ans);
    }
}
