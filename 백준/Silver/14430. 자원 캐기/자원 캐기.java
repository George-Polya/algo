import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		dp = new int[n+1][m+1];
		for(int y=1; y<=n; y++) {
			st= new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				dp[y][x] = -1;
			}
		}
		
		System.out.println(solve(1,1));
	}
	
	static int dp[][];
	
	static int solve(int y, int x) {
		int value = board[y][x];
		if(y == n && x == m)
			return value;
		
		if(dp[y][x] != -1)
			return dp[y][x];
		
		int ret = 0 ;
		for(int dir = 0; dir < 2; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx))
				continue;
			ret = Math.max(ret,  solve(ny,nx) + value);
		}
		
		return dp[y][x] = ret;
	}
	
	static int dy[] = {1,0};
	static int dx[] = {0,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	static int n,m;
	static StringTokenizer st;
	static int board[][];
}