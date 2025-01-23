package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3190 {
    static Queue<Integer> dirCount;
    static Queue<Character> dirWay = new LinkedList<>();
    static ArrayList<Integer>[] snake = new ArrayList[2];
    static int[][] board;
    static int snakeWay;
    static int count;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    
    static int isEmpty() {
        // 벽에 부딪치거나, 자기 자신과 부딪치면 -1 리턴
        // 사과랑 부딪치면 1 리턴
        // 비어있으면 0 리턴
        if(snake[1].get(0)+dy[snakeWay] == N || snake[1].get(0)+dy[snakeWay] == -1 || snake[0].get(0)+dx[snakeWay] == N || snake[0].get(0)+dx[snakeWay] == -1 || board[snake[1].get(0)+dy[snakeWay]][snake[0].get(0)+dx[snakeWay]] == 1) {
            return -1;
        } else if(board[snake[1].get(0)+dy[snakeWay]][snake[0].get(0)+dx[snakeWay]] == 2) {
            return 1;
        } else {
            return 0;
        }
    }
    
    static void changeDir() {
        if(dirWay.isEmpty())
            return;
        char way = dirWay.poll();
        //완쪽이면 방향을 -1, 오른쪽이면 +1
        if(way=='L') {
            if(snakeWay == 0)
                snakeWay = 3;
            else
                snakeWay -= 1;
        } else {
            if(snakeWay == 3)
                snakeWay = 0;
            else
                snakeWay += 1;
        }
    }
    
    static boolean move() {
        int moved = dirCount.poll();
        int move;
        if(!dirCount.isEmpty()) {
            move = dirCount.peek();
        } else {
            move = moved + N;
        }
        int cnt = move - moved;
        
        for(int i=0; i<cnt; i++) {
//            System.out.println("[ " + snake[0].get(0) +" "+ snake[1].get(0) + " ]");
//            for(int j =0; j<N; j++) {
//                System.out.print("[ ");
//                for(int k=0; k<N; k++) {
//                    System.out.print(board[j][k]+" ");
//                }
//                System.out.println(" ]");
//            }
//            System.out.println();
            
            // 0 이면 빈칸, 1이면 사과, -1이면 충돌
            if(isEmpty() == 0) {
                int tmpX = snake[0].get(snake[0].size()-1);
                int tmpY = snake[1].get(snake[1].size()-1);
                board[tmpY][tmpX] = 0;
                //머리 추가
                snake[0].add(0, snake[0].get(0)+dx[snakeWay]);
                snake[1].add(0, snake[1].get(0)+dy[snakeWay]);
                board[snake[1].get(0)][snake[0].get(0)] = 1;
                
                //꼬리 제거
                snake[0].remove(snake[0].size()-1);
                snake[1].remove(snake[1].size()-1);
            } else if(isEmpty() == 1) {
                //머리 추가
                snake[0].add(0, snake[0].get(0)+dx[snakeWay]);
                snake[1].add(0, snake[1].get(0)+dy[snakeWay]);
                board[snake[1].get(0)][snake[0].get(0)] = 1;
            } else {
                return false;
            }
            count++;
        }
        changeDir();
        return true;
    }
    
    static void game() {
        //게임 시작
        while(move());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //N: 게임판 크기
        N = Integer.parseInt(st.nextToken());
        //게임 판 생성
        board = new int[N][N];
        st = new StringTokenizer(br.readLine());
        //K : 사과의 개수
        int K = Integer.parseInt(st.nextToken());
        
        //뱀 추가
        snake[0] = new ArrayList<>();
        snake[1] = new ArrayList<>();
        snake[0].add(0);
        snake[1].add(0);
        //오른쪽 : 0 | 아래 : 1 | 왼쪽 : 2 | 위 : 3
        snakeWay = 0;
        
        //뱀 위치 초기화
        board[0][0] = 1;
        
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            //사과 생성
            board[y][x] = 2;
        }
        
        st = new StringTokenizer(br.readLine());
        //L : 방향 바꾸는 횟수
        int L = Integer.parseInt(st.nextToken());
        
        dirCount = new LinkedList<>();
        dirCount.add(0);
        
        //방향 전환과 시간초 저장
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            dirCount.add(Integer.parseInt(st.nextToken()));
            dirWay.add(st.nextToken().charAt(0));
        }
        
        count = 1;
        game();
        System.out.println(count);
    }

}

