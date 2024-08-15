import java.util.*;
import java.io.*;

public class Main {
	static int T,n,m;
	static StringTokenizer st;
	static long dp[][];
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    		dp = new long[n+1][m+1];
    		
    		
    		for(int i = 1; i<=m; i++) {
    			Arrays.fill(dp[1], 1);
    		}
    		
    		for(int i = 2; i<=n; i++) {
    			for(int j = 1; j<=m; j++) {
    				for(int k = 1; k<=j/2;k++) {
    					dp[i][j] += dp[i-1][k];
    				}
    			}
    		}
    		
//    		System.out.println(dp[n][m]);
    		
    		long ans = 0;
    		for(int i = 1; i<=m;i++)
    			ans += dp[n][i];
    		System.out.println(ans);
    	}
    }
}