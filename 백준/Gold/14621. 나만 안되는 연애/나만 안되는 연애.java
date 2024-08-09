import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n,m;
	static char gender[];
	static class Node implements Comparable<Node>{
		int id, cost;
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
		public int compareTo(Node n) {
			return cost - n.cost;
		}
	}
	static List<Node> adj[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		gender = new char[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n ;i++) {
			gender[i] = st.nextToken().charAt(0);
		}
		
		adj = new List[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Node(to, cost));
			adj[to].add(new Node(from, cost));
		}
		
		int sum = 0;
		int size = 0;
		visited = new boolean[n+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
//		visited[1] = true;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.id])
				continue;
			visited[cur.id] = true;
			sum += cur.cost;
			size++;
			for(Node nxt : adj[cur.id]) {
				if(gender[nxt.id] == gender[cur.id] || visited[nxt.id])
					continue;
				pq.add(nxt);
			}
		}
		System.out.println(size == n ? sum : -1);
	}
}