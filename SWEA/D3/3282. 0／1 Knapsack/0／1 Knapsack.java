import java.io.*;
import java.util.*;
public class Solution {
	static int T;
	static int n,k;
	static int MAX_N = 100;
	static int MAX_K = 1000;
	static int dp[];
	static int INT_MIN = Integer.MIN_VALUE;
	static StringTokenizer st;
	static int volumes[];
	static int values[];
	static void init() {
		for(int i = 0; i<=n; i++)
			dp[i] = INT_MIN;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			volumes = new int[n+1];
			values = new int[n+1];
			dp = new int[k+1];
			
			for(int i = 0; i< n; i++) {
				st = new StringTokenizer(br.readLine());
				volumes[i] = Integer.parseInt(st.nextToken());
				values[i] = Integer.parseInt(st.nextToken());
			}
			
			init();
			dp[0] = 0;
			
			for(int i = 0; i < n ;i++) {
				for(int j = k; j>=volumes[i]; j--) {
					
					if(dp[j - volumes[i]] == INT_MIN)
						continue;
					dp[j] = Math.max(dp[j], dp[j-volumes[i]] + values[i]);
				
				}
			}
			
//			System.out.println(Arrays.toString(dp));
			int ans = INT_MIN;
			for(int i = 0; i<= k;i++)
				ans = Math.max(ans, dp[i]);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');			
			
			
		}
		System.out.println(sb);
	}
	
	
}