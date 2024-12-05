import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	dp = new int[n+1][n+1];
    	for(int row[] : dp)
    		Arrays.fill(row, -1);
    	
    	System.out.println(solve(1, n));
    }
    
    static int dp[][];
    
    static int solve(int l, int r) {
    	if(l>=r)
    		return 0;
    	
    	if(dp[l][r] != -1)
    		return dp[l][r];
    	
    	int ret = 0;
    	if(arr[l] == arr[r])
    		ret = solve(l + 1, r - 1);
    	else
    		ret = Math.min(solve(l+1,r), solve(l,r-1)) + 1;
    	
    	return dp[l][r] = ret;
    }
    
    static int n;
    static int arr[];
    static StringTokenizer st;
}