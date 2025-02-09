import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	dpSum = new long[N+1];
    	dp = new long[N+1];
    	
    	dp[1] = 2;
    	if( N >= 2)
    		dp[2] = 7;
    	
    	dpSum[0] = 1;
    	
    	for(int i = 3; i<=N;i++) {
    		dp[i] = (2*dp[i-1] + 3 * dp[i-2] + 2 * dpSum[i-3]) % MOD;
    		dpSum[i-2] = (dpSum[i-3] + dp[i-2]) % MOD;
    	} 
    	System.out.println(dp[N]);
    }
    static int N;
    static long dp[], dpSum[];
    static int MOD = (int)1e9+7;
}