import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int n;
	static int board[][];
	static int cnt;
	
	static int dy[] = {1,0};
	static int dx[] = {0,1};
	static boolean OOB(int y,int x) {
		return y<= 0|| y>n || x<=0 || x>n;
	}
	
	static long dp[][];
	static long solve(int y,int x) {
		if(y == n && x == n) {
			return dp[y][x] = 1;
		}
		int value = board[y][x];
		if(value == 0)
			return dp[y][x];
		
		if(dp[y][x] != 0) {
			return dp[y][x];
		}
		
		
		
		for(int dir = 0; dir < 2; dir++) {
			int ny = y + dy[dir] * value;
			int nx = x + dx[dir] * value;
			
			if(OOB(ny,nx))
				continue;
			
			dp[y][x] += solve(ny,nx);
		}
		return dp[y][x];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1];
		
		dp = new long[n+1][n+1];
		for(int y=1;y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		System.out.println(solve(1,1));
		
	}
}