import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	board = new int[n][n];
    	dp = new int[n][n];
    	for(int y=0; y<n;y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x=0; x<n; x++) {
    			board[y][x] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int ans = 0;
    	for(int y=0; y<n; y++) {
    		for(int x=0; x<n; x++)
    			ans = Math.max(ans, solve(y,x));
    	}
    	System.out.println(ans);
    }
    static int solve(int y,int x) {
    	if(dp[y][x] != 0)
    		return dp[y][x];
    	
    	int ret = 1;
    	
    	for(int dir = 0; dir < 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		if(OOB(ny,nx) || board[ny][nx] <= board[y][x])
    			continue;
    		ret = Math.max(ret, solve(ny,nx) + 1);
    	}
    	return dp[y][x] = ret;
    }
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<0 || y>=n || x<0 || x>=n;
    }
    static StringTokenizer st;
    static int n;
    static int board[][],dp[][];
    
}