import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        adj = new List[n+1];
        for(int i =1; i<=n; i++) {
        	adj[i] = new ArrayList<>();
        }
        inDegree = new int[n+1];
        outDegree = new int[n+1];
        for(int i = 0; i < m ;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	adj[from].add(new Edge(to, cost));
        	inDegree[to]++;
        	outDegree[from]++;
        }
        
        
        Queue<Integer> q = new ArrayDeque<>();
        counts = new int[n+1];
        q.add(n);
        counts[n] = 1;
        
        
        while(!q.isEmpty()) {
//        	System.out.println("----");
//        	System.out.println(q);
//        	System.out.println(Arrays.toString(counts));
        	int cur = q.poll();
        	
        	for(Edge nxt : adj[cur]) {
        		inDegree[nxt.id]--;
        		counts[nxt.id] += nxt.cost * counts[cur];
        		if(inDegree[nxt.id] == 0) {
        			q.add(nxt.id);
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=n; i++) {
        	if(outDegree[i] == 0) {
        		sb.append(i).append(' ').append(counts[i]).append('\n');
        	}
        }
        System.out.println(sb);
        
    }
    static int n,m;
    static int inDegree[], outDegree[];
    static StringTokenizer st;
    static int counts[];
    static List<Edge> adj[];
    static class Edge{
    	int id, cost;
    	public Edge(int id, int cost) {
    		this.id = id;
    		this.cost = cost;
    	}
    	
    	public String toString() {
    		return id+" "+cost;
    	}
    }
}