import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		int f = -1;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
			for(int i = 1; i<=MAX_N;i++) {
				adj[i] = new ArrayList<>();
			}
			
			int max = -1;
			String str;
			while(!(str = br.readLine()).isEmpty()) {
				st = new StringTokenizer(str);
				if(!st.hasMoreTokens())
					break;
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				max = Math.max(max, from);
				max = Math.max(max, to);
				f = from;
				adj[from].add(new Edge(to, cost));
				adj[to].add(new Edge(from, cost));
			}
		}catch(Exception e) {
			
		}
		
		if(f == -1) {
			System.out.println(0);
			return;
		}
		
		
		costs = new int[MAX_N + 1];
		Arrays.fill(costs, -1);
		costs[1] = 0;
		dfs(1, -1);
//		System.out.println(Arrays.toString(costs));
		int idx = 0;
		int maxLen = 0;
		for(int i = 1; i<= MAX_N;i++) {
			if(maxLen < costs[i]) {
				maxLen = costs[i];
				idx = i;
			}
		}
		
		Arrays.fill(costs, -1);
		costs[idx] = 0;
		dfs(idx, -1);
		maxLen = 0;
		for(int i = 1; i<=MAX_N; i++) {
			maxLen = Math.max(maxLen, costs[i]);
		}
		System.out.println(maxLen);
	}	
	
	static void dfs(int cur, int parent) {
		
		for(Edge nxt : adj[cur]) {
			if(nxt.idx == parent)
				continue;
			
			costs[nxt.idx] = costs[cur] + nxt.cost;
			dfs(nxt.idx, cur);
		}
	}
	
	static StringTokenizer st;
	static int MAX_N = 10000;
	static int costs[];
	static ArrayList<Edge> adj[] = new ArrayList[MAX_N+1];
	static class Edge{
		int idx, cost;
		public Edge(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}