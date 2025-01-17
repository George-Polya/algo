import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[v+1];
        for(int i = 0 ; i <=v; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m ;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	adj[from].add(new Edge(to, cost));
        	adj[to].add(new Edge(from, cost));
        }
        st = new StringTokenizer(br.readLine());
        J = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        
        dists1 = new int[v+1];
        dists2 = new int[v+1];
        for(int i = 0;i<=v;i++) {
        	dists1[i] = dists2[i] = INF;
        }
        
        dijkstra(J, dists1);
        dijkstra(S, dists2);
        
//        System.out.println(Arrays.toString(dists1));
//        System.out.println(Arrays.toString(dists2));
        
        WORST = new State();
        int min = INF;
        for(int i =1; i<=v; i++) {
        	if(i == J || i == S)
        		continue;
        	min = Math.min(min, dists1[i] + dists2[i]);
        }
        State ans = WORST;
        for(int i = 1; i<=v; i++) {
        	if(i == J || i == S)
        		continue;
        	int sum = dists1[i] + dists2[i];
        	if(min == sum && dists1[i] <= dists2[i]) {
        		State state = new State(i);
        		if(ans.compareTo(state) > 0)
        			ans = state;
        	}
        }
        System.out.println(ans.idx == v+1 ? -1 : ans.idx);
        
        
    }
    
    static class State implements Comparable<State>{
    	int idx, dist1, dist2, sum;
    	
    	public State(int idx) {
    		this.idx = idx;
    		this.dist1 = dists1[idx];
    		this.dist2 = dists2[idx];
    		this.sum = this.dist1 + this.dist2;
    	}
    	
    	public State() {
    		this.idx = v + 1;
    		this.dist1 = INF;
    		this.sum = INF;
    	}
    	
    	public boolean check() {
    		return dist1 > dist2;
    	}
    	
    	public int compareTo(State o) {
    		if(sum != o.sum)
    			return sum - o.sum;
    		if(dist1 != o.dist1)
    			return dist1 - o.dist1;
    		return idx - o.idx;
    	}
    	
    	public String toString() {
    		return idx+" ";
    	}
    	
    }
    
    static State WORST;
    static void dijkstra(int start,int dist[]) {
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	dist[start] = 0;
    	pq.add(new Edge(start, dist[start]));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(dist[cur.idx] < cur.cost)
    			continue;
    		
    		for(Edge nxt : adj[cur.idx]) {
    			if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
    				dist[nxt.idx] = dist[cur.idx] + nxt.cost;
    				pq.add(new Edge(nxt.idx, dist[nxt.idx]));
    			}
    		}
    	}
    }
    
    static int INF = Integer.MAX_VALUE / 2;
    static int dists1[], dists2[];
    
    
    static StringTokenizer st;
    static int v,m,J,S;
    static class Edge implements Comparable<Edge>{
    	int idx, cost;
    	public Edge(int idx ,int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    }
    static ArrayList<Edge> adj[];
}