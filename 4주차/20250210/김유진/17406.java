import java.io.*;
import java.util.StringTokenizer;

class Size{
    int highR;
    int lowR;
    int lowC;
    int highC;

    public Size(int highR, int lowR, int lowC, int highC){
        this.highR=highR;
        this.lowR = lowR;
        this.lowC = lowC;
        this.highC = highC;
    }

    public boolean isPossible(){
        return !((highR == lowR) || (lowC==highC));
    }

    public Size getNextSize(){
        return new Size(this.highR+1, this.lowR-1, this.lowC+1, this.highC-1);
    }
}

class Move{
    int r;
    int c;
    int s;

    public Move(int r, int c, int s){
        this.r=r;
        this.c=c;
        this.s=s;
    }

    public Size getSize(){
        return new Size(r-s, r+s, c-s, c+s);
    }
}

class Main{
    static int N;
    static int M;
    static int K;
    static int[][] fields;
    static Move[] moves;

    static int[] selectedMoves;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fields = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moves = new Move[K];
        selectedMoves = new int[K];
        visited = new boolean[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            moves[i] = new Move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.print(backTracking(0));
    }

    static int backTracking(int idx){
        if(idx == K){
            return rotate();
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<K;i++){
            if(visited[i]){
                continue;
            }

            selectedMoves[idx] = i;
            visited[i] = true;

            min = Math.min(min, backTracking(idx+1));

            visited[i] = false;
        }

        return min;
    }

    static int rotate(){
        int[][] tempFields = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                tempFields[i][j] = fields[i][j];
            }
        }

        for(int moveIdx:selectedMoves){
            Move move = moves[moveIdx];
            Size size = move.getSize();

            while(true){
                if(!size.isPossible()){
                    break;
                }

                int field = tempFields[size.highR][size.lowC];

                //왼
                for(int i= size.highR ; i<size.lowR; i++){
                    tempFields[i][size.lowC] = tempFields[i+1][size.lowC];
                }

                //하
                for(int i = size.lowC;i<size.highC;i++){
                    tempFields[size.lowR][i] = tempFields[size.lowR][i+1];
                }

                //우
                for(int i = size.lowR; i>size.highR; i--){
                    tempFields[i][size.highC] = tempFields[i-1][size.highC];
                }

                //상
                for(int i = size.highC ;i > size.lowC; i--){
                    tempFields[size.highR][i] = tempFields[size.highR][i-1];
                }

                tempFields[size.highR][size.lowC+1] = field;

                size = size.getNextSize();
            }
        }

        return calculateMin(tempFields);
    }

    static int calculateMin(int[][] fields){
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            int sum = 0;
            for(int j=1;j<=M;j++){
                sum += fields[i][j];
            }

            min = Math.min(min, sum);
        }

        return min;
    }
}
