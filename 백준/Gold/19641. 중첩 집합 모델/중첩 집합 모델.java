import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			while(true) {
				int value = Integer.parseInt(st.nextToken());
				if(value == -1)
					break;
				adj[idx].add(value);
			}
			
			Collections.sort(adj[idx]);
		}
		
		root = Integer.parseInt(br.readLine());
		tree = new int[n+1][2];
		
		solve(root, 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=n; i++) {
			sb.append(i).append(' ')
			  .append(tree[i][0])
			  .append(' ')
			  .append(tree[i][1])
			  .append('\n');
		}
		System.out.println(sb);
	}
	
	static int solve(int cur, int num) {
		tree[cur][0] = num;
		
		for(int nxt : adj[cur]) {
			if(tree[nxt][0] != 0)
				continue;
			num = solve(nxt, num+1);
		}
		tree[cur][1] = num + 1;
		return num + 1;
	}
	
	static int n;
	static int tree[][];
	static ArrayList<Integer> adj[];
	static StringTokenizer st;
	static int root;
}