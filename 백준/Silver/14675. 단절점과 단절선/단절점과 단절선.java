import java.util.*;
import java.io.*;



public class Main {
	static int n,q;
	static StringTokenizer st;
	static List<Integer> adj[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new List[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i < n - 1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(t == 1) {
				sb.append(adj[k].size() == 1 ? "no" : "yes").append('\n');
			}else if(t == 2) {
				sb.append("yes").append('\n');
			}
		}
		System.out.println(sb);
		
	}
}