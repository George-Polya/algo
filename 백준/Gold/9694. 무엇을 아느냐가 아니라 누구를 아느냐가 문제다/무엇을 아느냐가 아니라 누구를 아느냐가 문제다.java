import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			sb.append("Case ").append("#"+t+":").append(' ');
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dist = new int[M];
			adj = new List[M];
			trace = new int[M];
			for(int i = 0; i < M; i++) {
				adj[i] = new ArrayList<>();
				dist[i] = INF;
				trace[i] = -1;
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				adj[from].add(new Edge(to, cost));
				adj[to].add(new Edge(from, cost));
			}
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			dist[0] = 0;
			pq.add(new Edge(0, 0));
			
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				
				if(dist[cur.idx] < cur.cost)
					continue;
				
				for(Edge nxt : adj[cur.idx]) {
					if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
						dist[nxt.idx] = dist[cur.idx] + nxt.cost;
						trace[nxt.idx] = cur.idx;
						pq.add(new Edge(nxt.idx, dist[nxt.idx]));
					}
				}
			}
			
			
			if(dist[M-1]!=INF) {
				int cur = M - 1;
				Stack<Integer> stk = new Stack<>();
				while(cur != 0) {
					stk.add(cur);
					cur = trace[cur];
				}
				stk.add(cur);
				
				while(!stk.isEmpty()) {
					sb.append(stk.pop()).append(' ');
				}
				sb.append('\n');
				
			}else {
				sb.append(-1).append('\n');
			}
			
			
		}
		System.out.println(sb);
	}
	static int trace[];
	static int INF = Integer.MAX_VALUE / 2;
	static int dist[];
	static int T,N,M;
	static StringTokenizer st;
	static List<Edge> adj[];
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
}

