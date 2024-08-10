import java.io.*;
import java.util.*;


public class Main {
	static int adj[][];
	static class Edge implements Comparable<Edge>{
		int u, v,cost;
		
		public Edge(int u, int v,int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		public int compareTo(Edge e) {
			return cost - e.cost;
		}
		
		public String toString() {
			return u+" "+v+" "+cost;
		}
	}
	static int n;
	static StringTokenizer st;
	static List<Edge> edges = new ArrayList<>();
	static int uf[];
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static boolean union(int a,int b) {
		a = find(a);
		b = find(b);
		if(a == b)
			return false;
		
		uf[a] = b;
		return true;
	}
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				System.out.printf("%-3d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new int[n+1][n+1];
		
		int total = 0;
		uf = new int[n+1];
		for(int y=1; y<=n; y++) {
			String str = br.readLine();
			uf[y] = y;
			for(int x=1; x<=n; x++) {
				char ch = str.charAt(x-1);
				if('a'<=ch && ch <='z') {
					adj[y][x] = ch - 'a' + 1;
				}else if('A' <= ch && ch <='Z') {
					adj[y][x] = ch - 'A' + 27;
				}
				
				total += adj[y][x];
				if(y != x) { 
					if(adj[y][x] != 0)
						edges.add(new Edge(y,x, adj[y][x]));
				}
			}
		}
		
//		printBoard(adj);
		
		Collections.sort(edges);
		int sum = 0;
		int size = 0;
		
		for(Edge e : edges) {
			int u = e.u;
			int v = e.v;
			int cost = e.cost;
			
			if(union(u,v)) {
				sum += cost;
				size++;
				
				if(size == n-1) {
//					System.out.println(total - sum);
//					System.exit(0);
					break;
				}
			}
		}
		
		System.out.println(size == n - 1 ? total - sum : -1);
	}
}