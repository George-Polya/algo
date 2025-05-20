import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new List[V+1];
		for(int i = 1; i<=V;i++) {
			adj[i]= new ArrayList<>();
		}
		
		for(int i = 0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v,w));
			adj[v].add(new Edge(u,w));
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		mcdonalds = new HashSet<>();
		for(int i = 0; i <M;i++) {
			mcdonalds.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		starbucks = new HashSet<>();
		for(int i = 0; i < S;i++) {
			starbucks.add(Integer.parseInt(st.nextToken()));
		}
		
		
		Dijkstra mcDijkstra = new Dijkstra(mcdonalds);
		Dijkstra starDijkstra = new Dijkstra(starbucks);
		
//		System.out.println("mc dist: " + Arrays.toString(macDijkstra.dist));
		
		Set<Integer> macArea = mcDijkstra.find(X);
		Set<Integer> starArea = starDijkstra.find(Y);
		
//		System.out.println("macArea: "+macArea);
//		System.out.println("starArea: "+starArea);
		
		long ans = INF;
		
		for(int i = 1; i<=V;i++) {
			if(macArea.contains(i) && starArea.contains(i)) {
				long totalDist = mcDijkstra.dist[i] + starDijkstra.dist[i];
				ans = Math.min(ans,  totalDist);
			}
		}
		
		System.out.println(ans == INF ? -1 : ans);
	}
	
	static class Dijkstra{
		long dist[] = new long[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Set<Integer> starts;
		public Dijkstra(Set<Integer> starts) {
			Arrays.fill(dist, INF);
			this.starts = starts;
			for(int start : starts) {
				dist[start] = 0;
				pq.add(new Edge(start, 0));
			}
			
			build();
		}
		
		private void build() {
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
//				System.out.println("cur: "+cur);
				if(dist[cur.id] < cur.cost)
					continue;
				
				for(Edge nxt : adj[cur.id]) {
					if(dist[nxt.id] > dist[cur.id] + nxt.cost) {
						dist[nxt.id] = dist[cur.id] + nxt.cost;
						pq.add(new Edge(nxt.id, dist[nxt.id]));
					}
				}
			}
		}
		
		public Set<Integer> find(int limit) {
			Set<Integer> ret = new HashSet<>();
			for(int i = 1; i<=V;i++) {
				if(dist[i] > limit || starts.contains(i))
					continue;
				ret.add(i);
			}
			
			return ret;
		}
	}
	
	static long INF = Long.MAX_VALUE / 2;
	static StringTokenizer st;
	static Set<Integer> starbucks, mcdonalds;
	static List<Edge> adj[];
	static int V,E;
	static int M,X, S,Y;
	static class Edge implements Comparable<Edge>{
		int id;
		long cost;
		
		public Edge(int id, long cost) {
			this.id = id;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(cost, o.cost);
		}
		
		public String toString() {
			return id+" "+cost;
		}
	}
}