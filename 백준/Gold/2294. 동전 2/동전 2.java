import java.util.*;
import java.io.*;
public class Main {
	static int n,k;
	static int arr[], dp[];
	static int INT_MAX = Integer.MAX_VALUE;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	arr = new int[n+1];
    	dp = new int[k+1];
    	Arrays.fill(dp, INT_MAX);
    	for(int i =1; i<=n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	dp[0] = 0;
    	for(int i = 1; i<=n; i++) {
    		for(int sum = 1; sum<=k; sum++) {
    			if(sum - arr[i]<0)
    				continue;
    			if(dp[sum - arr[i]] != INT_MAX) {
//    				System.out.printf("sum: %d, sum-arr[%d]: %d\n", sum,i, sum-arr[i]);
    				dp[sum] = Math.min(dp[sum], dp[sum-arr[i]] + 1);
    			}
    		}
    	}
    	System.out.println(dp[k] == INT_MAX ? -1: dp[k]);
    }
}