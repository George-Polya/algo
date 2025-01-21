import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	p = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	adj = new ArrayList[n+1];
    	for(int i = 1; i<=n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	for(int i = 0; i < p;i ++) {
    		st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	adj[from].add(new Edge(to, cost));
        	adj[to].add(new Edge(from, cost));
    	}
    	
    	int left = 0;
    	int right = 1_000_000;
    	int ans = -1;
    	while(left<=right) {
    		int mid = (left + right) / 2;
    		
    		if(decide(mid)) {
    			right = mid - 1;
    			ans = mid;
    		}else {
    			left = mid + 1;
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
    
    static boolean decide(int x) {
    	int[] dist = new int[n+1];
    	Arrays.fill(dist, INF);
    	
    	
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.add(new Edge(1, 0));
    	dist[1] = 0;
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(dist[cur.idx] < cur.cost)
    			continue;
    		
    		for(Edge nxt : adj[cur.idx]) {
    			int temp = dist[cur.idx] + (nxt.cost > x ? 1 : 0);
    			if(dist[nxt.idx] > temp) {
    				dist[nxt.idx] = temp;
    				
    				pq.add(new Edge(nxt.idx, dist[nxt.idx]));
    			}
    		}
    	}
    	
    	
    	return dist[n] <= k;
    }
    
    static int INF = Integer.MAX_VALUE;
    static StringTokenizer st;
    static class Edge implements Comparable<Edge>{
    	int idx, cost;
    	
    	public Edge(int idx, int cost) {
    		this.idx= idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    }
    
    static int n,p,k;
    static ArrayList<Edge> adj[];
}