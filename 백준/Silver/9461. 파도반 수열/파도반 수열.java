import java.util.*;
import java.io.*;
public class Main {
	static long dp[];
	static int t,n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		dp = new long[100 + 5];
		dp[1] = dp[2] = dp[3] = 1;
		for(int i=4;i<=100; i++)
			dp[i] = dp[i-2] + dp[i-3];
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=t;tc++) {
			n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append('\n');
		}
		System.out.println(sb);
	}
}