import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        board = new int[m+1][n+1];
        dp = new int[m+1][n+1];
        visited = new boolean[m+1][n+1];
        for(int y=1; y<=m; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x = 1; x<=n; x++) {
        		dp[y][x] = -1;
        		board[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited[m][n] = true;
        System.out.println(solve(m,n));
    }
    
    static int solve(int y,int x) {
    	if(dp[y][x] != -1)
    		return dp[y][x];
    	if(y == 1 && x == 1) {
    		return dp[y][x] = 1;
    	}
    	
    	int cnt = 0;
    	for(int dir = 0; dir < 4 ; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		if(OOB(ny,nx) || visited[ny][nx] || board[ny][nx] <= board[y][x])
    			continue;
    		visited[ny][nx] = true;
    		cnt += solve(ny,nx);
    		visited[ny][nx] = false;
    	}
    	
    	return dp[y][x] = cnt;
    }
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>m || x<=0 || x>n;
    }
    
    static int m,n;
    static int board[][];
    static int dp[][];
    static StringTokenizer st;
    static boolean visited[][];
}