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
		
//		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i<=n; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
		}
		long arr[] = new long[n+1];
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int nxt : adj[cur]) {
				inDegree[nxt]--;
				arr[nxt] = arr[cur] + 1;
				if(inDegree[nxt] == 0) {
					q.add(nxt);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=n;i++)
			sb.append(arr[i]+1).append(' ');
		
		System.out.println(sb);
	}
}