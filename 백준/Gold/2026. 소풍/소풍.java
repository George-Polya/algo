import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        
        adj = new Set[n+1];
        for(int i = 1; i<=n; i++) {
        	adj[i] = new HashSet<>();
        }
        
        for(int i = 0; i < f; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	adj[a].add(b);
        	adj[b].add(a);
        }
        
        solve(1,0);
        System.out.println(-1);
    }
    
    static void solve(int cur, int cnt) {
    	if(cnt == k) {
//    		System.out.println(selected);
    		StringBuilder sb = new StringBuilder();
    		for(int s : selected)
    			sb.append(s).append('\n');
    		System.out.println(sb);
    		System.exit(0);
//    		return;
    	}
    	
    	for(int i = cur; i<=n; i++) {
    		if(check(i)) {
    			selected.add(i);
    			solve(i + 1, cnt + 1);
    			selected.remove(selected.size() - 1);
    		}
    	}
    }
    
    static boolean check(int idx) {
    	if(selected.isEmpty())
    		return true;
    	
    	for(int s : selected) {
    		if(!adj[idx].contains(s))
    			return false;
    	}
    	return true;
    }
    
    static ArrayList<Integer> selected = new ArrayList<>();
    
    static String ans = "";
    static StringTokenizer st;
    static int k,n,f;
    static Set<Integer> adj[];
}