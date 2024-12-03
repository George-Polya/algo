import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	lose = new int[n + 1];
    	plus = new int[n + 1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		lose[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		plus[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	
    	dp = new int[101];
    	
    	for(int i = 1; i<=n; i++) {
    		for(int h = 100; h>=1; h--) {
    			if(h-lose[i] >0 && dp[h-lose[i]] != -1)
    				dp[h] = Math.max(dp[h], dp[h-lose[i]] +plus[i] );
    				
    		}
    	}
    	
    	System.out.println(dp[100]);
    	
    }
    
    
    static int dp[];
    
    static int ans;
    static StringTokenizer st;
    static int n;
    static int lose[], plus[];
}