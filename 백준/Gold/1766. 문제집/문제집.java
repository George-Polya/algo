import java.util.*;
import java.io.*;
public class Main {
	static int n,m;
	static StringTokenizer st;
	static List<Integer> adj[];
	static int inDegree[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		inDegree = new int[n+1];
		
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			inDegree[b]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i<=n; i++) {
			if(inDegree[i] == 0) {
				pq.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(' ');
			
			for(int nxt : adj[cur]) {
				inDegree[nxt]--;
				
				if(inDegree[nxt] == 0) {
					pq.add(nxt);
				}
			}
		}
		System.out.println(sb);
	}
}