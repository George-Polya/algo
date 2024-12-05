import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	while(true) {
    		n = Integer.parseInt(br.readLine());
    		if(n == 0)
    			break;
    		arr = new int[n + 1];
    		for(int i = 1; i<=n; i++) {
    			arr[i] = Integer.parseInt(br.readLine());
    		}
    		long ans = Long.MIN_VALUE;
    		dp = new long[n+1];
    		for(int i = 1; i<=n; i++)
    			ans = Math.max(ans, solve(i));
    		sb.append(ans).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static long dp[];
    
    static long solve(int cur) {
    	if(cur == n)
    		return arr[n];
    	if(dp[cur] != 0)
    		return dp[cur];
    	
    	return dp[cur] = Math.max(arr[cur], arr[cur] + solve(cur + 1));
    	
    }    
    static int n;
    static int arr[];
    
}