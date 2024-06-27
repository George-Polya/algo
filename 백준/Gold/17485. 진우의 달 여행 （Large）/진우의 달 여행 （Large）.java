import java.util.*;
import java.io.*;
public class Main {
	static int board[][], dp[][][];
	static int n,m;
	static StringTokenizer st;
	static int INT_MAX = Integer.MAX_VALUE/2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n+1][m+1];
		dp = new int[n+1][m+1][3];
		
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				for(int dir = 0; dir < 3; dir++) {
					dp[y][x][dir] = INT_MAX;
				}
			}
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				
//				for(int dir = 0 ; dir<3; dir++) {
//					dp[y][x][0] = Math.min(dp[y][x][0], dp[y-1][x+1][dir] + board[y][x]);
//					dp[y][x][1] = Math.min(dp[y][x][1], dp[y-1][x][dir] + board[y][x]);
//					dp[y][x][2] = Math.min(dp[y][x][2], dp[y-1][x-1][dir] + board[y][x]);
//				}
				
				for(int dir = 0; dir < 3; dir++) {
					if(x !=m && dir != 0) {
						dp[y][x][0] = Math.min(dp[y][x][0], dp[y-1][x+1][dir] + board[y][x]);
					}
					
					if(dir != 1)
						dp[y][x][1] = Math.min(dp[y][x][1], dp[y-1][x][dir] + board[y][x]);
					
					if(x != 1 && dir != 2) {
						dp[y][x][2] = Math.min(dp[y][x][2], dp[y-1][x-1][dir] + board[y][x]);
					}
				}
				
//				System.out.printf("(y,x): (%d, %d), dp: %s\n", y,x,Arrays.toString(dp[y][x]));
//				System.out.println("----");
				
			}
		}
		
		int ans = INT_MAX * 2;
		for(int x = 1; x<=m; x++) {
			for(int dir = 0; dir< 3; dir++) {
				ans = Math.min(dp[n][x][dir], ans);
			}
		}
		System.out.println(ans);
	}
}