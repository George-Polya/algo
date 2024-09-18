import java.io.*;
import java.util.*;

public class Main {
	static int n,m,x;
	static StringTokenizer st;
	static class Node implements Comparable<Node>{
		int id, cost;
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
		
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}
	
	static int INF = Integer.MAX_VALUE;
	static class Dijkstra{
		int dist[];
		List<Node> adj[];
		
		public Dijkstra() {
			dist = new int[n+1];
			adj = new List[n+1];
			for(int i = 1; i<=n;i++ ) {
				dist[i] = INF;
				adj[i] = new ArrayList<>();
			}
		}
		
		public void dijkstra() {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			dist[x] = 0;
			pq.add(new Node(x, dist[x]));
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(dist[cur.id] < cur.cost)
					continue;
				
				for(Node nxt : adj[cur.id]) {
					if(dist[nxt.id] > dist[cur.id] + nxt.cost) {
						dist[nxt.id] = dist[cur.id] + nxt.cost;
						pq.add(new Node(nxt.id, dist[nxt.id]));
					}
				}
			}
			
		}
	}
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        Dijkstra inverse = new Dijkstra();
        Dijkstra direct = new Dijkstra();
        
        
        for(int i = 0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	direct.adj[from].add(new Node(to, cost));
        	inverse.adj[to].add(new Node(from, cost));
        }
        
        inverse.dijkstra();
        direct.dijkstra();
        
//        System.out.println(Arrays.toString(inverse.dist));
//        System.out.println(Arrays.toString(direct.dist));
        
        int ans = -1;
        for(int i = 1; i<=n; i++) {
        	ans = Math.max(ans,  inverse.dist[i] + direct.dist[i]);
        }
        System.out.println(ans);
        
   }
}