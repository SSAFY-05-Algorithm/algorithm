import java.util.*;
import java.io.*;

class Alphabet implements Comparable<Alphabet>{
    int c;
    int cost;

    public Alphabet(int c, int cost){
        this.c=c;
        this.cost=cost;
    }

    public int compareTo(Alphabet alphabet){
        return Integer.compare(alphabet.cost, this.cost);
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] nums = new String[n];
        for(int i=0;i<n;i++){
            nums[i] = br.readLine();
        }

        //알파벳 배열 값 설정
        int size = 'Z'-'A'+1;
        Alphabet[] countedAlphabets = new Alphabet[size];
        for(int i=0;i<size;i++){
            countedAlphabets[i] = new Alphabet(i, 0);
        }

        //알파벳 값 설정
        for(String num:nums){
            char[] alphabets = num.toCharArray();

            int cost = 1;
            for(int i=alphabets.length-1;i>=0;i--){
                char alphabet = alphabets[i];
                int idx = alphabet-'A';
                countedAlphabets[idx].cost+=cost;

                cost*=10;
            }
        }

        Arrays.sort(countedAlphabets);

        int[] alphabetCost = new int[size];
        for(int i=9;i>=0;i--){
            Alphabet alphabet = countedAlphabets[9-i];

            alphabetCost[alphabet.c] = i;
        }

        int res = 0;
        for(String num:nums){
            char[] alphabets = num.toCharArray();

            int sum = 0;
            for(int i=0; i<alphabets.length; i++){
                sum*=10;

                char alphabet = alphabets[i];
                sum += alphabetCost[alphabet-'A'];
            }

            res+=sum;
        }

        System.out.print(res);
    }
}
