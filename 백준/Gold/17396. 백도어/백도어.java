import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paths = new int[N];
		dist = new long[N];
		adj = new List[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			paths[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, cost));
			adj[to].add(new Edge(from, cost));
			
		}
		
		dist[0] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, dist[0]));
		
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.id] < cur.cost)
				continue;
			
			for(Edge nxt : adj[cur.id]) {
				if(paths[nxt.id] == 1 && nxt.id != (N-1))
					continue;
				
				if(dist[nxt.id] > dist[cur.id] + nxt.cost) {
					dist[nxt.id] = dist[cur.id] + nxt.cost;
					pq.add(new Edge(nxt.id, dist[nxt.id]));
				}
			}
		}
		
		System.out.println(dist[N-1] == INF ? -1 : dist[N-1]);
		
	}
	static long dist[];
	static long INF = Long.MAX_VALUE - 1;
	static int paths[];
	static StringTokenizer st;
	
	static int N,M;
	static List<Edge> adj[];
	
	static class Edge implements Comparable<Edge>{
		int id;
		long cost;
		public Edge(int id, long cost) {
			this.id = id;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}

