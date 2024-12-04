import java.io.*;
import java.util.*;
// https://sh1mj1-log.tistory.com/138
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	n = Integer.parseInt(br.readLine());
    	
    	adj = new ArrayList[n];
    	for(int i = 0; i < n ;i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	visited = new boolean[n];
    	dist = new long[n];
    	
    	long lcm = 1;
    	
    	for(int i = 0; i < n - 1 ;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int p = Integer.parseInt(st.nextToken());
    		int q = Integer.parseInt(st.nextToken());
    		
    		adj[a].add(new Node(b, p,q));
    		adj[b].add(new Node(a, q,p));
    		
    		lcm *= ( p * q / gcd(p,q));
    	}
    	
    	dist[0] = lcm;
    	
    	dfs(0);
    	
    	long mgcd = dist[0];
    	
    	for(int i = 1; i < n ;i++) {
    		mgcd = gcd(mgcd, dist[i]);
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0; i < n ;i++) {
    		sb.append(dist[i] / mgcd).append(' ');
    	}
    	System.out.println(sb);
    }
    
    static void dfs(int cur) {
    	visited[cur] = true;
    	for(Node nxt : adj[cur]) {
    		if(visited[nxt.idx])
    			continue;
    		dist[nxt.idx] = dist[cur] * nxt.q / nxt.p;
    		dfs(nxt.idx);
    	}
    }
    
    static long gcd(long a, long b) {
    	if(b == 0)
    		return a;
    	return gcd(b, a % b);
    }
    static boolean visited[];
    static long dist[];
    static ArrayList<Node> adj[];
    static int n;
    static StringTokenizer st;
    static class Node{
    	int idx, p,q;
    	public Node(int idx,int p, int q) {
    		this.idx = idx;
    		this.p = p;
    		this.q = q;
    	}
    }
}