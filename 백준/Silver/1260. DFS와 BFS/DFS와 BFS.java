import java.io.*;
import java.util.*;
public class Main {
    static StringTokenizer st;
    static int n,m,v;
    static List<Integer> adj[];
    static StringBuilder sb = new StringBuilder();
    static boolean visited[];
    static void dfs(int root) {
    	sb.append(root).append(' ');
    	for(int nxt : adj[root]) {
    		if(visited[nxt])
    			continue;
    		visited[nxt] = true;
    		dfs(nxt);
    	}
    }
    
    static void bfs(int root) {

    	Arrays.fill(visited, false);
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(root);
    	visited[root] = true;
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		sb.append(cur).append(' ');
    		for(int nxt : adj[cur]) {
    			if(visited[nxt])
    				continue;
    			visited[nxt] = true;
    			q.add(nxt);
    		}
    	}
    	
    }
    		
    
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        adj = new List[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i<=n; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	adj[a].add(b);
        	adj[b].add(a);
        }
        
        for(int i = 1; i<=n; i++) {
        	Collections.sort(adj[i]);
//        	System.out.println(adj[i]);
        }
        
   
        visited[v] = true;
        dfs(v);
        sb.append('\n');
        bfs(v);
        System.out.println(sb);
    }
}