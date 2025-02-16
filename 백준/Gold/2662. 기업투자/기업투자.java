import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	
    	Invest invests[] = new Invest[N];
    	for(int y = 0; y < N; y++) {
    		st = new StringTokenizer(br.readLine());
    		int cost = Integer.parseInt(st.nextToken());
    		int profits[] = new int[M];
    		for(int x = 0; x<M; x++) {
    			profits[x] = Integer.parseInt(st.nextToken());
    		}
    		
    		invests[y] = new Invest(cost, profits);
    	}
    	
    	Arrays.sort(invests);
    	arr = new int[N+1][M+1];
    	for(int y = 0 ; y <N; y++) {
    		int cost = invests[y].cost;
    		
    		for(int x = 1; x<=M; x++) {
    			arr[cost][x] = invests[y].profits[x - 1];
    		}
    	}
    	
    	dp = new int[N+1][M+1];
    	path = new int[N+1][M+1];
    	path = new int[N+1][M+1];
    	
//    	for(int cost = 1; cost<=N; cost++) {
//    		for(int cur = 0; cur <= cost; cur++) {
//    			for(int company = 1; company<=M; company++) {
//    				if(dp[cost][company] < dp[cost - cur][company - 1] + arr[cur][company]) {
//						dp[cost][company] = dp[cost - cur][company - 1] + arr[cur][company];
//						path[cost][company] = cur;
//    				}    			
//    			}
//    		}
//    	}
    	
    	
    	for(int company = 1; company<=M; company++) {
    		for(int total = 1; total <= N; total++) {
    			for(int cur =0; cur <= total; cur++) {
    				if(dp[total][company] < dp[total - cur][company - 1] + arr[cur][company]) {
    					dp[total][company] = dp[total - cur][company - 1] + arr[cur][company];
    					path[total][company] = cur;
    				}
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(dp[N][M]).append('\n');
    	
    	Stack<Integer> stk = new Stack<>();
    	int company = M;
    	int cost = N;
    	
    	while(company > 0) {
    		int money = path[cost][company];
    		stk.push(money);
    		cost -= money;
    		company--;
    	}
    	
    	while(!stk.isEmpty()) {
    		sb.append(stk.pop()).append(' ');
    	}
    	System.out.println(sb);
    }
    
    static int arr[][];
    static int INT_MIN = Integer.MIN_VALUE / 2;
    static int dp[][], path[][];
    static class Invest implements Comparable<Invest>{
    	int cost;
    	int profits[];
    	public Invest(int cost, int ...profits) {
    		this.cost = cost;
    		this.profits = profits;
    	}
    	
    	public int compareTo(Invest o) {
    		return cost - o.cost;
    	}
    }
    
    static int N,M;
    static StringTokenizer st;
}