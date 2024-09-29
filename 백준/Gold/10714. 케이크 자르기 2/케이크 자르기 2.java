import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        dp = new long[n+1][n+1];
        for(int i = 0 ; i <=n; i++)
        	Arrays.fill(dp[i], -1);
        
        
        long ans = Long.MIN_VALUE;
        for(int i = 1; i<=n; i++) {
        	ans = Math.max(ans, arr[i] + dfs(0, Left(i), Right(i)));
        	
        }
        
        System.out.println(ans);
        
    }
    
    static long dfs(int cnt, int left, int right) {
    	if(left == right) {
    		return cnt == 1 ? arr[left] : 0;
    	}
    	
    	if(dp[left][right] != -1)
    		return dp[left][right];
    	
    	dp[left][right] = 0;
    	
    	if(cnt == 1)
    		dp[left][right] += Math.max(arr[left] + dfs(0, Left(left), right), arr[right] + dfs(0, left, Right(right)));
    	else {
    		dp[left][right] += arr[left] > arr[right] ? dfs(1, Left(left), right) : dfs(1, left, Right(right));
    	}
    	
    	return dp[left][right];
    }
    
    static int Left(int idx) {
    	int left = idx - 1;
    	return (left % n == 0) ? n : left % n;
    }
    
    static int Right(int idx) {
    	int right = idx + 1;
    	return (right % n == 0) ? n : right % n;
    }
    
    static long dp[][];
    
    static int n;
    static int arr[];
}