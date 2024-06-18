import java.util.*;
import java.io.*;
public class Main {
	
	static int V,e;
	static class Edge implements Comparable<Edge>{
		int u,v;
		long cost;
		public Edge(int u,int v,long cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		
		public int compareTo(Edge e) {
			return Long.compare(cost,e.cost);
		}
	}
	static StringTokenizer st;
	static Edge edges[];
	static int uf[];
	
	static int find(int x) {
		if(uf[x] == x)
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static boolean union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return false;
		uf[x] = y;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		edges = new Edge[e];
		uf = new int[V+1];
		for(int i =1 ;i<=V;i++)
			uf[i] = i;
		for(int i = 0; i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			edges[i] = new Edge(a,b,cost);
		}
		
		Arrays.sort(edges);
		
		int sum = 0;
		int cnt = 0;
		for(int i = 0; i<e;i++) {
			int u = edges[i].u;
			int v = edges[i].v;
			
			u = find(u);
			v = find(v);
			
			if(u == v)
				continue;
			uf[v] = u;
			cnt++;
			sum += edges[i].cost;
			if(cnt == V-1)
				break;
			
		}
		System.out.println(sum);
	}
}