import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //지도 크기
        int c_len = sc.nextInt();
        int r_len = sc.nextInt();

        int m_cnt = sc.nextInt();
        ArrayList<Integer[]> position = new ArrayList<>();
        for (int cnt = 0; cnt < m_cnt; cnt++) {
            int tmp_r = sc.nextInt();
            int tmp_c = sc.nextInt();
            position.add(new Integer[] { tmp_r, tmp_c });
        }
        int user_dir=sc.nextInt();
        int user_dis=sc.nextInt();

        int shop_dir;
        int shop_dis;
        //1: 북 2 : 남 3: 서 4 : 동
        //시작
        int result=0;
        for (int k = 0; k < m_cnt; k++) {
            shop_dir=position.get(k)[0];
            shop_dis=position.get(k)[1];
            int sum_dir=user_dir+shop_dir;
            int sum_dis=user_dis+shop_dis;
            //둘다 같은 방향
            if(shop_dir==user_dir)
            {
                result+=Math.abs(shop_dis-user_dis);
            }
            //남북
            else if(sum_dir==3)
            {
                
                if(shop_dis+user_dis<c_len)
                    result+=r_len+sum_dis;
                else
                    result+=r_len+c_len+c_len-(sum_dis);
            }
            //동서
            else if(sum_dir==7)
            {
                result+=c_len;
                if(sum_dis<r_len)
                    result+=sum_dis;
                else
                    result+=(2*r_len-(sum_dis));
            }
            // ㄱ ㄴ 모양
            else
            {
                if(sum_dir==4)
                {
                    result+=sum_dis;
                }
                else if(sum_dir==5)
                {
                    if(user_dir==3)
                    {
                        result+=r_len-user_dis+shop_dis;
                    }
                    else if(user_dir==2)
                    {
                        result+=r_len-shop_dis+user_dis;
                    }
                    else if(user_dir==1)
                    {
                        result+=c_len-user_dis+shop_dis;
                    }
                    else if(user_dir==4)
                    {
                        result+=c_len-shop_dis+user_dis;
                    }
                }
                else
                {
                    result+=c_len+r_len-user_dis-shop_dis;
                } 
            }
        }
        System.out.println(result);
    }
}
