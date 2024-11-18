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
        
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for(int i = 1; i<=n; i++) {
        	for(int j = 0; j < i; j++) {
        		if(arr[j] < arr[i])
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        	}
        }
        
        int ans = -1;
        for(int i = 1;i <=n; i++)
        	ans = Math.max(ans, dp[i]);
        System.out.println(n - ans);
    }
    
    static int n;
   static int arr[], dp[];
}