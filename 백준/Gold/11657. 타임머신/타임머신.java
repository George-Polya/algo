import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static StringTokenizer st;
	static long dist[];
	static long INF = Long.MAX_VALUE;
	
	static class Edge{
		int from, to, cost;
		
		public Edge(int from,int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			
		}
	}
	
	static void print() {
		for(int i = 1; i<=n; i++) {
			System.out.print( (dist[i] == INF ? "INF" : dist[i]) +" ");
		}
		System.out.println();
	}
	
	static Edge edges[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edges = new Edge[m];
		for(int i = 0; i <m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, cost);
		}
		
		dist = new long[n+1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		for(int i = 1; i<=n; i++) {
//			System.out.println("-----");
//			System.out.println("i: "+i);
			for(Edge edge : edges) {
				if(dist[edge.from] == INF)
					continue;
				if(dist[edge.to] > dist[edge.from] + edge.cost) {
					dist[edge.to] = dist[edge.from] + edge.cost;
					
					if(i == n) {
						System.out.println(-1);
						System.exit(0);
					}
				}
//				print();
			}
		}
		
		for(int i = 2; i<=n; i++) {
			if(dist[i] == INF) {
				System.out.println(-1);
			}else {
				System.out.println(dist[i]);
			}
		}
	}
}