import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	n = Integer.parseInt(br.readLine());
    	
    	board = new int[n+1][n+1];
    	
    	dp = new int[3][n+1][n+1];
    	
    	for(int y=1; y<=n;y++) {
    		st = new StringTokenizer(br.readLine());
    		
    		for(int x=1; x<=n; x++) {
    			board[y][x] = Integer.parseInt(st.nextToken());
    			dp[0][y][x] = dp[1][y][x] = dp[2][y][x] = -1;
    		}
    	}
    			
    	System.out.println(solve(1,1,0));
    	
    }
    
    static int solve(int y,int x, int cur) {
    	if(y > n || x>n)
    		return 0;
    	
    	if(dp[cur][y][x] != -1)
    		return dp[cur][y][x];
    	
    	int nxt = (cur + 1 ) % 3;
    	int ret = 0;
    	
    	for(int dir = 0; dir < 2; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		if(board[y][x] == cur)
    			ret = Math.max(ret, solve(ny,nx,nxt) + 1);
    		else
    			ret = Math.max(ret,  solve(ny,nx,cur));
    	}
    	
//    	if(board[y][x] == cur)
//    		ret = Math.max(solve(y+1,x,nxt), solve(y,x+1,nxt)) + 1;
//    	else
//    		ret = Math.max(solve(y+1,x,cur), solve(y,x+1,cur));
    	
    	return dp[cur][y][x] = ret;
    				
    }
    static int dy[] = {1,0};
    static int dx[] = {0,1};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>n || x<=0 || x>n;
    }
  
    static int n;
    static StringTokenizer st;
    static int board[][], dp[][][];
}