import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new long[n+1];
		Arrays.fill(dp, -1);
		
		dp[0] = 0;
		if(n >= 1) {
			dp[1] = 1;
			for(int i =2 ; i<=n; i++) {
				dp[i] = (dp[i-1] + dp[i-2]) % mod;
			}
		}
		System.out.println(dp[n]);
	}
	
	
	
	
	static int n;
	static int mod = 1_000_000_007;
	static long dp[];
}