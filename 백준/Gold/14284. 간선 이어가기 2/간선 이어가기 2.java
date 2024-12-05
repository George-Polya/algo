import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	adj = new ArrayList[n+1];
    	dist = new int[n+1];
    	for(int i = 1; i<=n; i++) {
    		adj[i] = new ArrayList<>();
    		dist[i] = INF;
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		
    		adj[a].add(new Node(b,cost));
    		adj[b].add(new Node(a,cost));
    	}
    	st = new StringTokenizer(br.readLine());
    	s = Integer.parseInt(st.nextToken());
    	t = Integer.parseInt(st.nextToken());
    	
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.add(new Node(s,0));
    	dist[s] = 0;
    	int ans = 0;
    	while(!pq.isEmpty()) {
//    		System.out.println("----");
//    		System.out.println("pq: "+pq);
    		Node cur = pq.poll();
//    		System.out.println("cur: "+cur);
    		if(dist[cur.idx] < cur.cost)
    			continue;
    		
//    		ans += cur.cost;
//    		if()
    		
    		for(Node nxt : adj[cur.idx]) {
    			if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
    				dist[nxt.idx] = dist[cur.idx] + nxt.cost;
    				pq.add(new Node(nxt.idx, dist[nxt.idx]));
    			}
    		}
    	}
    	
    	System.out.println(dist[t]);
    }
    static int INF = Integer.MAX_VALUE / 2;
    static int dist[];
    static StringTokenizer st;
    static int n,m, s,t;
    static class Node implements Comparable<Node>{
    	int idx, cost;
    	public Node(int idx, int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Node o) {
    		return Integer.compare(cost, o.cost);
    	}
    	
    	public String toString() {
    		return idx+" "+cost;
    	}
    }
    
    static ArrayList<Node> adj[];
}