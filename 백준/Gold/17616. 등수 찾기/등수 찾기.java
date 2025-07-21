import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		g1 = new Graph(N);
		g2 = new Graph(N);
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			g1.link(from, to);
			g2.link(to,  from);
		}
		
		
		System.out.println((g2.bfs(X) + 1)+" "+(N - g1.bfs(X)));
		
	}
	
	static Graph g1, g2;
	static int N,M,X;
	static StringTokenizer st;
	static class Graph{
		List<Integer> adj[];
		boolean visited[];
		int n,m;
		public Graph(int n) {
			this.n = n;
			adj = new List[n+1];
			for(int i = 1; i<=n;i++) {
				adj[i] = new ArrayList<>();
			}
			visited = new boolean[n+1];
			
		}
		
		public void link(int from, int to) {
			adj[from].add(to);
		}
		
		public int bfs(int x) {
			Queue<Integer> q = new ArrayDeque<>();
			visited[x] = true;
			q.add(x);
			int ret = 0;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int nxt : adj[cur]) {
					if(visited[nxt])
						continue;
					visited[nxt] = true;
					q.add(nxt);
					ret++;
				}
			}
			
			return ret;
		}
	}
}

