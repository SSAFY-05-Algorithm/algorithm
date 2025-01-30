import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static Set<Integer> set;
    // 소수 확인
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false; 
        }
        int limit = (int) Math.sqrt(number);
        for (int i = 2; i <= limit; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(String numbers) {
        // 문자열을 char 배열로 변환
        char[] chars = numbers.toCharArray();
        
        //중복 제거를 위해 사용
        set = new HashSet<>();
        
        // 재귀 함수를 이용해 순열 생성 (1부터 n 길이까지)
        permute(new StringBuilder(), new boolean[chars.length], chars);
        
        // 소수 개수 세기
        int count = 0;
        for (int num : set) {
            if (isPrime(num)) {
                count++;
            }
        }
        
        return count;
    }
    
    // 순열을 생성하는 재귀 함수
    private void permute(StringBuilder sb, boolean[] v, char[] chars) {
        // 현재까지 만든 숫자가 있으면 Set에 추가
        if (sb.length() > 0) {
            set.add(Integer.valueOf(sb.toString()));
        }
        
        // 아직 사용하지 않은 숫자를 붙여가며 모든 순열 탐색
        for (int i = 0; i < digits.length; i++) {
            if (!v[i]) {
                v[i] = true;
                sb.append(chars[i]);
                permute(sb, v, chars);
                sb.deleteCharAt(sb.length() - 1);
                v[i] = false;
            }
        }
    }
    

}
