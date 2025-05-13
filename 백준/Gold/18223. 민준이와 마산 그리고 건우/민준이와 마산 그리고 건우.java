import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        adj = new List[V+1];
        for(int i = 1;i<=V;i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	adj[from].add(new Edge(to, cost));
        	adj[to].add(new Edge(from, cost));
        }
        
        dist1 = new int[V+1];
        dist2 = new int[V+1];
        for(int i = 0; i <=V;i++) {
        	dist1[i] = dist2[i] = INF;
        }
        
        dijkstra(1, dist1);
        dijkstra(P, dist2);
        
        int stopBy = dist1[P] + dist2[V];
        int shortest = dist1[V];
        
        System.out.println(stopBy <= shortest ? "SAVE HIM" : "GOOD BYE");
        
    }
    
    static void dijkstra(int start, int dist[]) {
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	dist[start] = 0;
    	pq.add(new Edge(start, 0));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(dist[cur.node] < cur.cost)
    			continue;
    		
    		for(Edge nxt : adj[cur.node]) {
    			if(dist[nxt.node] > dist[cur.node] + nxt.cost) {
    				dist[nxt.node] = dist[cur.node] + nxt.cost;
    				pq.add(new Edge(nxt.node, dist[nxt.node]));
    			}
    		}
    	}
    	
    }
    
    static int INF = Integer.MAX_VALUE / 2;
    
    static int dist1[], dist2[];
    static StringTokenizer st;
    static int V,E,P;
    static List<Edge> adj[];
    static class Edge implements Comparable<Edge>{
    	int node, cost;
    	public Edge(int node, int cost) {
    		this.node = node;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge e) {
    		return cost - e.cost;
    	}
    }
}