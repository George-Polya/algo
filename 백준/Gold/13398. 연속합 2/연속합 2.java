import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new int[N+1];
    	dp = new int[2][N+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1;i<=N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    		dp[0][i] = dp[1][i] = INT_MIN;
    	}
    	
    	
    	dp[0][1] = arr[1];
    	
    	for(int i = 2; i<=N;i++) {
    		dp[0][i] = Math.max(dp[0][i-1], 0) + arr[i];
    		dp[1][i] = Math.max(dp[1][i-1] + arr[i], dp[0][i-1]);
    	}
    	
    	int ans = INT_MIN;
    	for(int i = 1; i<=N;i++) {
    		ans = Math.max(ans, Math.max(dp[0][i], dp[1][i]));
    	}
    	System.out.println(ans);
    }
    static int N;
    static int INT_MIN = Integer.MIN_VALUE / 2;
    static int arr[],dp[][];
    static StringTokenizer st;
    
}