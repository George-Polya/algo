import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pSum = new int[N+1];
		dp = new int[N+1][M+1];
		checked = new boolean[N+1][M+1];
		
		for(int i = 0; i<=N;i++) {
			if(i > 0) {
				pSum[i] = pSum[i-1]+Integer.parseInt(br.readLine());
			}
			Arrays.fill(dp[i], INT_MIN);
		}
		
		System.out.println(solve(N,M));
	}
	
	static int solve(int idx, int section) {
		if(section == 0)
			return 0;
		if(idx < 0)
			return INT_MIN;
		
		if(checked[idx][section])
			return dp[idx][section];
		
		checked[idx][section] = true;
		dp[idx][section] = solve(idx - 1, section);
		
		for(int i = idx; i>0;i--) {
			dp[idx][section] = Math.max(dp[idx][section], solve(i-2, section-1) + (pSum[idx]-pSum[i-1]));
		}
		
		return dp[idx][section];
	}
	
	static StringTokenizer st;
	static int N,M;
	static int pSum[], dp[][];
	static boolean checked[][];
	static int INT_MIN = Integer.MIN_VALUE / 2;
}