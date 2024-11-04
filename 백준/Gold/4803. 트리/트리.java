import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		while(true) {
//			System.out.println("----");
			tc++;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n == 0 && m == 0)
				break;
//			System.out.printf("n: %d, m: %d\n", n,m);
			sb.append("Case ").append(tc).append(": ");
			
			
			adj = new ArrayList[n+1];
			for(int i = 1; i<=n; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < m ;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a].add(b);
				adj[b].add(a);
			}
			
			
			visited = new boolean[n+1];
			int cnt = 0;
			for(int i = 1; i<=n; i++) {
				if(visited[i])
					continue;
				cnt += bfs(i);
			}
			
			
//			System.out.println("cnt: "+cnt);
			if(cnt == 0) {
				sb.append("No trees.\n");
			}else if(cnt > 1) {
				sb.append(String.format("A forest of %d trees.\n", cnt));
			}else if(cnt == 1) {
				sb.append("There is one tree.\n");
			}
			
		}
		System.out.println(sb);
	}
	
	static int bfs(int root) {
		Queue<Integer> q = new ArrayDeque<>();
		int edgeCnt = 0;
		int nodeCnt = 0;
		visited[root] = true;
		q.add(root);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			nodeCnt++;
			
			for(int nxt : adj[cur]) {
				edgeCnt++;
				if(visited[nxt])
					continue;
				visited[nxt] = true;
				q.add(nxt);
			}
		}
//		System.out.printf("edgeCnt: %d, nodeCnt: %d\n", edgeCnt, nodeCnt);
		
		return edgeCnt / 2 == nodeCnt - 1 ? 1 : 0; 
		
	}
	
	static ArrayList<Integer> adj[];
	
	static boolean visited[];
	static StringTokenizer st;
	static int n,m;
	
}