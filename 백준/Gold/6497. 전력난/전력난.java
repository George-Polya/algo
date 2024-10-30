import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	while(true) {
    		st = new StringTokenizer(br.readLine());
        	m = Integer.parseInt(st.nextToken());
        	n = Integer.parseInt(st.nextToken());
        	
        	if(m == 0 && n == 0)
        		break;
        	
        	int total = 0;
        	
        	adj = new ArrayList[m];
        	for(int i = 0; i <m;i++) {
        		adj[i] = new ArrayList<>();
        	}
        	
        	for(int i = 0; i < n ;i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		if(from == 0 && to == 0)
        			break;
        		int cost = Integer.parseInt(st.nextToken());
        		total += cost;
        		
        		adj[from].add(new Edge(to, cost));
        		adj[to].add(new Edge(from, cost));
        		
        	}
//        	System.out.println(total);
        	visited = new boolean[m];
        	PriorityQueue<Edge> pq = new PriorityQueue<>();
        	pq.add(new Edge(0,0));
        	int cnt = 0;
        	while(!pq.isEmpty()) {
//        		System.out.println("-----");
//        		System.out.println(pq);
        		Edge cur = pq.poll();
//        		System.out.println(cur);
        		if(visited[cur.idx])
        			continue;
        		visited[cur.idx] = true;
        		cnt++;
        		total -= cur.cost;
        		if(cnt == m)
        			break;
        		for(Edge nxt : adj[cur.idx]) {
        			if(visited[nxt.idx])
        				continue;
        			pq.add(nxt);
        		}
        	}
        	
//        	System.out.println(total);
        	sb.append(total).append('\n');
     
    	}
    	System.out.println(sb);
    	   	
    	
    }
    static boolean visited[];
    static ArrayList<Edge> adj[];
    static StringTokenizer st;
    static int m,n;
    static class Edge implements Comparable<Edge>{
    	int idx, cost;
    	public Edge(int idx,int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    	
    	public String toString() {
    		return idx+" "+cost;
    	}
    }
}