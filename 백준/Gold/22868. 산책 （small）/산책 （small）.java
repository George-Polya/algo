import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		for(int i = 1; i<=n; i++) {
			Collections.sort(adj[i]);
		}
		st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		
		Queue<Node> q = new ArrayDeque<>();
		dist = new int[n+1];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		q.add(new Node(s, new ArrayList<Integer>()));
		ArrayList<Integer> s2e = new ArrayList<>();
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.idx == e) {
//				System.out.println(cur.list);
				s2e = cur.list;
				break;
			}
			
			for(int nxt : adj[cur.idx]) {
				if(dist[nxt] != INF)
					continue;
				dist[nxt] = dist[cur.idx] + 1;
				ArrayList<Integer> list = (ArrayList)cur.list.clone();
				list.add(nxt);
				q.add(new Node(nxt, list));
			}
		}
		
		int ans = dist[e];
		
		visited = new boolean[n+1];
		Arrays.fill(dist, INF);
		for(int v : s2e)
			visited[v] = true;
		Queue<Integer> q2 = new ArrayDeque<>();
		dist[e] = 0;
		q2.add(e);
		
		while(!q2.isEmpty()) {
			int cur = q2.poll();
//			System.out.println(cur);
			
			for(int nxt : adj[cur]) {
				if(dist[nxt] != INF || visited[nxt])
					continue;
				dist[nxt] = dist[cur] + 1;
				q2.add(nxt);
			}
		}
		ans += dist[s];
		System.out.println(ans);
	}
	static boolean visited[];
	static int dist[];
	static int INF = Integer.MAX_VALUE / 2;
	static class Node{
		int idx;
		ArrayList<Integer> list;
		public Node(int idx, ArrayList<Integer> list) {
			this.idx = idx;
			this.list = list;
		}
	}
	
	static StringTokenizer st;
	static int n,m,s,e;
	static ArrayList<Integer> adj[];
}