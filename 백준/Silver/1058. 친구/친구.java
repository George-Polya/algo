import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	adj = new List[N+1];
    	for(int y =1; y<=N; y++) {
    		adj[y] = new ArrayList<>();
    		String line = br.readLine();
    		for(int x=1; x<=N; x++) {
    			char ch = line.charAt(x-1);
    			if(ch == 'Y') {
    				adj[y].add(x);
    			}
    		}
    	}
//    	
//    	for(int y =1; y<=N; y++) {
//    		System.out.println(adj[y]);
//    	}
    	
    	
    	int ans = 0;
    	for(int y=1; y<=N; y++) {
    		ans = Math.max(ans, bfs(y));
    	}
    	System.out.println(ans);
    }
    
    static int bfs(int start) {
    	Queue<Integer> q = new ArrayDeque<>();
    	boolean visited[] = new boolean[N+1];
    	visited[start] = true;
    	q.add(start);
    	int dist = 0;
    	int ret = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		
    		for(int s = 0; s < size;s++) {
    			if(1<= dist && dist <= 2)
    				ret++;
    			if(dist == 3)
    				return ret;
    			int cur = q.poll();
    			
    			for(int nxt : adj[cur]) {
    				if(visited[nxt])
    					continue;
    				visited[nxt] = true;
    				q.add(nxt);
    			}
    		}
    		dist++;
    	}
    	return ret;
    }
    
    static int N;
    static List<Integer> adj[];
}