import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[n+1];
        arr[0] = Integer.MAX_VALUE;
//        Arrays.fill(dp, -1);
        
//        dp[0] = Integer.MAX_VALUE;
        
        for(int i = 1; i<=n; i++) {
        	for(int j = 0; j<i; j++) {
        		if(arr[i] < arr[j]) {
        			dp[i] = Math.max(dp[i], dp[j] + arr[i]);
        		}
        	}
        }
        
        int ans = -1;
        for(int i = 1;i <=n; i++)
        	ans= Math.max(ans, dp[i]);
        System.out.println(ans);
        
    }
    static int dp[];
    static int n;
    static StringTokenizer st;
    static int arr[];
}