import java.util.*;
import java.io.*;
public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n+m+1][n+m+1];
        
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        
        for(int i = 2; i<=n+m;i++) {
        	dp[i][0] = 1;
        	for(int j = 1; j<=i; j++) {
        		dp[i][j] = Math.min(dp[i-1][j-1] + dp[i-1][j], INT_MAX);
        	}
        }
        
        
        if(dp[n+m][n] < k) {
        	System.out.println(-1);
        	System.exit(0);
        }
        
        StringBuilder sb = new StringBuilder();
        while(!( n == 0 && m == 0)) {
        	if(dp[n+m-1][m] >= k) { 
        		sb.append('a');
        		n--;
        	}else {
        		sb.append('z');
        		k -= dp[n+m-1][m];
        		m--;
        	} 
        }
        System.out.print(sb);
        
   }
   static int INT_MAX = (int)1e9+1;
   static StringTokenizer st;
   static int n,m,k;
   static int dp[][];
}