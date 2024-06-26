import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	static int weights[];
	static int values[];
	static StringTokenizer st;
	static int dp[];
	static int INT_MIN = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		weights = new int[n+1];
		values = new int[n+1];
		for(int i = 1; i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[k+1];
		Arrays.fill(dp, INT_MIN);
		dp[0] = 0;
		for(int i = 1; i<=n;i++) {
			for(int j = k; j>=0;j--) {
				if(j - weights[i] >= 0) {
					if(dp[j-weights[i]] == INT_MIN)
						continue;
					dp[j] = Math.max(dp[j], dp[j-weights[i]] + values[i]); 
				}
			}
		}
		
		int ans = -1;
		for(int i = 0; i<=k; i++)
			ans = Math.max(ans,  dp[i]);
		
		System.out.println(ans);
		
	}
}