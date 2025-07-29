import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		uf = new int[N+1];
		for(int i = 1; i<=N; i++) {
			uf[i] = i;
		}
		
		adj = new int[N+1][N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x,y);
		}
		
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) {
				int cost= Integer.parseInt(st.nextToken());
				if(y > 1 && x > y)
					edges.add(new Edge(y,x,cost));
			}
		}
		
		Collections.sort(edges);
		long total = 0;
		List<Edge> resultEdges = new ArrayList<>();
		
		for(Edge edge : edges) {
			if(find(edge.x) != find(edge.y)) {
				union(edge.x, edge.y);
				total += edge.cost;
				
				resultEdges.add(edge);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(total).append(' ').append(resultEdges.size()).append('\n');
		
		for(Edge e : resultEdges) {
			sb.append(e.x).append(' ').append(e.y).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if (x!= y)
			uf[y] = x;
	}
	
	static List<Edge> edges = new ArrayList<>();
	static class Edge implements Comparable<Edge>{
		int y,x,cost;
		public Edge(int y,int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
	static int uf[];
	static int N,M;
	static StringTokenizer st;
	static int adj[][];
}

