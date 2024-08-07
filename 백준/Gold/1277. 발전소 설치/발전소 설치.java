import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int id;
		double cost;
		public Node(int id, double cost) {
			this.id = id;
			this.cost = cost;
		}
		
		public int compareTo(Node node) {
			return Double.compare(cost, node.cost);
		}
		
		public String toString() {
			return String.format("%d %.3f", id,cost);
		}
	}
	
	static StringTokenizer st;
	static int n,w;
	static double m;
	static class Pair{
		double x,y;
		public Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static Pair pairs[];
	static List<Node> adj[];
	
	static double calCost(Pair pair1, Pair pair2) {
		double x1 = pair1.x;
		double y1 = pair1.y;
		
		double x2 = pair2.x;
		double y2 = pair2.y;
		
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
	}
	
	static double costs[][];
	static double INF = Double.MAX_VALUE;
	
	static String printDist(double dist[]) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for(int i = 1; i<=n; i++) {
			sb.append(i).append(' ');
			sb.append(dist[i] == INF ? "INF" : String.format("%.3f", dist[i])).append('|');
		}
		sb.append(']');
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		m = Double.parseDouble(br.readLine());
		
		pairs = new Pair[n+1];
		for(int i =1; i <=n ;i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			pairs[i] = new Pair(x,y);
		}
		
		adj = new List[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		costs = new double[n+1][n+1];
		for(int i = 1; i<=n; i++) {
			Arrays.fill(costs[i], INF);
		}
		for(int from =1; from<=n; from++) {
			for(int to = 1; to<=n; to++) {
				if(from == to)
					continue;
				double cost = calCost(pairs[from], pairs[to]);
				costs[from][to] = cost;
				costs[to][from] = cost;
			}
		}
		
		for(int i = 0; i <w ;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			costs[from][to] = 0;
			costs[to][from] = 0;
		}
		
		
		double dist[] = new double[n+1];
		Arrays.fill(dist, INF);

		dist[1] = 0;
		boolean visited[] = new boolean[n+1];
		
		for(int i =1; i<=n; i++) {
			int minIndex = -1;
			
			for(int j = 1; j<=n; j++) {
				if(visited[j])
					continue;
				
				if(minIndex == -1 || dist[minIndex] > dist[j])
					minIndex = j;
			}
			
			visited[minIndex] = true;
			
			for(int j = 1; j<=n; j++) {
				if(costs[minIndex][j] == INF || costs[minIndex][j] > m)
					continue;
				dist[j] = Math.min(dist[j], dist[minIndex] + costs[minIndex][j]);
			}
			
		}
		
		
//		PriorityQueue<Node> pq = new PriorityQueue<>();
//		pq.add(new Node(1,dist[1]));
//		for(int from =1; from<=n; from++) {
//			for(int to = 1; to<=n; to++) {
//				if(from == to)
//					continue;
//				adj[from].add(new Node(to, costs[from][to]));
//			}
//		}
//		
//		while(!pq.isEmpty()) {
//			Node cur = pq.poll();
//			
//			if(dist[cur.id] < cur.cost)
//				continue;
//			
//			for(Node nxt : adj[cur.id]) {
//				if(nxt.cost <= m && dist[nxt.id] > dist[cur.id] + nxt.cost) {
//					dist[nxt.id] = dist[cur.id] + nxt.cost;
//					pq.add(new Node(nxt.id, dist[nxt.id]));
//				}
//			}
//		}
		
		System.out.println((long)(dist[n] * 1000));
		
	}
}