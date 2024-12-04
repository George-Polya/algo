import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n+1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}

    	dp = new int[n+1];
    	for(int i = 1; i<=n; i++) {
    		if(arr[i] >= arr[i-1])
    			dp[i] = dp[i-1]+1;
    		else
    			dp[i] = 1;
    	}
    	int ans = 0;
    	for(int i = 1; i<=n; i++) {
    		ans = Math.max(ans, dp[i]);
    	}
    	
    	dp = new int[n+1];
    	for(int i = 1; i<=n; i++) {
    		if(arr[i] <= arr[i-1])
    			dp[i] = dp[i-1] + 1;
    		else
    			dp[i] = 1;
    	}
    	for(int i = 1; i<=n; i++) {
    		ans = Math.max(ans, dp[i]);
    	}
    	System.out.println(ans);
    }
    static int dp[];
    static StringTokenizer st;
    static int n;
    static int arr[];
}