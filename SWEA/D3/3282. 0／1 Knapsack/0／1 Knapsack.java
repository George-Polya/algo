import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static StringTokenizer st;
	
	static int n,k;
	static int dp[][];
	static int value[], weight[];
	static int INT_MIN = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			dp = new int[n+1][k+1];
			
			value = new int[n+1];
			weight = new int[n+1];
			
			for(int i = 1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int i = 1; i<=n;i++) {
				for(int j = 1; j<=k; j++) {
					dp[i][j] = dp[i-1][j];
					
					if(j - weight[i] >= 0)
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weight[i]] + value[i]);
				}
			}
			
			
			int ans = INT_MIN;
			for(int i = 1; i<=k;i++)
				ans = Math.max(ans, dp[n][i]);
			
			sb.append("#").append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}