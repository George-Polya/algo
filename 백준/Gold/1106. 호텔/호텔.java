import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][2];
		for(int i = 0; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[c+1];
		Arrays.fill(dp, INF);
		
		ans = solve(0);
		System.out.println(ans);
	}
	
	static int solve(int cnt) {
		if(cnt >= c) {
			return 0;
		}
		
		if(dp[cnt] != INF)
			return dp[cnt];
		
		int ret = INF;
		for(int i = 0; i < n ;i++) {
//			solve(sum + arr[i][0] , cnt + arr[i][1]);
			ret = Math.min(ret, solve(cnt + arr[i][1]) + arr[i][0]);
		}
		return dp[cnt] = ret;
	}
	
	static int dp[];
	
	static int INF = Integer.MAX_VALUE / 2;
	static int ans = INF;
	static int c,n, arr[][];
	static StringTokenizer st;
}