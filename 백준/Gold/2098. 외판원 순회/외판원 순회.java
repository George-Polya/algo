import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
      
        board = new int[n][n];
        dp = new int[1<<n][n];
        
        for(int y=0; y<n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x=0; x<n; x++) {
        		board[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        
        for(int i = 0; i < 1<<n;i++) {
        	Arrays.fill(dp[i], INF);
        }
        
        dp[1<<0][0] = 0;
        
        for(int bit = 0; bit < (1<<n); bit++) {
        	for(int i = 0; i <n;i++) {
        		if(dp[bit][i] == INF)
        			continue;
        		
        		for(int j = 0; j < n; j++) {
        			if((bit & (1<<j))!=0 || board[i][j] == 0)
        				continue;
        			
        			dp[bit | (1<<j)][j] = Math.min(dp[bit | (1<<j)][j], dp[bit][i] + board[i][j]);
        		}
        	}
        }
        
        int ans = INF;
        for(int i = 1; i<n;i++) {
        	if(board[i][0] == 0)
        		continue;
        	ans = Math.min(ans, dp[(1<<n) - 1][i] + board[i][0]);
        }
        System.out.println(ans);
        
    }
    static int INF = Integer.MAX_VALUE / 2;
    static int n;
    static StringTokenizer st;
    static int board[][];
    static int dp[][];
}