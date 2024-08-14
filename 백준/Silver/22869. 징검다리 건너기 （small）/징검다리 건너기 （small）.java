import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int arr[];
	static int n,k;
	static boolean dp[];
	static int INT_MAX = Integer.MAX_VALUE;
	static int calc(int j, int i) {
		return (j - i) * ( 1 + Math.abs(arr[i] - arr[j]));
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        arr = new int[n+1];
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new boolean[n+1];
        dp[1] = true;
        for(int i = 2; i<=n; i++) {
        	for(int c = 1; c<i; c++) {
        		if(!dp[c])
        			continue;
        		dp[i] |= dp[c] && calc(i,c) <= k;
         	}
        }
        
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n] ? "YES": "NO");
        
    }
}