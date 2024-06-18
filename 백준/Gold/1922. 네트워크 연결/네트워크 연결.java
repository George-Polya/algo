import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int n,m;
	static class Node implements Comparable<Node>{
		int id, cost;
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
		
		public int compareTo(Node n) {
			return cost - n.cost;
		}
		
		public String toString() {
			return id+" "+cost;
		}
	}
	
	static List<Node> adj[];
	static int board[][];
	static int INF= Integer.MAX_VALUE;
	static Map<Integer,Integer> map = new HashMap<>();
	
	static int prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));
		
		boolean visited[] = new boolean[n+1];
		int ret = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.id])
				continue;
		
			visited[cur.id] = true;
			ret += cur.cost;
			for(Node nxt : adj[cur.id]) {
				if(visited[nxt.id])
					continue;
				pq.add(nxt);
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1];
		for(int  y=1; y<=n;y++) {
			Arrays.fill(board[y], INF);
			board[y][y] = 0;
		}
		adj = new List[n+1];
		
		int idx = 1;
		for(int i = 0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(!map.containsKey(a)) {
				map.put(a, idx++);
			}
			a = map.get(a);
			
			int b = Integer.parseInt(st.nextToken());
			if(!map.containsKey(b))
				map.put(b, idx++);
			b = map.get(b);
			
			int cost = Integer.parseInt(st.nextToken());
			board[a][b] = Math.min(board[a][b], cost);
			board[b][a] = Math.min(board[b][a], cost);
		}
//		System.out.println(map.size());
		for(int i = 1; i<=n;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				if(board[y][x] != INF && board[y][x] != 0)
					adj[y].add(new Node(x, board[y][x]));
			}
		}
		
//		for(int i = 1; i<=n; i++) {
//			System.out.println(Arrays.toString(adj));
//		}
		

		System.out.println(prim(1));
		
		
		
		
		
	}
}