import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		uf = new int[N+1];
		for(int i = 0; i <=N; i++) {
			uf[i] = i;
		}
		installed = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int idx = Integer.parseInt(st.nextToken());
			installed[idx] = true;
		}
		
		edges = new Edge[M];
		for(int i = 0; i <M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u,v,cost);
		}
		
		Arrays.sort(edges);
		
		int ans = 0;
		for(Edge edge : edges) {
			int A = find(edge.u);
			int B = find(edge.v);
			
			if(A == B)
				continue;
			
			if(installed[A] && installed[B])
				continue;
			
			if(!installed[A])
				uf[A] = B;
			else
				uf[B] = A;
			
			ans += edge.cost;
			
				
					
		}
		
		System.out.println(ans);
	}
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static StringTokenizer st;
	static boolean installed[];
	static int N,M,K;
	static int uf[];
	static Edge edges[];
	static class Edge implements Comparable<Edge>{
		int u,v,cost;
		public Edge(int u,int v,int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
}