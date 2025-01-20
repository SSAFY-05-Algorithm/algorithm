import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int min=Integer.MAX_VALUE;
		int[][] grid = new int[10][10];
		int cnt[]={0,0,0,0,0};

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				grid[x][y] = sc.nextInt();
			}
		}
		
		//복사해놓기
		int[][]grid_copy=new int[grid.length][];
		for (int i = 0; i < grid.length; i++) {
			grid_copy[i] = grid[i].clone();
		}
		
		for (int len = 4; len >= 0; len--) // 5x5부터 작은거 순으로
		{
			for (int x = 0; x < 10; x++)
			{
				for (int y = 0; y < 10; y++) {
					if (grid[x][y] == 1) 
					{
						int flag = 1;
						for (int dx = 0; dx <= len; dx++) 
						{
							for (int dy = 0; dy <= len; dy++) 
							{
								//경계 넘으면
								if(x+dx>=10||y+dy>=10)
								{
									flag = 0;
									break;
								}
								//만약 아닌 경우
								if (grid[x+dx][y+dy] != 1) {
									flag = 0;
									break;
								}

								//탐색중 1이 지속되면
								else
									grid_copy[x+dx][y+dy]=2;//방문 및 확인된 좌표 저장

							}
							if (flag == 0)
							{
								for (int i = 0; i < grid.length; i++) {
									grid_copy[i] = grid[i].clone(); // 각 행을 복사
								}
								break; //아닌경우
							}
						}

						
						if(flag==1)
						{
							cnt[len]++;
							for (int i = 0; i < grid.length; i++) {
								grid[i] = grid_copy[i].clone(); // 각 행을 복사
							}
							if(cnt[len]>5)//만약 종이를 다 썼으면
							{
								System.out.println(-1);
								return;
							}
						}

						
					}
				}
			}
		}

		int sum=0;
		for(int k=0;k<5;k++)
		{
			sum+=cnt[k];
		}
		System.out.println(sum);

	}
}
