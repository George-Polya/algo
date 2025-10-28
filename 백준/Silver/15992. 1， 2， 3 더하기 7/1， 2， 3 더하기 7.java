import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dp = new int[1001][1001];
		
		for(int row[] : dp) {
			Arrays.fill(row, -1);
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(solve(N,M) % MOD).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static int solve(int sum, int cnt) {
		if(sum < 0 || cnt < 0)
			return 0;
		
		if(sum == 0 && cnt == 0)
			return 1;
		
		if(dp[sum][cnt] != -1)
			return dp[sum][cnt];
		
		int ret = solve(sum - 1, cnt - 1);
		ret = (ret + solve(sum - 2, cnt - 1)) % MOD;
		ret = (ret + solve(sum - 3, cnt - 1)) % MOD;
		
		return dp[sum][cnt] = ret;
		
	}
	static int dp[][];
	
	static int MOD = (int)1e9 + 9;
	static StringTokenizer st;
	static int T, N, M;
}
