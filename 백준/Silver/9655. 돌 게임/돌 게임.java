import java.util.*;
import java.io.*;



public class Main {
	static int n;
	static int dp[];
	static int INT_MAX = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		Arrays.fill(dp, INT_MAX);
		dp[0] = 0;

		
		for(int i = 1; i<=n; i++) {
			for(int k : new int[] {1,3}) {
				if(i - k >= 0 && dp[i-k] != INT_MAX) {
					dp[i] = Math.min(dp[i], dp[i-k]) + 1;
				}
			}
		}
		
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[n] % 2 == 0 ? "CY" : "SK");
		
	}
}