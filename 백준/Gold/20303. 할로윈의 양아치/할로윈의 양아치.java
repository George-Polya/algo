import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	uf = new int[N+1];
    	arr = new int[N+1];
    	st = new StringTokenizer(br.readLine());
    	counts = new int[N+1];
    	
    	for(int i = 1; i<=N;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    		uf[i] = i;
    		counts[i] = 1;
    	}
    	
    	
    	for(int i = 0; i <M;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		union(a,b);
    	}

    	
    	List<Pair> list = new ArrayList<>();
    	for(int i = 1; i<= N; i++) {
    		int I = find(i);
    		if(i == I) {
    			list.add(new Pair(counts[i], arr[i]));
    		}
    	}
    	dp = new int[K];
    	Arrays.fill(dp, INT_MIN);
    	dp[0] =0 ;
    	
    	for(int i = 0; i < list.size(); i++) {
    		int cnt = list.get(i).cnt;
    		int candy = list.get(i).candy;
    		for(int j = K - 1; j >= 0; j--) {
    			if(j - cnt >= 0 && dp[j - cnt] != INT_MIN) {
    				dp[j] = Math.max(dp[j], dp[j-cnt] + candy);
    			}
    		}
    	}
    	
    	int ans = INT_MIN;
    	for(int i = 0 ; i < K; i++) {
    		ans = Math.max(ans, dp[i]);
    	}
    	System.out.println(ans);
    	
    }
    
    static int dp[];
    static int INT_MIN = Integer.MIN_VALUE / 2;
    
    static class Pair{
    	int cnt, candy;
    	public Pair(int cnt, int candy) {
    		this.cnt = cnt;
    		this.candy = candy;
    	}
    }
    
    static int N,M,K;
    static StringTokenizer st;
    static int uf[], arr[], counts[], candies[];
    
    static void union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	if(x == y)
    		return;
    	
    	if(x < y) {
    		uf[y] = x;
    		counts[x] += counts[y];
    		arr[x] += arr[y];
    	}else {
    		uf[x] = y;
    		counts[y] += counts[x];
    		arr[y] += arr[x];
    	}
    }
    
    static int find(int x) {
    	if(x == uf[x])
    		return x;
    	return uf[x] = find(uf[x]);
    }
    
}