import java.util.*;
import java.io.*;
public class Main {
	static int n,root;
	static List<Node> adj[];
	static StringTokenizer st;
	static List<Integer> leafs = new ArrayList<>();
	
	static class Node{
		int id, cost;
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
	}
	
	static int findGiga(int cur, int parent) {
		if(adj[cur].size() == 1 && cur != root)
			return -1;
		
		int cnt = 0;
		for(Node nxt : adj[cur]) {
			if(nxt.id == parent)
				continue;
			cnt++;
		}
		if(cnt >= 2) {
			return cur;
		}
		for(Node nxt : adj[cur]) {
			if(nxt.id == parent)
				continue;
			return findGiga(nxt.id, cur);
		}
		return -1;
	}
	
	static int dist[];
	static int INF = Integer.MAX_VALUE;
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(Node nxt : adj[cur]) {
				if(dist[nxt.id] != INF)
					continue;
				dist[nxt.id] = dist[cur] + nxt.cost;
				q.add(nxt.id);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(st.nextToken());
		adj = new List[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0 ;i <n-1;i++) {
			st=new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[a].add(new Node(b,cost));
			adj[b].add(new Node(a,cost));
		}
		
		if(n == 1) {
			
			System.out.println("0 0");
		
			return;
		}
			
		
		for(int i = 1; i<=n; i++) {
			if(i == root)
				continue;
			if(adj[i].size() == 1) {
				leafs.add(i);
			}
		}
		
		int giga = 0;
		
		giga = findGiga(root, -1);
		if(leafs.size() == 1) {
			giga = leafs.get(0);
		}
		
//		System.out.println(giga);
		dist = new int[n+1];
		Arrays.fill(dist, INF);
		bfs(giga);
//		System.out.println(Arrays.toString(dist));
		
		int columnLength = dist[root];
		int _max = 0;
		for(int leaf : leafs) {
			_max = Math.max(_max,dist[leaf]);
		}
		
		System.out.println(columnLength +" "+_max);
	}
}