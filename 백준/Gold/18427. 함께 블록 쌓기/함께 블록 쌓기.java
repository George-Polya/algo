import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][m];
		for(int i = 1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int size = st.countTokens();
			for(int j = 0; j < size;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(arr[i]));
		}
		
		dp = new int[n+1][h+1];
		for(int row[] : dp) {
			Arrays.fill(row, -1);
		}
		
		ans = solve(1,0);
		System.out.println(ans);
	}
	
	static int dp[][];
	
	static int solve(int cur, int sum) {
		if(sum == h)
			return 1;
		if(cur > n || sum > h)
			return 0;
        
		if(dp[cur][sum] != -1)
			return dp[cur][sum];
		
		int ret = 0;
		for(int i=0; i< m; i++) {
			if(arr[cur][i] != 0)
				ret = (ret + solve(cur + 1, sum + arr[cur][i])) % mod;;
				
		}
		ret = (ret +  solve(cur + 1, sum)) % mod;
		return dp[cur][sum] = ret;
	}
	
//	static void solve(int cur, int sum) {
//		if(sum == h) {
//			ans++;
//			return;
//		}
//		
//		
//		if(cur == n + 1)
//			return;
//		
//		if(sum > h)
//			return;
//		
//		for(int i = 0; i < m ;i++) {
//			if(arr[cur][i] != 0) {
//				solve(cur + 1, sum + arr[cur][i]);
//			}
//		}
//		solve(cur + 1, sum);
//	}
	
	static int mod = 10007;
	static int ans;
	static int n,m,h;
	static StringTokenizer st;
	static int arr[][];
}