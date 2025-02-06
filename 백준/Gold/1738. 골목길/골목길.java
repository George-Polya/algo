import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	dist = new long[N+1];
    	
    	edges = new Edge[M];
    	Arrays.fill(dist, INF);
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		
    		edges[i] = new Edge(from, to, cost);
    	}
    	
    	prev = new int[N+1];
    	dist[1] = 0;
    	for(int i = 1; i<=N; i++) {
    		for(Edge e : edges) {
    			if(dist[e.from] == INF)
    				continue;
    			
    			if(dist[e.to] < dist[e.from] + e.cost) {
    				prev[e.to] = e.from;
    				dist[e.to] = dist[e.from] + e.cost;
    			}
    		}
    	}
    	
    	for(Edge e : edges) {
    		if(dist[e.from] == INF || dist[e.to] >= dist[e.from ] + e.cost)
				continue;
    		
    		if(canReach(1,e.from) && canReach(e.from, N)) {
    			System.out.println(-1);
    			return;
    		}
			
    	}
    	Stack<Integer> stk = new Stack<>();
    	int cur = N;
    	while(cur != 0) {
    		stk.push(cur);
    		cur = prev[cur];
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while(!stk.isEmpty()) {
    		sb.append(stk.pop()).append(' ');
    	}
    	
    	System.out.println(sb);
    	
    } 
    
    static boolean canReach(int start, int end) {
    	boolean visited[] = new boolean[N+1];
    	Queue<Integer> q = new ArrayDeque<>();
    	q.add(start);
    	
    	visited[start] = true;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		
    		if(cur == end)
    			return true;
    		
    		for(Edge e : edges) {
    			if(e.from != cur || visited[e.to])
    				continue;
    			q.add(e.to);
    			visited[e.to] = true;
    		}
    	}
    	
    	return false;
    }
    
    
    static int N,M;
    static StringTokenizer st;
    static long dist[];
    static int prev[];
    static Edge edges[];
    static long INF = Long.MIN_VALUE;
    static class Edge{
    	int from,to,cost;
    	public Edge(int from, int to, int cost) {
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    }
}