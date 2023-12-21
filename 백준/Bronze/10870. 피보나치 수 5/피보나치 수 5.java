import java.io.*;
import java.util.*;
/**
 * 에라토스테네스의 체 
 */
public class Main {
	static int n;
	static long dp[];
	
	static long fibo(int i) {
		if(i<=1)
			return dp[i] = i;
		
		if(dp[i] != -1)
			return dp[i];
		
		dp[i] = fibo(i - 1) + fibo(i-2);
		return dp[i];
	}
	
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	n = Integer.parseInt(br.readLine());
	 	dp = new long[n+1];
	 	Arrays.fill(dp, -1);
	 	
	 	System.out.println(fibo(n));
	}
}