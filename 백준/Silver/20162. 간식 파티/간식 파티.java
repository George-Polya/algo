import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		dp = new int[n+1];
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = -1;
		}
		
		
		
		int ans = 0;
		
		for(int i = 1; i<=n; i++)
			ans = Math.max(ans, solve(i));
		
		System.out.println(ans);
	}
	
	static int dp[];
	
	
	static int solve(int idx) {
		if(dp[idx] != -1)
			return dp[idx];
		
		dp[idx] = arr[idx];
		
		for(int i = 1; i< idx; i++) {
			if(arr[i] < arr[idx])
				dp[idx] = Math.max(dp[idx], solve(i) + arr[idx]);
		}
		
		return dp[idx];
	}
	
	static void solve(int idx, int top, int sum){
		if(idx == n) {
			ans = Math.max(ans, sum);
			return;
		}
			
		
		for(int i = idx + 1; i <=n; i++) {
			if(arr[i] > top) {
				solve(i, arr[i], sum + arr[i]);
			}
		}
		
		solve(idx + 1, top, sum);
	}
	static int ans;
	static int n;
	static int arr[];
}