import java.util.*;
import java.io.*;
public class Main {
	static int n,w;
	static List<Integer> adj[];
	static StringTokenizer st;
	static int leaf[];
	static int cnt;
	static void solve(int cur, int parent) {
		if(adj[cur].size() == 1 && adj[cur].get(0) == parent) {
//			leaf[cur] = 1;
			cnt++;
			return;
		}
		
		for(int nxt : adj[cur]) {
			if(nxt == parent)
				continue;
			solve(nxt, cur);
//			leaf[cur] += leaf[nxt];
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		adj = new List[n+1];
		for(int i = 1;i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		leaf = new int[n+1];
		for(int i = 0; i <n-1;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		
		
//		solve(1,-1);
//		System.out.println(leaf[1]);
		
		for(int i =2; i<=n; i++) {
			if(adj[i].size() == 1)
				cnt++;
		}
		
		System.out.printf("%.10f\n", (double)w / cnt);
	}
}