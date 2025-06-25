import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		Arrays.fill(dp, -1);
		System.out.println(solve(N));
	}
	static long solve(int cur) {
		if(cur <= 6 )
			return cur;
		
		if(dp[cur] != -1)
			return dp[cur];
			

		long ret = solve(cur - 1) + 1;
		for(int prv = 1; prv <= cur - 3; prv++) {
			ret = Math.max(ret,  solve(prv) * (cur - prv - 1));
		}
		
		return dp[cur] = ret;
	}
	static int N;
	static long dp[];
	
}