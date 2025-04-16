import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new List[N+1];
        for(int i =1; i<=N;i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i =0; i<N-1;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	adj[from].add(to);
        	adj[to].add(from);
        }
        
        sequence = new int[N+1];
        order = new int[N+1];
        st = new StringTokenizer(br.readLine());
        
        for(int i = 1; i<=N; i++) {
        	sequence[i] = Integer.parseInt(st.nextToken());
        	order[sequence[i]] = i;
        }
        
        if(sequence[1] != 1) {
        	System.out.println(0);
        	return;
        }
        
        for(int i = 1; i<=N;i++) {
        	Collections.sort(adj[i], (u,v)->Integer.compare(order[u], order[v]));
        }
        visited = new boolean[N+1];
        dfs(1);
        
        boolean check = true;
        if(result.size() != N)
        	check = false;
        else {
        	for(int i = 0; i<N;i++) {
        		if(result.get(i) != sequence[i+1]) {
        			check = false;
        			break;
        		}
        	}
        }
        System.out.println(check ? 1 : 0);
    }
    static void dfs(int cur) {
    	visited[cur] = true;
    	result.add(cur);
    	
    	for(int nxt : adj[cur]) {
    		if(!visited[nxt])
    			dfs(nxt);
    	}
    }
    static List<Integer> result= new ArrayList<>();
    static int N;
    static List<Integer> adj[];
    static StringTokenizer st;
    static int sequence[], order[];
    static boolean visited[];
}