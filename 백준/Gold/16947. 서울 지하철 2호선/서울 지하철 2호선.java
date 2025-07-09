import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		adj = new List[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		parents = new int[N+1];
		visited[1] = true;
		dfs(1,0);
		cycled = new boolean[N+1];
		int cur = cycleEnd;
		while(cur != cycleStart) {
			cycled[cur] = true;
			cur = parents[cur];
		}
		cycled[cycleStart] = true;
		
		dist = new int[N+1];
		Arrays.fill(dist, -1);;
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i<=N; i++) {
			if(cycled[i]) {
				dist[i] = 0;
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int nxt : adj[cur]) {
				if(dist[nxt]!=-1)
					continue;
				dist[nxt] = dist[cur] + 1;
				q.add(nxt);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N; i++) {
			sb.append(dist[i]).append(' ');
		}
		System.out.println(sb);
	}
	static boolean cycled[];
	static int dist[];
	
	static void dfs(int cur, int prv) {
		
		for(int nxt: adj[cur]) {
			if(nxt == prv)
				continue;
			if(visited[nxt]) {
				cycleStart = nxt;
				cycleEnd = cur;
				return;
			}
			parents[nxt] = cur;
			visited[nxt] = true;
			dfs(nxt, cur);
			if(cycleStart != 0)
				return;
		}
	}
	
	static int N;
	static int cycleStart, cycleEnd;
	static int parents[];
	static boolean visited[];
	static StringTokenizer st;
	static List<Integer> adj[];
}