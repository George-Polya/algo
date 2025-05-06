import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new List[N+1];
		dist = new int[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for(int i = 0; i <M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Node(to, cost));
			adj[to].add(new Node(from, cost));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[1] = 0;
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.idx] < cur.cost)
				continue;
			
			for(Node nxt : adj[cur.idx]) {
				if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
					dist[nxt.idx] = dist[cur.idx] + nxt.cost;
					pq.add(new Node(nxt.idx, dist[nxt.idx]));
				}
			}
		}
		System.out.println(dist[N]);
	}
	static int N,M;
	static StringTokenizer st;
	static List<Node> adj[];
	static int dist[];
	static int INF = Integer.MAX_VALUE / 2;
	static class Node implements Comparable<Node>{
		int idx, cost;
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}
}