import java.io.*;
import java.util.*;

public class Main {
	static int MAX_N = 100;
	static long dp[];
	static int T;
	static long LONG_MAX = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp = new long[MAX_N+1]; //dp[i] : i개의 성냥개비를 이용해 만들 수 있는 가장 작은 
		T = Integer.parseInt(br.readLine());
		
		Arrays.fill(dp, LONG_MAX);
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		dp[8] = 10;
		
		
		String[] strs = {"1","7","4","2","0","8"};
		
		// dp[n] = dp[n-2] + strs[2] + dp[n-3] + strs[3] + ...+ dp[n-7] + strs[7];
		
		for(int i = 9; i<=100; i++) {
			for(int k = 2; k<=7; k++) {
				String temp = "" + dp[i-k] + strs[k-2];
				dp[i] = Math.min(Long.parseLong(temp), dp[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int tc=0;tc<T;tc++) {
			int n = Integer.parseInt(br.readLine());
			StringBuilder max = new StringBuilder();
			long a = n / 2;
			long b = n % 2;
			if(b == 1) {
				max.append("7");
			}else {
				max.append("1");
			}
			
			for(int i=1; i<a;i++)
				max.append("1");
			sb.append(dp[n]).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
}