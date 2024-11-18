import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(n));
    }
    static long solve(int n) {

    	if(n < 2)
    		return dp[n] = 1;
    	
    	if(dp[n] != -1)
    		return dp[n];
    	
    	return dp[n] = (solve(n - 2) + solve(n-1) + 1 ) % MOD;
    	
    	
    }
    static int MOD = 1000000007;
    
    static int n;
    static long dp[];
}

