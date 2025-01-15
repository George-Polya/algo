import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        for(int i = 1;i <=n; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i <m ;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	adj[from].add(new Edge(to, cost));
        	adj[to].add(new Edge(from, cost));
        }
    
        
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        costs = new int[n+1];
        Arrays.fill(costs, INF);
        costs[1] = 0;
        pq.add(new Tuple(1,costs[1]));
        ArrayList<int[]> list = new ArrayList<>();
        int prev[] = new int[n+1];
        while(!pq.isEmpty()) {
        	Tuple cur = pq.poll();
        	
        	if(costs[cur.idx] < cur.cost)
        		continue;
        	for(Edge nxt : adj[cur.idx]) {
        		if(costs[nxt.idx] > costs[cur.idx] + nxt.cost) {
        			costs[nxt.idx] = costs[cur.idx] + nxt.cost;
        			prev[nxt.idx] = cur.idx;
        			pq.add(new Tuple(nxt.idx, costs[nxt.idx]));
        		}
        	}
        }
        
        int cnt= 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i<=n; i++) {
        	if(prev[i] == 0)
        		continue;
        	cnt++;
        	sb.append(i+" "+prev[i]+"\n");
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
    
    static int INF = Integer.MAX_VALUE / 2;
    static int costs[];
    
    static class Tuple implements Comparable<Tuple>{
    	int idx, cost;
    	public Tuple(int idx, int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Tuple o) {
    		return cost  - o.cost;
    	}
    }
    
    static int n,m;
    static StringTokenizer st;
    static class Edge{
    	int idx, cost;
    	public Edge(int idx, int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    }
    static ArrayList<Edge> adj[];
}