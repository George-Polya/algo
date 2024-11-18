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
    	if(n == 0)
    		return dp[n] = 1;
    	
    	if(dp[n] != -1)
    		return dp[n];
    	
    	long sum = 0;
    	
    	for(int i = 0; i < n; i++) {
    		sum += solve(i) * solve( n - 1 - i);
    	}
    	
    	return dp[n] = sum;
    }
    
    static int n;
    static long dp[];
}