import java.util.*;

class Moving{
    int dis;
    char dir;
    Moving(int dis,char dir){
        this.dis=dis;
        this.dir=dir;
    }
}

class RC{
    int r;
    int c;
    RC(int r,int c){
        this.r=r;
        this.c=c;
    }
}

public class Main {
    static int N;
    static int[][]board;
    static int array_dir[][]={{0,1},{1,0},{0,-1},{-1,0}}; //방향
    static Deque<RC> body=new ArrayDeque<>();
    static ArrayList<Moving> moved=new ArrayList<>();
    //현재 꼬리가 있는 부분은 2
    //머리 꼬리 나눠서 생각하기

    static int game(int startr,int startc)
    {
        int k=0; //방향
        int t=0; //거리/방향
        body.addFirst(new RC(startr,startc));//머리이자 꼬리 나는 지렁이요
        board[startr][startc]=1; //시작지점 있다고 알려줌
        int distance=0; //이동거리
        int nr=startr;
        int nc=startc;
        while(true)
        {
            nr=body.getFirst().r+array_dir[k][0];//방금 이동한 머리 주소 r
            nc=body.getFirst().c+array_dir[k][1];//방금 이동한 머리 주소 c
            body.addFirst(new RC(nr, nc)); //머리 이동
            if(nr<0||nr>=N||nc<0||nc>=N)
                break;

            
            
            //0을 밟으면
            if(board[nr][nc]==0)
            {
                board[body.getLast().r][body.getLast().c]=0;
                body.removeLast();
            }
            //1 몸닫으면
            else if(board[nr][nc]==1)
            {
                return distance+1;
            }
            //사과 있으면
            //머리 있는곳 1로 바꿈
            board[nr][nc]=1;
            distance++;
            if(t>=moved.size()) continue;
            if(distance==moved.get(t).dis)
            {
                
                if(moved.get(t).dir=='D')
                {
                    k=(k+1)%4;
                }
                else
                {
                    k=(k+4-1)%4;
                }
                t++;
            }
        }
        return distance+1;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        board=new int[N][N];
        int K=sc.nextInt();
        //0 : 사과없음 
        //1 : 내몸 
        //2 : 사과 있음

        for(int k=0;k<K;k++)
        {
            int tmpr=sc.nextInt();
            int tmpc=sc.nextInt();
            board[tmpr-1][tmpc-1]=2;
        }
        int tmp=sc.nextInt();
        
        for(int i=0;i<tmp;i++)
        {
            int tmpc=sc.nextInt();
            char tmpd=sc.next().charAt(0);
            moved.add(new Moving(tmpc,tmpd));
        }

        System.out.println(game(0,0));
    }
}
