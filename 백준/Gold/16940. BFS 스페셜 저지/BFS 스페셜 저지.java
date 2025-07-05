import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sequence = new int[N+1];
		order = new int[N+1];
		adj = new List[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1;i <=N;i++) {
			order[sequence[i]] = i;
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adj[i], (a,b)->{
				return order[a] - order[b];
			});
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N+1];
		q.add(1);
		visited[1] = true;
		result = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);
			
			for(int nxt : adj[cur]) {
				if(visited[nxt])
					continue;
				visited[nxt] = true;
				q.add(nxt);
			}
		}
		
		System.out.println(check() ? 1 : 0);
	}  
	static boolean check() {
		for(int i = 1; i<=N; i++) {
			if(sequence[i] != result.get(i-1))
				return false;
		}
		return true;
	}
	static List<Integer> result;
	static boolean visited[];
	static StringTokenizer st;
	static int N;
	static int sequence[], order[];
	static List<Integer> adj[];
}