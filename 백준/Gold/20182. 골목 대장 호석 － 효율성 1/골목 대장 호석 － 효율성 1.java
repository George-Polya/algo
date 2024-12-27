import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n + 1];
		shames = new int[n+1];
		for(int i = 0; i <=n; i++) {
			adj[i] = new ArrayList<>();
			shames[i] = INF;
		}
		
		for(int i = 0; i < m ;i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, cost));
			adj[to].add(new Edge(from, cost));
		}
		
		
		System.out.println(dijkstra());
	}
	
	static class Node implements Comparable<Node>{
		int node, cost, shame;
		public Node(int node, int cost, int shame) {
			this.node = node;
			this.cost = cost;
			this.shame = shame;
		}
		
		public int compareTo(Node o) {
			return shame - o.shame;
		}
	}
	
	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start,0,0)); // 현재 노드, start에서 현재 노드까지 오는데 누적된 비용, 현재 노드, 수치심 
		shames[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.node == end)
				return shames[end];
			
			if(shames[cur.node] < cur.shame)
				continue;
			
			for(Edge nxt: adj[cur.node]) {
				if(cur.cost + nxt.cost <= c && shames[nxt.node] > Math.max(shames[cur.node], nxt.cost)) {
					
					shames[nxt.node] = Math.max(shames[cur.node], nxt.cost);
					pq.add(new Node(nxt.node, nxt.cost + cur.cost, shames[nxt.node]));
				}
			}
		}
		
		return -1;
	}
	
	static int shames[];
	static int INF = Integer.MAX_VALUE / 2;
	static class Edge {
		int node, cost;
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
	}
	static ArrayList<Edge> adj[];
	static StringTokenizer st;
	static int n,m,start,end,c;
}