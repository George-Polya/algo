import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n+1][2];
    	
    	for(int i =1; i<=n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a= Integer.parseInt(st.nextToken());
    		int b= Integer.parseInt(st.nextToken());
    		arr[i] = new int[] {a,b};
    	}
    	Arrays.sort(arr, 1,n+1,(o1, o2)->{
    		return o1[0] - o2[0];
    	});
    	dp = new int[n+1][n+1];
    	for(int row[] : dp) {
    		Arrays.fill(row, -1);
    	}
    	
    	System.out.println(n - solve(1,0));
    	
    }
    
    static int solve(int cur, int last) {
    	if(cur == n+1)
    		return 0;
    	
    	if(dp[cur][last] != -1)
    		return dp[cur][last];
    	
    	int ret = solve(cur+1,last);
    	
    	if(arr[cur][1] > arr[last][1])
    		ret = Math.max(ret, solve(cur+1, cur) + 1);
    	
    	return dp[cur][last] = ret;
    			
    }
    	
    
    static int n;
    static StringTokenizer st;
    static int[] arr[];
    static int dp[][];
}