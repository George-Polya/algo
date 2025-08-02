import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		dp = new boolean[N][M];
		
		for(int  y=0;  y<N; y++) {
			String line = br.readLine();
			for(int x= 0; x<M; x++) {
				board[y][x] = line.charAt(x);
			}
		}
		
		int ans = 0;
		visited = new boolean[N][M];
		for(int y= 0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(solve(y,x))
					ans++;
			}
			
		}
		System.out.println(ans);
		
		
	}
	
	static boolean solve(int y,int x) {
		if(OOB(y,x))
			return true;
		if(visited[y][x])
			return dp[y][x];
		if(dp[y][x])
			return dp[y][x];
		
		char ch = board[y][x];
		Pair nxt = getNxtPos(y,x,ch);
		visited[y][x] = true;
		return dp[y][x] = solve(nxt.y, nxt.x);
	}
	static boolean visited[][];
	
	static Pair getNxtPos(int y,int x, char ch) {
		if(ch == 'U') {
			return new Pair(y + dy[0] ,x + dx[0]);
		}else if(ch == 'R') {
			return new Pair(y + dy[1], x + dx[1]);
		}else if(ch == 'D') {
			return new Pair(y + dy[2], x + dx[2]);
		}
		return new Pair(y + dy[3], x + dx[3]);
	}
	static boolean OOB(int y,int x) {
		return y< 0 || y>=N || x<0 || x>=M;
	}
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	
	static StringTokenizer st;
	static int N,M;
	static char board[][];
	static boolean dp[][];
}

