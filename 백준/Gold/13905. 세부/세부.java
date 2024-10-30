import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	s = Integer.parseInt(st.nextToken());
    	e = Integer.parseInt(st.nextToken());
    	
    	adj = new ArrayList[n+1];
    	for(int i = 1;i<=n;i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i <m ;i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		
    		adj[from].add(new Edge(to, cost));
    		adj[to].add(new Edge(from, cost));
    	}
    	
    	visited = new boolean[n+1];
    	dist = new int[n+1];
    	dist[s] = INF;
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.add(new Edge(s, 0));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(visited[cur.idx])
    			continue;
    		visited[cur.idx] = true;
    		
    		for(Edge nxt : adj[cur.idx]) {
    			dist[nxt.idx] = Math.max(dist[nxt.idx], Math.min(dist[cur.idx], nxt.cost));
    			pq.add(nxt);
    		}
    	}
    	
    	System.out.println(dist[e]);
    }
    
    
    static boolean visited[];
    static int dist[];
    static int INF = Integer.MAX_VALUE / 2;
   static StringTokenizer st;
   static int n,m,s,e;
   
   static ArrayList<Edge> adj[];
   static class Edge implements Comparable<Edge>{
	   int idx, cost;
	   public Edge(int idx,int cost) {
		   this.idx = idx;
		   this.cost = cost;
	   }
	   
	   public int compareTo(Edge o) {
		   return o.cost - cost;
	   }
	   
   }
}