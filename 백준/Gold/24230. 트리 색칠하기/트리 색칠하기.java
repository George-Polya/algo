import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		target = new int[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		adj = new List[N+1];
		for(int i = 1; i<=N ;i++) {
			target[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}
		
		
		for(int i = 0; i < N - 1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		if(target[1] != 0)
			ans++;
		visited[1] = true;
		dfs(1, target[1]);
		System.out.println(ans);
	}
	
	static void dfs(int cur,int color) {
		if(target[cur] != color)
			ans++;
		
		for(int nxt : adj[cur]) {
			if(visited[nxt])
				continue;
			visited[nxt] = true;
			dfs(nxt, target[cur]);
		} 
	}
	
	static int ans;
	static int target[];
	static boolean visited[];
	static StringTokenizer st;
	static List<Integer> adj[];
	static int N;
}

