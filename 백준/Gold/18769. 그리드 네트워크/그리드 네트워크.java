import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		r = Integer.parseInt(st.nextToken());
    		c = Integer.parseInt(st.nextToken());
    		n = r * (c-1) + (r-1) * c;
    		edges = new Edge[n + 1];
    		
    		int idx = 1;
    		for(int y =1; y <= r; y++) {
    			st = new StringTokenizer(br.readLine());
    			for(int x =1; x<=c-1; x++) {
    				int from =  x + (y - 1) * c;
    				int to = (x+1) + (y - 1) * c;
    				int cost = Integer.parseInt(st.nextToken());
//    				System.out.printf("from: %d, to: %d cost: %d\n", from, to, cost);
    				edges[idx++] = new Edge(from, to, cost);
    			}
    		}
    		
    		for(int y = 1; y<=r-1; y++) {
    			st = new StringTokenizer(br.readLine());
    			for(int x=1; x<= c; x++) {
    				int from = x + (y - 1) * c;
    				int to = x + y * c;
    				int cost = Integer.parseInt(st.nextToken());
//    				System.out.printf("from: %d, to: %d cost: %d\n", from, to, cost);
    				edges[idx++] = new Edge(from, to, cost);
    			}
    		}
    		
//    		System.out.printf("n: %d, idx: %d\n", n, idx);
    		
    		uf = new int[n+1];
    		for(int i = 1; i<=n; i++)
    			uf[i] = i;
    		
    		Arrays.sort(edges, 1, n+1);
//    		System.out.println(Arrays.toString(edges));
    		int cnt = 0;
    		int ans = 0;
    		
    		for(int i = 1; i<=n; i++) {
    			Edge e = edges[i];
//    			System.out.println(e);
    			if(union(e.x, e.y)) {
    				
    				cnt++;
    				ans += e.cost;
    				if(cnt == n - 1)
    					break;
    			}
    		}
//    		System.out.println(ans);
    		sb.append(ans).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static int find(int x) {
    	if(x == uf[x])
    		return x;
    	return uf[x] = find(uf[x]);
    }
    
    static boolean union(int x,int y) {
    	x = find(x);
    	y = find(y);
    	if(x == y)
    		return false;
    	
    	uf[x] = y;
    	return true;
    }
    
    static int T, r, c, n;
    static Edge edges[];
    static int uf[];
    static StringTokenizer st;
    static class Edge implements Comparable<Edge>{
    	int x, y, cost;
    	public Edge(int x,int y,int cost) {
    		this.x = x;
    		this.y = y;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    	
    	public String toString() {
    		return x +" "+y+" "+cost;
    	}
    }
}