import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n,m;
	static char gender[];
	static class Edge implements Comparable<Edge>{
		int u, v, cost;
		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		
		public int compareTo(Edge e) {
			return cost - e.cost;
		}
		
		public String toString(){
			return u +" "+v+" "+cost;
		}
	}
	
	static Edge edges[];
	static int uf[];
	
	static int find(int x) {
		if( x == uf[x])
			return x;
		
		return uf[x] = find(uf[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b)
			return false;
		uf[b] = a;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		gender = new char[n+1];
		
		st = new StringTokenizer(br.readLine());
		uf = new int[n+1];
		for(int i = 1; i<=n ;i++) {
			gender[i] = st.nextToken().charAt(0);
			uf[i] = i;
		}
		
		edges = new Edge[m];
		
		for(int i = 0; i <m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u,v,cost);
		}
		
		Arrays.sort(edges);
		
//		for(int i = 0; i<m;i++) {
//			System.out.println(edges[i]);
//		}
		
		int size = 0;
		int sum = 0;
		
		for(int i = 0; i < m ;i++) {
			Edge e = edges[i];
			
			int u = e.u;
			int v = e.v;
			int cost = e.cost;
			if(gender[u] == gender[v])
				continue;
			if(union(u,v)) {
//				System.out.println(e);
				sum += cost;
				size++;
			}
			
			if(size == n - 1)
				break;
		}
//		System.out.println(sum);
		System.out.println(size == n-1 ? sum : -1);
	}
}