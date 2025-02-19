import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	if(M == N - 1) {
    		System.out.println("NO");
    		return;
    	}
    	
    	
    	edges = new Edge[M];
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		edges[i] = new Edge(i, from, to, cost);
    	}
    	
    	Arrays.sort(edges);
    	uf = new int[N+1];
    	for(int i = 1; i<=N; i++)
    		uf[i] = i;
    	
    	mst = new boolean[M];
    	int cnt = 0;
    	for(int i = 0; i < M; i++) {
    		Edge edge = edges[i];
    		
    		if(union(edge.from, edge.to)) {
    			mst[i] = true;
    			cnt++;
    		}
    		
    		if(cnt == N - 1)
    			break;
    	}
    	
//    	System.out.println(Arrays.toString(mst));
//    	System.out.println(Arrays.toString(uf));
//    	System.out.println(Arrays.toString(edges));
    	
    	int idx = findIdx(mst);
//    	System.out.println(idx);
//    	System.out.println(edges[idx]);
    	
    	
    	
    	for(int i = 1; i<=N; i++) {
    		uf[i] = i;
    	}
    	
    	Edge e = edges[idx];
    	List<Integer> result = new ArrayList<>();
    	result.add(e.id);
    	cnt = 1;
    	union(e.from, e.to);
    	
    	for(int i = 0; i < M; i++) {
    		if(i == idx)
    			continue;
    		e = edges[i];
    		if(union(e.from, e.to)) {
    			result.add(e.id);
    			cnt++;
    		}
    		
    		if(cnt == N - 1)
    			break;
    	}
    	
//    	System.out.println(result);
    	StringBuilder sb = new StringBuilder();
    	sb.append("YES").append('\n');
    	for(int num : result)
    		sb.append(num + 1).append(' ');
    	System.out.println(sb);
    	
    }
    static int findIdx(boolean mst[]) {
    	for(int i = 0; i < M; i++) {
    		if(!mst[i])
    			return i;
    	}
    	return -1;
    }
    
    static boolean union(int x,int y) {
    	x = find(x);
    	y = find(y);
    	if(x == y)
    		return false;
    	
    	if(x > y) {
    		uf[x] = y;
    	}else {
    		uf[y] = x;
    	}
    	return true;
    }
    
    static int find(int x) {
    	if(x == uf[x])
    		return x;
    	
    	return uf[x] = find(uf[x]);
    }
    
    
    static int N,M;
    static int uf[];
    static Edge edges[];
    static boolean mst[];
    static StringTokenizer st;
    static class Edge implements Comparable<Edge>{
    	int id, from, to, cost;
    	
    	public Edge(int id, int from, int to, int cost) {
    		this.id = id;
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Edge o) {
    		return cost - o.cost;
    	}
    	
    	public String toString() {
    		return String.format("id: %d, (%d, %d, %d)", id, from, to, cost);
    	}
    }
}