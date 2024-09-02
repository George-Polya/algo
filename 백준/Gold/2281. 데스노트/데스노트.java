import java.io.*;
import java.util.*;
public class Main {
    
    static int n, m;
    static int[] arr;
    static int[][] dp;
    static StringTokenizer st;
    static int INT_MAX = Integer.MAX_VALUE;
    
    static int solve(int depth, int len) {
//    	System.out.printf("%d, %d\n", depth,len);
    	if(depth == n)
    		return 0;
    	
    	if(dp[depth][len] != INT_MAX)
    		return dp[depth][len];
    	
    	
    	
    	if(len + arr[depth] <= m) {
    		dp[depth][len] = Math.min(dp[depth][len],  solve(depth + 1, len + arr[depth] + 1));
    	}
    	
    	
    	int left = m - len + 1;
    	dp[depth][len] = Math.min(dp[depth][len], solve(depth + 1, arr[depth] + 1) + left * left);
    	
    	
    	
    	return dp[depth][len];
    }
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n];
    	dp = new int[n+1][m+2];
    	
    	for(int i = 0; i<n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	for(int i = 0; i <=n; i++) {
    		Arrays.fill(dp[i], INT_MAX);
    	}
    	
    	System.out.println(solve(1, arr[0] + 1));
    	
    	
    }
}