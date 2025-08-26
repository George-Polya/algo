import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pSum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N;i ++) {
			pSum[i] = pSum[i-1] + Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		dp = new int[3][N  + 1];
		for(int row[] : dp) {
			Arrays.fill(row, -1);
		}
		
		System.out.println(solve(0,1));
		
	}
	
	static int solve(int cnt, int cur) {
		if(cnt == 3)
			return 0;
		
		if(cur + M - 1 > N)
			return 0;
		if(dp[cnt][cur] != -1)
			return dp[cnt][cur];
		
		int ret = solve(cnt, cur + 1);
		
		ret = Math.max(ret,  solve(cnt + 1, cur + M) + pSum[cur+M-1] - pSum[cur - 1]);
		
		return dp[cnt][cur] = ret;
		
					
	}
	
	static int N,M;
	static StringTokenizer st;
	static int arr[], pSum[], dp[][];
}

