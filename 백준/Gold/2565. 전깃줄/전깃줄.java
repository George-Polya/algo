import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new Pair[N+1];
    	dp = new int[N+1];
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		arr[i] = new Pair(a,b);
    	}
    	
    	Arrays.sort(arr, 1, N+1);
    	
    	
    	for(int i = 1; i<=N;i++) {
    		ans = Math.max(ans, solve(i));
    	}
    	System.out.println(N - ans);
    }
    
    static int solve(int cur) {
    	if(dp[cur] != 0)
    		return dp[cur];
    	int ret = 1;
    	
    	for(int i = 1; i< cur; i++) {
    		if(check(arr[i], arr[cur]))
    			ret = Math.max(ret, solve(i) + 1);
    	}
    	
    	return dp[cur] = ret;
    }
    
    static boolean check(Pair prev, Pair cur) {
    	return prev.b < cur.b;
    }
    
    static int dp[];
    
    static int ans;
    
    static int N;
    static StringTokenizer st;
    
    static Pair arr[];
    
    static class Pair implements Comparable<Pair>{
    	int a, b;
    	public Pair(int a, int b) {
    		this.a = a;
    		this.b = b;
    				
    	}
    	
    	public int compareTo(Pair o) {
    		if( a != o.a)
    			return a - o.a;
    		return b - o.b;
    	}
    }
    
}