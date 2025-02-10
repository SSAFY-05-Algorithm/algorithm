import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        System.out.print(backTracking(0, 0, arr));
    }

    static int backTracking(int idx, int sum, char[] arr){
        if(idx>=arr.length){
            return sum;
        }

        //괄호 x
        int max = Integer.MIN_VALUE;
        int num = arr[idx]-'0';
        if(idx==0){
            max = Math.max(max, backTracking(idx+2, num, arr));
        } else {
            max = Math.max(max, backTracking(idx+2, calSingle(sum, arr[idx-1], num), arr));
        }

        //괄호 o
        if(idx+2 < arr.length){
            int expSum = calSingle(arr[idx]-'0', arr[idx+1], arr[idx+2]-'0');

            if(idx == 0){
                max = Math.max(max, backTracking(idx+4, expSum, arr));
            } else {
                max = Math.max(max, backTracking(idx+4, calSingle(sum, arr[idx-1], expSum), arr));
            }
        }

        return max;
    }

    static int calSingle(int num1, char operator, int num2){
        if(operator == '+'){
            return num1+num2;
        }

        if(operator == '-'){
            return num1-num2;
        }

        if(operator == '*'){
            return num1*num2;
        }

        return 0;
    }
}
