import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static List<Integer> adj[];
	static StringTokenizer st;
	static boolean visited[];
	static int ans;
	static void dfs(int cur, int depth) {
		for(int nxt : adj[cur]) {
			if(visited[nxt])
				continue;
			visited[nxt] = true;
			dfs(nxt, depth + 1);
		}
		
		if(cur != 1 && adj[cur].size() == 1) {
			ans += depth;
		}
		
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new List[n+1];
        for(int i = 1; i<=n; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i <n - 1;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	adj[a].add(b);
        	adj[b].add(a);
        }
        
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1, 0);
        System.out.println(ans % 2 == 0? "No" : "Yes");
    }
}