import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int V,E, a,b;
	static StringTokenizer st;
	static int parents[];
	
	static int depth[];
	
	static int dfs(int cur, int parent) {
		int ret = 1;
		for(int nxt : adj[cur]) {
			if(nxt == parent)
				continue;
			depth[nxt] = depth[cur] + 1;
			ret += dfs(nxt, cur);
		}
		return ret;
	}
	
	static int LCA(int a,int b) {
		dfs(1,-1);
		
		
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b])
				a = parents[a];
			else
				b = parents[b];
		}
		
		while(a != b) {
			a = parents[a];
			b = parents[b];
		}
		
		return a;
//		return dfs(1, -1);
	}
	static ArrayList<Integer> adj[];
	static int MAX_V = 10003;
	public static void main(String[] args) throws IOException{
		// System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			parents = new int[MAX_V+1];
			depth = new int[MAX_V+1];
			
			adj = new ArrayList[MAX_V+1];
			for(int i = 1; i<=MAX_V;i++) {
				adj[i] = new ArrayList<>();
			}
			
			parents[1] = -1;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				parents[child] = p;
				adj[p].add(child);
				adj[child].add(p);
			}
			
			sb.append('#').append(tc).append(' ');
			
			int lca = LCA(a,b);
//			System.out.println(lca);
			int size = dfs(lca, parents[lca]);
			sb.append(lca).append(' ').append(size).append('\n');
		}
		
		System.out.println(sb);
	}
}