import java.io.*;
import java.util.*;
/**
 * 에라토스테네스의 체 
 */
public class Main {
	static int n;
	static long dp[];
	
//	static int fibo(int i) {
//		if( i<=1)
//			return dp[i] = i;
//		if(i == 2) {
//			return dp[i] = 1;
//		}
//		if(dp[i] != -1)
//			return dp[i];
//		
//		dp[i] = fibo(i - 1) + fibo(i-2);
//		return dp[i];
//	}
	
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	n = Integer.parseInt(br.readLine());
	 	dp = new long[n+1];
	 	dp[0] = 0;
	 	if(n >= 1) {
	 		dp[1] = 1;
	 		for(int i =2; i <=n; i++) {
	 			dp[i] = dp[i-1] + dp[i-2];
	 		}
	 	}
	 	System.out.println(dp[n]);
	}
}