import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	/*
    	 * 1 -> v1으로 최단경로로 이동
    	 * v1->v2으로 최단경로로 이동 
    	 * v2-> n으로 최단경로로 이동
    	 * 가능한 이유: 한번 이동했던 정점과 이동했던 간선을 다시 이동할 수 있
    	 * 다익스트라 3번 돌리면 됨 
    	 */
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	e = Integer.parseInt(st.nextToken());
    	
    	adj = new ArrayList[n+1];
    	for(int i = 1; i<=n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i <e ; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		
    		adj[from].add(new Edge(to, cost));
    		adj[to].add(new Edge(from, cost));
    	}
    	st = new StringTokenizer(br.readLine());
    	v1 = Integer.parseInt(st.nextToken());
    	v2 = Integer.parseInt(st.nextToken());
    	
    	dist = new int[n+1];
    	dijkstra(1);
    	
    	int s2v1 = dist[v1];
    	int s2v2 = dist[v2];
    	
    	dijkstra(v1);
    	int v1Tov2 = dist[v2];
    	int v2Tov1 = v1Tov2;
    	int v1ToN = dist[n];
    	
    	dijkstra(v2);
    	int v2ToN = dist[n];
    	
    	int ans = Math.min(INF, s2v1 + v1Tov2 + v2ToN);
    	ans = Math.min(ans, s2v2 + v2Tov1 + v1ToN );
    	System.out.println(v1Tov2 == INF || ans == INF ? -1 : ans);
    }
    
    static StringTokenizer st;
    static int n,e;
    static class Edge implements Comparable<Edge>{
    	int idx, cost;
    	public Edge(int idx, int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    }
    static ArrayList<Edge> adj[];
    static int v1, v2;
    
    static int INF = Integer.MAX_VALUE / 2;
    static int dist[];
    static void dijkstra(int start) {
    	Arrays.fill(dist, INF);
    	dist[start] = 0;
    	
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	
    	pq.add(new Edge(start, dist[start]));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(dist[cur.idx] < cur.cost)
    			continue;
    		
    		for(Edge nxt : adj[cur.idx]) {
    			if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
    				dist[nxt.idx] = dist[cur.idx] + nxt.cost;
    				pq.add(new Edge(nxt.idx, dist[nxt.idx]));
    			}
    		}
    	}
    }
    
    
}