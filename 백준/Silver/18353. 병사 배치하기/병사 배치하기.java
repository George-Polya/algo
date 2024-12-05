import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <=n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	arr[0] = Integer.MAX_VALUE / 2;
    	dp = new int[n+1];
    	int ans = 0;
    	for(int i = 1; i<=n; i++)
    		ans = Math.max(ans, solve(i));
    	System.out.println(n - ans);
    }
    
    static int solve(int cur) {
    	if(cur == 0)
    		return 0;
    	
    	if(dp[cur] != 0)
    		return dp[cur];
    	
    	int ret = 0;
    	for(int i = 0; i < cur; i++) {
    		if(arr[i] > arr[cur])
    			ret = Math.max(ret,  solve(i) + 1);
    	}
    	return dp[cur] = ret;
    }
    
    static int n;
    static int arr[], dp[];
    static StringTokenizer st;
}