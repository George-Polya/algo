import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        trace = new int[n+1][n+1];
        prev = new int[n+1][n+1];
        adj = new int[n+1][n+1];
        
        for(int y=1; y<=n; y++) {
        	for(int x=1; x<=n; x++) {
        		adj[y][x] = INF;
        		trace[y][x] = x;
        		if(y==x) {
        			adj[y][x] = 0;
        			trace[y][x] = 0;
        		}
        	}
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	adj[from][to] = cost;
        	adj[to][from] = cost;
        }
        
        
        for(int z = 1; z<=n; z++) {
        	for(int y=1; y<=n; y++) {
        		for(int x=1; x<=n; x++) {
        			if(adj[y][x] > adj[y][z] + adj[z][x]) {
        				prev[y][x] = z;
        				adj[y][x] = adj[y][z] + adj[z][x];
        			}
        		}
        	}
        }
        
//        printBoard(prev);
        
        for(int idx = 1; idx<=n; idx++) {
        	
        	for(int i = 1; i<=n; i++) {
        		if(idx == i)
        			continue;
        		int prv = i;
        		while(prev[idx][prv] != 0)
        			prv = prev[idx][prv];
        		trace[idx][i] = prv;
        	}
        	
        }
        
        printBoard(trace);
        
    }
    
    static int INF = Integer.MAX_VALUE / 2;
    static void printBoard(int board[][]) {
    	StringBuilder sb= new StringBuilder();
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=n; x++) {
    			sb.append(y == x ? "-" : board[y][x]).append(' ');
    		}
    		sb.append('\n');
    	}
    	System.out.println(sb);
    }
    
    static StringTokenizer st;
    static int n,m;
    static int trace[][], adj[][], prev[][];
}