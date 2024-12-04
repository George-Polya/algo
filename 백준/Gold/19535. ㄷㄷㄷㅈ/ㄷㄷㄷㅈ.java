import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	adj = new ArrayList[n+1];
    	for(int i = 1; i<=n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < n - 1;i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		adj[from].add(to);
    		adj[to].add(from);
    	}
    	
    	boolean visited[] = new boolean[n+1];
//    	long ㄷ = 0;
//    	long ㅈ = 0;
    	long d = 0;
    	long g = 0;
    	
    	for(int i = 1; i<=n; i++) {
    		long a = adj[i].size();
    		if(a >= 3) {
    			g += a * (a-1) * (a-2) / 6;
    		}
    		
    		visited[i] = true;
    		
    		for(int nxt : adj[i]) {
    			long b = adj[nxt].size();
    			
    			if(!visited[nxt]) {
    				d += (a - 1) * (b - 1);
    			}
    		}
    	}
    	
    	if(d == 3 * g) {
    		System.out.println("DUDUDUNGA");
    	}else if(d > 3 * g) {
    		System.out.println("D");
    	}else {
    		System.out.println("G");
    	}
    }    
    
    static int n;
    static StringTokenizer st;
    static ArrayList<Integer> adj[];
}