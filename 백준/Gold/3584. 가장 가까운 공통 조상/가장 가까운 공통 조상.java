import java.io.*;
import java.util.*;

public class Main{
	static int n,q;
	static List<Integer> adj[];
	static StringTokenizer st;
	static boolean visited[];
	static int depth[], parents[];
	static int  MAX_H = 17;
	
	static void dfs(int cur) {
		for(int nxt : adj[cur]) {
			if(visited[nxt])
				continue;
			visited[nxt] = true;
			depth[nxt] = depth[cur] + 1;
			dfs(nxt);
		}
	}
	
	static int findRoot() {
		int root = -1;
		for(int i = 1; i<=n; i++) {
			if(parents[i] == 0) {
				root = i;
				break;
			}
		}
		return root;
	}
	
	
	static int findLCA(int a,int b) {
		int root = findRoot();
		visited = new boolean[n+1];
		depth = new int[n+1];
		visited[root] = true;
		dfs(root);
//		System.out.println(Arrays.toString(depth));
		
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
	}
	

	static int T;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			adj = new List[n+1];
			for(int i = 1; i<=n; i++)
				adj[i] = new ArrayList<>();
			parents = new int[n+1];
			for(int i = 0; i<n-1;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());		
				adj[from].add(to);
				parents[to] = from;
//				adj[to].add(from);
			}
//			System.out.println(Arrays.toString(parents));
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(findLCA(a,b)).append('\n');
			
		}
		System.out.println(sb);
	}
}