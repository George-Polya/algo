import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	adj = new ArrayList[N+1];
    	for(int i = 1; i<=N; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	S = Integer.parseInt(st.nextToken());
    	E = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		adj[x].add(y);
    		adj[y].add(x);
    	}
    	
    	dist = new int[N+1];
    	Arrays.fill(dist, -1);
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	dist[S] = 0;
    	q.add(S);
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		
    		for(int dir : new int[] {-1,1}) {
    			int nxt = cur + dir;
    			if(OOB(nxt) || dist[nxt] != -1)
    				continue;
    			
    			dist[nxt] = dist[cur] + 1;
    			q.add(nxt);
    		}
    		
    		for(int nxt : adj[cur]) {
    			if(OOB(nxt) || dist[nxt] != -1)
    				continue;
    			dist[nxt] = dist[cur] + 1;
    			q.add(nxt);
    		}
    	}
    	
    	System.out.println(dist[E]);
    	
    }
    
    static boolean OOB(int x) {
    	return x<=0 || x> N;
    }
    static StringTokenizer st;
    static int N,M, S, E;
    static ArrayList<Integer> adj[];
    static int dist[];
}