import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
//    	solve(0, n, 0);
//    	System.out.println(ans);
    	

    	dp = new int[n+1];
    	Arrays.fill(dp,INT_MAX);
    	dp[0] = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		for(int sum = 1; sum <= n; sum++) {
    			if(sum - arr[i] >= 0 && dp[sum - arr[i]] != INT_MAX)
    				dp[sum] = Math.min(dp[sum], dp[sum-arr[i]] + 1);
    		}
    	}
    	System.out.println(dp[n]);
    }
    
    static int INT_MAX = Integer.MAX_VALUE;
    static int dp[];
    static void solve(int cur, int amount, int cnt) {
    	if(amount == 0) {
    		ans = Math.min(ans, cnt);
    		return;
    	}
    	
    	if(cur == 4)
    		return;
    	
    	if(amount >= arr[cur])
    		solve(cur, amount - arr[cur], cnt + 1);
    	solve(cur + 1, amount , cnt);
    }
    static int ans = Integer.MAX_VALUE;
    static int n;
    static int arr[] = {1,2,5,7};
}