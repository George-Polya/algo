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
    	int ans = INT_MAX;
    	dp = new int[N+1];
    	ans = solve(N);
    	System.out.println(ans == INT_MAX ? -1: ans);
    	
    }
    
    static int solve(int food) {
    	if(food < 0)
    		return INT_MAX;
    	
    	if(food == 0)
    		return 0;
    	
    	if(dp[food] != 0)
    		return dp[food];
    	
    	int ret = INT_MAX;
    	for(int cook : cooks) {
    		if(food - cook >= 0)
    			ret = Math.min(ret, solve(food - cook) + 1);
    	}
    	return dp[food] = ret;
    }
    static int dp[];
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    
    
    
    static int N,M;
    static StringTokenizer st;
    static int wocks[];
    static List<Integer> cooks = new ArrayList<>();
}