import java.util.*;
import java.io.*;

public class Main {
	static int dp[];
	static StringTokenizer st;
	static int d,p;
	static int length[], capacity[];
	static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	d = Integer.parseInt(st.nextToken());
    	p = Integer.parseInt(st.nextToken());

//    	dp = new int[p+1][d+1];
    	length = new int[p+1];
    	capacity = new int[p+1];
    	for(int i = 1; i<=p ;i++) {
    		st = new StringTokenizer(br.readLine());
    		length[i] = Integer.parseInt(st.nextToken());
    		capacity[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	
    	dp = new int[d+1];
    	dp[0] = INF;
    	
    	for(int i = 1; i<=p;i++) {
    		for(int len = d; len>=1; len--) {
    			if(len - length[i]>=0 ) {
    				dp[len] = Math.max(dp[len], Math.min(dp[len-length[i]], capacity[i]));
    			}
    		}
    	}
    	System.out.println(dp[d]);
    	
    	
    }
}