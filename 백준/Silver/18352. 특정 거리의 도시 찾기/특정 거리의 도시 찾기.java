import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	static int n,m,k,x;
	static StringTokenizer st;
	static List<Pair> adj[];
	static int INT_MAX = Integer.MAX_VALUE;
	static int dist[];
	
	static class Pair implements Comparable<Pair>{
		int node, cost;
		public Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
		public int compareTo(Pair p) {
			return cost - p.cost;
		}
		
		public String toString() {
			return node +" "+cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[n+1];
		dist = new int[n+1];
		Arrays.fill(dist, INT_MAX);
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Pair(b,1));
		}
		
		dist[x] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(x,dist[x]));
		
		
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			
			if(dist[cur.node] < cur.cost)
				continue;
			
			for(Pair nxt : adj[cur.node]) {
				if(dist[nxt.node] <= dist[cur.node] + nxt.cost)
					continue;
				
				dist[nxt.node] = dist[cur.node] + nxt.cost;
				pq.add(new Pair(nxt.node, dist[nxt.node]));
			}
		}
		
//		System.out.println(Arrays.toString(dist));
		
		boolean flag = false;
		for(int i = 1; i<=n; i++) {
			if(dist[i] == k) {
				flag = true;
				System.out.println(i);
			}
				
		}
		if(!flag)
			System.out.println(-1);
	}
}