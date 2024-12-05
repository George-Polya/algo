import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0;i <n ;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	m = Integer.parseInt(br.readLine());
    	
    	dp = new boolean[n+1][40000+1];
    	solve(0, 0);

    	
    	
    	StringBuilder sb = new StringBuilder();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < m ;i++) {
    		int ball = Integer.parseInt(st.nextToken());
    		
    		sb.append(dp[n][ball] ? 'Y' : 'N').append(' ');
    	}
    	System.out.println(sb);
    }
    
    static void solve(int cur,int weight) {
    	if(dp[cur][weight])
    		return;
    	
    	dp[cur][weight] = true;
    	
    	if(cur == n)
    		return;
    	
    	
    	solve(cur + 1, weight + arr[cur]);
    	solve(cur + 1, weight);
    	solve(cur + 1, Math.abs(weight - arr[cur]));
    }
    
    static int n,m;
    static boolean dp[][];
    static int arr[];
    static StringTokenizer st;
}