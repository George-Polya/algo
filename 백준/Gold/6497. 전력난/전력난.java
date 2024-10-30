import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	while(true) {
    		st = new StringTokenizer(br.readLine());
        	m = Integer.parseInt(st.nextToken());
        	n = Integer.parseInt(st.nextToken());
        	if(m == 0 && n == 0)
        		break;
        	edges = new Edge[n];
        	int total = 0;
        	for(int i = 0; i < n;i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		if(x == 0 && y == 0)
        			break;
        		int cost = Integer.parseInt(st.nextToken());
        		total += cost;
        		edges[i] = new Edge(x,y,cost);
        	}
        	
        	Arrays.sort(edges);
        	
        	uf = new int[m];
        	for(int i = 0; i < m; i++) {
        		uf[i] = i;
        	}
        	int cnt = 0;
        	for(Edge edge : edges) {
        		int x = edge.x;
        		int y = edge.y;
        		int cost = edge.cost;
        		
        		if(!union(x,y)) {
        			cnt++;
        			total -= cost;
        			
        		}
//        		System.out.printf("%d %d %d\n", x,y,cost);
//        		System.out.println(Arrays.toString(uf));
        	}
//        	System.out.println(total);
        	sb.append(total).append('\n');
     
    	}
    	System.out.println(sb);
    	
    }
    
    static int find(int x) {
    	if(x == uf[x])
    		return x;
    	
    	return uf[x] = find(uf[x]);
    }
    
    static boolean union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	
    	if(x == y)
    		return true;
    	
    	uf[x] = y;
    	return false;
    			
    }
    
    static Edge edges[];
    static int uf[];
    static StringTokenizer st;
    static int m,n;
    static class Edge implements Comparable<Edge>{
    	int x,y,cost;
    	
    	public Edge(int x,int y, int cost) {
    		this.x = x;
    		this.y = y;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    }
}