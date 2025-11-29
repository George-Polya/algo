import java.util.*;
import java.io.*;
 class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		
		for(int i = 0; i <=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
//			edges[i] = new Edge(a,b, 1-c);
			edges.add(new Edge(a,b,1-c));
		}
		
		System.out.println(Math.abs(MST(true) - MST(false)));
	}
	
	static int uf[];
	static int find(int x) {
		if(x == uf[x])
			return x;
		
		return uf[x] = find(uf[x]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b)
			return false;
		uf[a] = b;
		return true;
	}
	static int MST(boolean reverse) {
		uf = new int[N+1];
		for(int i = 0; i <=N;i++) {
			uf[i] = i;
		}
		
		if(reverse) {
			Collections.sort(edges, Collections.reverseOrder());
		}else {
			Collections.sort(edges);
		}
		
		int cnt = 0;
		int k = 0;
		
		for(Edge e : edges) {
			if(union(e.a, e.b)) {
				cnt++;
				k += e.cost;
			}
			
			if(cnt == N)
				break;
		}
		
		return k * k;
	}
	
	static int N,M;
	static ArrayList<Edge> edges;
	static StringTokenizer st;
	static class Edge implements Comparable<Edge>{
		int a,b,cost;
		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			return  o.cost - cost;
		}
	}
 }
