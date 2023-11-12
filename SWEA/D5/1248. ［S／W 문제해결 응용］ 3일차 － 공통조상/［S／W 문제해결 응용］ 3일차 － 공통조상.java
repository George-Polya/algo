import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int V,E, a,b;
	static StringTokenizer st;
	static int parents[][];
	
	static int depth[];
	
	static int dfs(int cur) {
		int ret = 1;
		for(int nxt : adj[cur]) {
			if(visited[nxt])
				continue;
			depth[nxt] = depth[cur] + 1;
			parents[0][nxt] = cur;
			visited[nxt] = true;
			ret += dfs(nxt);
		}
		return ret;
	}
	
	static int LCA(int a,int b) {
		if(depth[a] < depth[b])
			return LCA(b,a);
		
		for(int h = MAX_H; h>=0; h--) {
			if(depth[a] - depth[b] >= (1<<h))
				a = parents[h][a];
		}
		
		if(a == b)
			return a;
		
		for(int h = MAX_H;h>=0;h--) {
			if(parents[h][a] != parents[h][b]) {
				a = parents[h][a];
				b = parents[h][b];
			}
		}
		
		return parents[0][a];
	}
	static ArrayList<Integer> adj[];
	static int MAX_H = 16;
	static boolean visited[];
	
	static void initialize() {
		parents = new int[MAX_H+1][V+1];
		depth = new int[V+1];
		visited = new boolean[V+1];
		visited[1] = true;
		dfs(1);
		
		for(int h = 1; h<=MAX_H; h++) {
			for(int i = 1; i<=V;i++)
				parents[h][i] = parents[h-1][parents[h-1][i]];
		}
	}
	public static void main(String[] args) throws IOException{
		//System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			adj = new ArrayList[V+1];
			for(int i = 1; i<=V;i++) {
				adj[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				adj[p].add(child);
				adj[child].add(p);
			}
			
			initialize();
			
			sb.append('#').append(tc).append(' ');
			
			int lca = LCA(a,b);
//			System.out.println(lca);
			Arrays.fill(visited, false);
			int parent = parents[0][lca];
			visited[parent] = true;
			int size = dfs(lca) - 1;
			sb.append(lca).append(' ').append(size).append('\n');
		}
		
		System.out.println(sb);
	}
}