class Solution {
    static int result=0;
    static int[] duple=new int[1000];
    static int idx=0;
    public int solution(String tmp) {
        char[] num=tmp.toCharArray();

        for(int i=1;i<=num.length;i++) {
            Recursive(0,num,"",i,new boolean[tmp.length()]);
        }
        return result;
    }
    static void Recursive(int cnt, char[] ch, String str, int k,boolean[] v)
    {
        if(str.length()>=k) {
            if (chk(Integer.parseInt(str))) {
                result+=1;
                System.out.println(str);
            }

            //System.out.println(str);
            return;
        }

        for(int i=0;i<ch.length;i++) {
            if(v[i]==false) {
                v[i]=true;
                str+=ch[i];
                Recursive(cnt+1,ch,str,k,v);
                str=str.substring(0,str.length()-1);
                v[i]=false;
            }

        }

//      str+=ch[0];
//      Recursive(cnt+1,ch,str);
//      str=str.substring(0,str.length()-1);
//      
//      str+=ch[1];
//      Recursive(cnt+1,ch,str);
//      str=str.substring(0,str.length()-1);
//      
//      str+=ch[2];
//      Recursive(cnt+1,ch,str);
//      str=str.substring(0,str.length()-1);
    }
    static boolean chk(int n)
    {
        if(n<=1) return false;
        for(int i=0;i<idx;i++) {
            if(duple[i]==n)
                return false;
        }
        for(int i=2;i<=Math.sqrt(n);i++) {
            if(n%i==0)
                return false;
        }
        duple[idx++]=n;
        return true;
    }
}
