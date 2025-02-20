import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	weights = new int[N+1];
    	values = new int[N+1];
    	int maxWeight = 0;
    	for(int i = 1; i<=N; i++) {
    		st = new StringTokenizer(br.readLine());
    		weights[i ] = Integer.parseInt(st.nextToken());
    		values[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	bags = new int[M+1];
    	for(int i = 1; i<=M; i++) {
    		bags[i] = Integer.parseInt(br.readLine());
    		maxWeight = Math.max(maxWeight, bags[i]);
    	}
    	
    	dp = new int[maxWeight + 1];
    	for(int i =1; i<=N;i++) {
    		for(int k = maxWeight; k>=1; k--) {
    			if(k - weights[i] >= 0)
    				dp[k] = Math.max(dp[k], dp[k-weights[i]] + values[i]);
    		}
    	}
    	
    	double ans = -1.0;
    	int ret = M + 2;
    	
    	for(int i=1; i<=M;i++) {
    		int bag = bags[i];
    		double effi = (double)dp[bag] / bag;
    		if(ans < effi || (ans == effi && ret > i)) {
    			ans = effi;
    			ret = i;
    		}
    	}
    	
    	System.out.println(ret);
    	
    	
    	
    }
    static int dp[];
    static int weights[], values[];
    static int N,M;
    static StringTokenizer st;
    static int bags[];
}