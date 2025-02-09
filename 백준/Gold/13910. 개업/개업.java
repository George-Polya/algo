import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	wocks = new int[M];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < M; i++) {
    		wocks[i] = Integer.parseInt(st.nextToken());
    		cooks.add(wocks[i]);
    	}
    	
    	
    	for(int i = 0 ;i < M - 1;i++) {
    		for(int j = i +1 ; j<M; j++) {
    			int sum = wocks[i] + wocks[j];
    			cooks.add(sum);
    		}
    	}
    	dp = new int[N+1];
    	Arrays.fill(dp,INT_MAX);
    	
    	dp[0] = 0;
    	
    	for(int i = 1; i<=N;i++) {
    		for(int cook: cooks) {
    			if(i - cook >=0 && dp[i-cook] != INT_MAX)
    				dp[i] = Math.min(dp[i], dp[i-cook] + 1);
    		}
    	}
    	
    	System.out.println(dp[N] == INT_MAX ? -1 : dp[N]);
    }
    
    static int dp[];
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    
    
    
    static int N,M;
    static StringTokenizer st;
    static int wocks[];
    static List<Integer> cooks = new ArrayList<>();
}