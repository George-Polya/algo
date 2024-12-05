import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	dp = new int[21];
    	dp[1] = 1;
    	dp[2] = 2;
    	dp[3] = 4;
    	dp[4] = 7;
    	
    	for(int i = 5; i<=n; i++) {
    		dp[i] = 2* dp[i-1] - (i % 2 == 0 ? (dp[i-4] + dp[i-5]) : 0);
    	}
    	System.out.println(dp[n]);
    }
    static int n;
    static int dp[];
}