import java.util.*;
import java.io.*;



public class Main {
	static int n;
	static int dp[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		int INT_MAX = Integer.MAX_VALUE;
		Arrays.fill(dp, INT_MAX);
		dp[0] = 0;
		
		for(int i = 2; i<=n; i++) {
			for(int k : new int[] {2,5}) {
				if(i - k < 0 || dp[i-k] == INT_MAX)
					continue;
				
				dp[i] = Math.min(dp[i], dp[i-k]) + 1;
			}
		}
		
		System.out.println(dp[n] == INT_MAX ? -1 : dp[n]);
	}
}