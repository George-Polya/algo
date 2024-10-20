import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new int[n][n];
        for(int y=0; y<n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x=0; x<n; x++) {
        		adj[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        
        for(int z = 0; z<n;z++) {
        	for(int y =0 ; y<n; y++) {
        		for(int x= 0; x<n; x++) {
        			adj[y][x] = Math.min(adj[y][x], adj[y][z] + adj[z][x]);
        		}
        	}
        }
        
//        printBoard(adj);
        used = new boolean[n];
        used[k] = true;
        solve(k,0, 1 << k, new ArrayList<Integer>(List.of(k)));
        System.out.println(ans);
    }
    
    static boolean used[];
    static void solve(int cur, int sum, int bit, ArrayList<Integer> list) {
//    	System.out.printf("cur: %d, bit: %d\n", cur, bit);
    	if(bit == (1<<n) - 1) {
//    		System.out.printf("%s: %d\n", list, sum);
    		ans = Math.min(ans, sum);
    		return;
    	}
    	
    	for(int nxt = 0; nxt <n;nxt++) {
    		if(used[nxt])
    			continue;
    		used[nxt] = true;
    		list.add(nxt);
    		solve(nxt, sum + adj[cur][nxt], bit | (1<<nxt), list);
    		list.remove(list.size() - 1);
    		used[nxt] = false;
    	}
    }
    
    static void printBoard(int board[][]) {
    	for(int y=0; y<n; y++) {
    		for(int x=0; x<n;x++) {
    			System.out.printf("%3d", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    
    static int INF = Integer.MAX_VALUE;
    static int ans = INF;
    static int n,k;
    static StringTokenizer st;
    static int adj[][];
}