import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        
//        
//        if(K == 0) {
//        	System.out.println(1);
//        	return;
//        }
//        	
//        if(K == 1) {
//        	System.out.println(N);
//        	return;
//        }
//        
//        if(K > N / 2) {
//        	System.out.println(0);
//        	return;
//        }
        dp = new long[N+1][K+1];
        for(long row[] : dp) {
        	Arrays.fill(row, -1);
        }
        
        long ans1 = solve(N-1, K);
        long ans2 = solve(N-3, K-1);
        
        
        ans1 = (ans1 + ans2) % MOD;
        System.out.println(ans1);
    }
    
    static long solve(int cur,int cnt) {
    	if(cnt == 0)
    		return 1;
    	
    	if(cur <= 0 )
    		return 0;
    	
    	if(cnt == 1)
    		return cur;
    	
    	if(cur < cnt)
    		return 0;
    	
    	if(dp[cur][cnt ] != -1)
    		return dp[cur][cnt];
    	
    	long ret = solve(cur - 2, cnt-1);
    	ret = (ret + solve(cur-1, cnt)) % MOD;
    	
    	return dp[cur][cnt] = ret;
    }
    static int MOD = (int)1e9+3;
    static long dp[][];
    static int N,K;
}