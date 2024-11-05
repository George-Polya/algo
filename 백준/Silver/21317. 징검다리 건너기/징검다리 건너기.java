import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n - 1][2];
		for(int i = 0; i < n - 1;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		k = Integer.parseInt(br.readLine());
		
		dp = new int[n-1][2];
		for(int row[] : dp)
			Arrays.fill(row, INF);
		
//		solve2(0,0,0);
		System.out.println(solve(0,0));
		
	}
	
	static int dp[][];	
	static int solve(int cur, int state) {
		if(cur == n-1) {
			return 0;
		}
		
		if(cur > n-1)
			return INF;
		
		if(dp[cur][state] != INF)
			return dp[cur][state];
		
		int ret = INF;
		
		if(state == 0) {
			ret = Math.min(ret,  solve(cur + 3, 1) + k);
		}
		
		ret = Math.min(ret,  solve(cur + 1, state) + arr[cur][0]);
		ret = Math.min(ret,  solve(cur + 2, state) + arr[cur][1]);
		
		return dp[cur][state] = ret;
	}
	
	
	
	
//	static void solve2(int cur, int state, int sum) {
//		if(cur == n - 1) {
//			System.out.println("sum: "+sum);
//			ans = Math.min(ans,  sum);
//			return;
//		}
//		
//		if(cur > n - 1)
//			return;
//		
//		if(state == 0) {
//			solve2(cur + 3, 1, sum + k);
//		}
//		
//		solve2(cur + 1, state, sum + arr[cur][0]);
//		solve2(cur + 2, state, sum + arr[cur][1]);
//	}
	
	static int INF = Integer.MAX_VALUE / 2;
	static int ans = INF;
	static StringTokenizer st;
	static int n,k,arr[][];
}