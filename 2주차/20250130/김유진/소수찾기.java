import java.util.*;

class Solution {
    public int solution(String numbers) {
        int max = 9999999;
        boolean[] isPN = new boolean[max+1];
        
        Arrays.fill(isPN, true);
        isPN[0] = false;
        isPN[1] = false;
        
        for(int i=2; i*i<= max ;i++){
            if(!isPN[i]){
                continue;
            }
            
            for(int j = i*i; j<=max; j+=i){
                isPN[j] = false;
            }
        }
        
        Set<Integer> candidates = new HashSet<>();
        backTracking(0, numbers, new Stack<>(), candidates);
        
        int cnt = 0;
        for(int candidate:candidates){
            if(isPN[candidate]){
                cnt++;
            }
        }
        
        return cnt;
    }
    
    void backTracking(int idx, String numbers, Stack<Integer> res, Set<Integer> set){
        if(idx == numbers.length()){
            backTracking2(res, new boolean[res.size()], new Stack<>(), set);
            
            return;
        }
        
        //미포함
        backTracking(idx+1, numbers, res, set);
        
        //포함
        res.push(numbers.charAt(idx)-'0');
        backTracking(idx+1, numbers, res, set);
        res.pop();
    }
    
    void backTracking2(Stack<Integer> combination, boolean[] visited, Stack<Integer> stack, Set<Integer> res){
        if(combination.size() == stack.size()){
            int num = calculateNum(stack);
            res.add(num);
            
            return;
        }
        
        for(int i=0;i<combination.size();i++){
            if(visited[i]){
                continue;
            }
            
            stack.push(combination.get(i));
            visited[i] = true;
            backTracking2(combination, visited, stack, res);
            visited[i] = false;
            stack.pop();
        }
        
    }
    
    int calculateNum(Stack<Integer> stack){
        int res = 0;
        for(int digit:stack){
            res *= 10;
            res += digit;
        }
        
        return res;
    }
}
