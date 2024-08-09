import java.io.*;
import java.util.*;


public class Main {
	static int n,m;
	static long k;
	static StringTokenizer st;
	static int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int u,v,cost;
		public Edge(int u, int v, int cost) {
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
	
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean construction[];
	
	static int uf[];
	
	static int find(int x) {
		if(x == uf[x])
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
		k = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		uf = new int[n+1];
		for(int id = 1; id<=n; id++) {
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Edge(0, id, cost));
			uf[id] = id;
		}
		
		for(int i = 1; i<n; i++) {
			pq.add(new Edge(i,i+1,0));
		}
		
		pq.add(new Edge(n,1,0));
		
		int temp[] = new int[2];
		construction = new boolean[n+1];
		
		for(int i = 0; i < m;i++) {
			st = new StringTokenizer(br.readLine());
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			Arrays.sort(temp);
			
			if(temp[0] == 1 && temp[1] == n)
				construction[n] = true;
			else
				construction[temp[0]] = true;
		}
		
		if(m < 2) {
			System.out.println("YES");
			return;
		}
		
		long sum = 0;
		int size = 0;
		while(!pq.isEmpty()) {
//			System.out.println("-----");
//			System.out.println("pq: "+pq);
			Edge cur = pq.poll();
			int u = cur.u;
			int v = cur.v;
			int cost = cur.cost;
			
			if(construction[u])
				continue;
			
			if(union(u, v)) {
//				System.out.println("cur: "+cur);
				sum += cost;
				size++;
				
				if(size == n) {
					
//					System.out.println(sum);
					break;
				}
			}
			
		}
		
		System.out.println(sum <=k ? "YES" : "NO");
		
		
	}
}