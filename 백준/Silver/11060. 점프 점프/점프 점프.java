import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());

    	arr = new int[n+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1;i<=n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	dp = new int[n+1];
    	Arrays.fill(dp, -1);
    	int ans = solve(1);
    	System.out.println(ans == INT_MAX ? -1 : ans);
    	
    }
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    static int solve(int cur) {
    	if(cur >= n)
    		return 0;
    	if(dp[cur] != -1)
    		return dp[cur];
    	
    	int ret = INT_MAX;
    	for(int dist = 1; dist<=arr[cur]; dist++)
    		ret = Math.min(ret, solve(cur + dist) + 1);
    	
    	return dp[cur] = ret;
    }
    
    static int dp[];
    static int n;
    static StringTokenizer st;
    static int arr[];
}