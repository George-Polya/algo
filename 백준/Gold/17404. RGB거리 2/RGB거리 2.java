import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][3];
        for(int y=1; y<=n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x=0; x<3; x++) {
        		board[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        dp = new int[n + 1][3];
        
        for(int k = 0; k < 3; k++) {
        	for(int i = 0; i < 3; i++) {
        		dp[1][i] = i == k ? board[1][i] : INF;
        	}
        	
        	
        	for(int i = 2; i<=n; i++) {
        		dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + board[i][0];
        		dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + board[i][1];
        		dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + board[i][2];
        	}
        	
        	for(int i = 0; i < 3; i++) {
        		if(i != k)
        			ans = Math.min(ans,  dp[n][i]);
        	}
        }
        
        System.out.println(ans);
    }
    
    
    static int dp[][];
    
    static int INF = Integer.MAX_VALUE / 2;
    static int ans = INF;
    static int n;
    static int board[][];
    static StringTokenizer st;
}