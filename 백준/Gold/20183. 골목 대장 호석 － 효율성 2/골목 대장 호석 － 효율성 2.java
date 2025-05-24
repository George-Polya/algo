import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		COST = Long.parseLong(st.nextToken());
		
		adj = new List[N+1];
		for(int i = 1; i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		long l = 1;
		long r = -1;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, cost));
			adj[to].add(new Edge(from, cost));
			r = Math.max(r, cost);
		}
		
		long ans = INF;
		dist = new long[N+1];
		while(l<=r) {
			long mid = (l + r)/2;
			
			if(decide(mid)) {
				r = mid - 1;
				ans = mid;
			}else {
				l = mid + 1;
			}
		}
		
		System.out.println(ans == INF ? -1 : ans);
		
	}
	
	static boolean decide(long c) {
		Arrays.fill(dist, INF);
		maxCost = new long[N+1];
		dist[S] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(S, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.idx] < cur.cost)
				continue;
			
			for(Edge nxt : adj[cur.idx]) {
				if(nxt.cost > c)
					continue;
				long temp = dist[cur.idx] + nxt.cost;
				if(temp > COST)
					continue;
				if(dist[nxt.idx] > temp) {
					dist[nxt.idx] = temp;
					maxCost[nxt.idx] = Math.max(maxCost[nxt.idx], nxt.cost);
					pq.add(new Edge(nxt.idx, dist[nxt.idx]));
				}
			}
		}
//		System.out.println("dist: "+Arrays.toString(dist));
//		System.out.println("maxCost: "+Arrays.toString(maxCost));
		
		return dist[E] <= COST && maxCost[E] <= c;
		
	}
	
	static StringTokenizer st;
	static int N,M,S,E;
	static long COST;
	static List<Edge> adj[];
	static long INF = (long)1e14 + 9;
	static long dist[], maxCost[];
	static class Edge implements Comparable<Edge>{
		int idx;
		long cost;
		public Edge(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(cost, o.cost);
		}
		
		public String toString() {
			return String.format("[ idx: %d, cost: %d ]", idx, cost);
		}
	}
}