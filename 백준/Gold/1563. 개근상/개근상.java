import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
//        solve(0,0,0,"");
//        System.out.println(cnt);
        dp = new int[N][2][3];
        System.out.println(solve(0,0,0));
    }
    static int cnt;
    static int dp[][][];
    static int solve(int cur, int late, int absent) {
    	if(cur == N)
    		return 1;
    	
    	if(dp[cur][late][absent] != 0)
    		return dp[cur][late][absent];
    	
    	int ret = solve(cur + 1, late, 0);
    	if(late < 1)
    		ret = (ret + solve(cur + 1, late+1, 0)) % MOD;
    	
    	if(absent < 2)
    		ret = (ret + solve(cur + 1, late, absent + 1)) % MOD;
    	
    	return dp[cur][late][absent] = ret;
    }
    static int MOD = 1_000_000;
    
    static void solve(int cur,int late, int absent, String str) {
    	if(cur == N) {
//    		System.out.println(str);
    		cnt++;
    		return;
    	}
    	
    	solve(cur + 1, late, 0, str+'O');
    	
    	if(late < 1)
    		solve(cur + 1, late + 1, 0, str+'L');
    	
    	if(absent < 2)
    		solve(cur + 1, late, absent + 1, str+'A');
    }
    static int N;
}