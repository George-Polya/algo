import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dp = new int[MAX_N+1][MAX_K+1][2];
		for(int n = 0; n<=MAX_N;n++) {
			for(int k=0; k<=MAX_K;k++) {
				for(int i = 0; i <2 ; i++)
					dp[n][k][i] = -1;
			}
		}
		
		for(int t=0; t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			sb.append(solve(N,K,0) + solve(N,K,1)).append('\n');
		}
		System.out.println(sb);
	}
	
	static int solve(int idx, int cnt ,int bit) {
		if(cnt < 0)
			return 0;
		if(idx == 1) {
			return cnt == 0 ? 1 : 0;
		}
		
		if(dp[idx][cnt][bit] != -1) {
			return dp[idx][cnt][bit];
		}
		
		int ret = solve(idx - 1, cnt, 0);
		if(bit == 0)
			ret += solve(idx-1,cnt,1);
		else
			ret += solve(idx-1,cnt-1,1);
		
		return dp[idx][cnt][bit] = ret;
			
	}
	
	static int MAX_N = 100;
	static int MAX_K = 100;
	static int dp[][][];
	static int T,N,K;
	static StringTokenizer st;
}