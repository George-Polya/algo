import java.io.*;
import java.util.*;
public class Solution {

	static int T;
	static int v,e,a,b;
	static StringTokenizer st;
	static int MAX_V = 10000;
	static List<Integer> adj[] = new List[MAX_V+5];
//	static int LCA(int a, int b) {
//		
//	}
	
	static int parent[] = new int[MAX_V+5];
	static int depth[] = new int[MAX_V+5];
	static void dfs(int cur) {
		for(int nxt : adj[cur]) {
			if(nxt == parent[cur])
				continue;
			parent[nxt] = cur;
			depth[nxt] = depth[cur] + 1;
			dfs(nxt);
		}
	}
	
	static int LCA(int a, int b) {
		while(depth[a] != depth[b]) {
			if(depth[a] < depth[b])
				b = parent[b];
			else if(depth[a] > depth[b])
				a = parent[a];
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}
	
	static int getSubtreeSize(int cur) {
		int ret = 1;
		
		for(int nxt : adj[cur]) {
			if(nxt == parent[cur])
				continue;
			ret += getSubtreeSize(nxt);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			for(int i =1;i<=v;i++) {
				adj[i] = new ArrayList<>();
			}
			
			parent[1] = -1;
			depth[1] = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i <e ; i++) {
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				adj[c].add(d);
				adj[d].add(c);
			}
			
			dfs(1);
			
			int lca = LCA(a,b);
			int cnt = getSubtreeSize(lca);
//			int cnt = 0;
			
			sb.append('#').append(tc).append(' ').append(lca).append(' ').append(cnt).append('\n');
			
		}
		System.out.println(sb);
	}
	
}