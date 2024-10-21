import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        trace = new int[n+1][n+1];
        
        adj = new ArrayList[n+1];
        for(int i = 1; i<=n; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        
        for(int i = 0; i < m ;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	adj[from].add(new Edge(to, cost));
        	adj[to].add(new Edge(from,cost));
        }
        
        
        for(int i = 1; i<=n; i++) {
        	solve(i);
        }
        
        
        StringBuilder sb= new StringBuilder();
        for(int y=1; y<=n; y++) {
        	for(int x=1; x<=n; x++) {
        		sb.append(y == x ? "-" : trace[y][x]).append(' ');
        	}
        	sb.append('\n');
        }
        System.out.println(sb);
        
    }
    
    static void solve(int idx) {
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	int dist[] = new int[n+1];
    	Arrays.fill(dist, INF);
    	dist[idx] = 0;
    	pq.add(new Edge(idx, dist[idx]));
    	
    	int prev[] = new int[n+1];
    	
    	
    	while(!pq.isEmpty()) {
//    		System.out.printf("pq: %s, dist: %s\n", pq, Arrays.toString(dist));
    		Edge cur = pq.poll();
    		
    		if(dist[cur.idx] < cur.cost)
    			continue;
    		
    		for(Edge nxt : adj[cur.idx]) {
    			if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
    				dist[nxt.idx] = dist[cur.idx] + nxt.cost;
    				prev[nxt.idx] = cur.idx;
    				pq.add(new Edge(nxt.idx, dist[nxt.idx]));
    			}
    		}
    	}
//    	System.out.println(Arrays.toString(prev));
    	
    	
    	for(int i = 1; i<=n; i++) {
    		if(idx == i)
    			continue;
    		int prv = i;
    		while(prev[prv] != idx) {
    			prv = prev[prv];
    		}
    		trace[idx][i] = prv;
    	}
    	
    }
    
    static ArrayList<Edge> adj[];
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
    
    static boolean visited[];
    static int INF = Integer.MAX_VALUE;
    static void printBoard(int board[][]) {
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=n; x++) {
    			if(board[y][x] == INF)
    				System.out.print("-\t");
    			else
    				System.out.print(board[y][x]+"\t");
    		}
    		System.out.println();
    	}
    }
    static StringTokenizer st;
    static int n,m;
    static int trace[][];
}