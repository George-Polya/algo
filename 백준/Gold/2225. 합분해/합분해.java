import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	static int MOD = 1000000000;
	static StringTokenizer st;
	static int dp[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[k+1][n+1];
		
		Arrays.fill(dp[1], 1);
		for(int i = 1; i<=k;i++) {
			dp[i][0] = 1;
		}
		

		
		
		for(int i = 1; i<=k; i++) {
			for(int j = 1; j<=n; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
			}
		}

		
		System.out.println(dp[k][n] % MOD);
	}
}