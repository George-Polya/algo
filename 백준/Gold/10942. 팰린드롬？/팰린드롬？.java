import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int arr[];
	static int n,m;
	static boolean dp[][];
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i =1;i<=n;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	dp = new boolean[n+1][n+1];

    	
    	for(int e = 1; e<=n; e++) {
    		for(int s = 1; s<=e; s++) {
    			if(s==e) {
    				dp[s][e] = true;
    			}else if(e - s == 1) {
    				dp[s][e] = arr[s]==arr[e];
    			}else
    				dp[s][e] = dp[s+1][e-1] && arr[s]==arr[e];
    		}
    	}
    	
    	m= Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int e = Integer.parseInt(st.nextToken());
    		sb.append(dp[s][e] ? 1 : 0).append('\n');
    	}
    	System.out.println(sb);
    }
}