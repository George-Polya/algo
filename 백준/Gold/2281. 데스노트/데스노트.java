import java.io.*;
import java.util.*;
public class Main {
    
    static int n, m;
    static int[] arr;
    static int[][] dp;
    static StringTokenizer st;
    
    static int solve(int idx, int len) {
    	if(idx == n)
    		return 0;
    	
    	if(dp[idx][len] != -1)
    		return dp[idx][len];
    	
    	int left = m - len + 1;
    	int ans = solve(idx + 1, arr[idx] + 1) + left * left;
    	
    	if(len + arr[idx] <= m) {
    		ans = Math.min(ans,  solve(idx + 1, len + arr[idx] + 1));
    	}
    	
    	dp[idx][len] = ans;
    	return ans;
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
    		Arrays.fill(dp[i], -1);
    	}
    	
    	System.out.println(solve(1, arr[0] + 1));
    	
    	
    }
}