import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new long[N+1][N+1];
		
		uf = new int[N+1];
		for(int y= 1; y<=N; y++) {
			uf[y] = y;
			st = new StringTokenizer(br.readLine());
			for(int x= 1; x<=N; x++) {
				adj[y][x] = Long.parseLong(st.nextToken());
				
				if(adj[y][x] != 0) {
					edges.add(new Edge(y,x,adj[y][x]));
				}
			}
		}
		
		Collections.sort(edges);
		int cnt = 0;
		long ans = 0;
		for(Edge edge : edges) {
			if(union(edge.u, edge.v)) {
				ans += edge.cost;
				cnt ++;
				
				if(cnt == N-1)
					break;
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int x) {
		if(uf[x] == x)
			return x;
		
		return uf[x] = find(uf[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return false;
		
		uf[y] = x;
		return true;
	}
	
	static int uf[];
	static List<Edge> edges = new ArrayList<>();
	static StringTokenizer st;
	static int N;
	static long adj[][];
	static class Edge implements Comparable<Edge>{
		int u,v;
		long cost;
		public Edge(int u, int v, long cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		
		public String toString() {
			return String.format("%d %d %d", u,v,cost);
		}
	}
}

