import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int parent[];
	static ArrayList<Integer> adj[];
	static int cnt = 1;
	static HashMap<Integer, Integer> id2node = new HashMap<>();
	static HashMap<Integer, Integer> node2id = new HashMap<>();
	static StringTokenizer st;
	
	static void dfs(int root) {
		Stack<Integer> stk = new Stack<>();
		parent[root] = -1;
		boolean visited[] = new boolean[n+1];
		visited[root] = true;
		stk.push(root);
		
		while(!stk.isEmpty()) {
			int cur = stk.pop();
			
			for(int nxt : adj[cur]) {
				if(visited[nxt])
					continue;
				visited[nxt] = true;
				parent[nxt] = cur;
				stk.push(nxt);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!id2node.containsKey(a)) {
				id2node.put(a, cnt);
				node2id.put(cnt, a);
				cnt++;
			}
			
			if(!id2node.containsKey(b)) {
				id2node.put(b, cnt);
				node2id.put(cnt, b);
				cnt++;
			}
			a = id2node.get(a);
			b = id2node.get(b);
			
			adj[a].add(b);
			adj[b].add(a);
		}
//		System.out.println(cnt);
//		System.out.println(map.keySet());
		
		parent = new int[n+1];
//		parent[1] = -1;
		
		dfs(id2node.get(1));
		
//		System.out.println(Arrays.toString(parent));
		
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i<=n;i++) {
			int node = id2node.get(i);
//			System.out.println(node);
			sb.append(node2id.get(parent[node])).append('\n');
		}
		System.out.println(sb);
		
	}
}