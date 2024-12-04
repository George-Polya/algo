import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	adj = new ArrayList[n+1];
    	for(int i = 1; i<=n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	dp = new long[n+1];
    	
    	for(int i = 2; i<=n; i++) {
    		st = new StringTokenizer(br.readLine());
    		char ch = st.nextToken().charAt(0);
    		int value = Integer.parseInt(st.nextToken());
    		int from = Integer.parseInt(st.nextToken());
    		
    		
    		adj[from].add(i);
    		if(ch == 'W')
    			value *= -1;
    		
    		dp[i] = value;
    	}
    	
    	dfs(1,-1);
    	System.out.println(dp[1]);
    }
    
    static long dp[];
    
    static void dfs(int cur, int parent) {
    	for(int nxt : adj[cur]) {
    		dfs(nxt, cur);
    	}
    	
    	if(parent != -1 && dp[cur] > 0)
    		dp[parent] += dp[cur];
    }
    
    static int n;
    static StringTokenizer st;
    static ArrayList<Integer> adj[];
}