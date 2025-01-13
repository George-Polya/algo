import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        edges = new Edge[m];
        for(int i = 0; i < m ;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	edges[i] = new Edge(from, to, cost);
        }
        
        dist = new long[n+1]; // 1번 도시에서 시작해 나머지 도시로 가는데 필요한 최소 거리
        Arrays.fill(dist, INF);
        dist[1] = 0;

        
        /*
         * 기본적인 아이디어는 다익스트라와 같다.(1에서 현재 노드까지의 최단 거리 = 1에서 이전 노드까지의 최단거리 + 이전노드에서 현재 노드까지의 최단거리)  
         * 다만, 다익스트라와 다른점은 음수 가중치가 존재한다는 점
         * 따라서, 매번 모든 간선을 확인해봐야한다(벨만 포드)
		 * n개의 도시각각에 대해 전부 다음을 수행
		 */
        for(int i = 1; i<=n; i++) {
        	for(Edge edge : edges) {
        		if(dist[edge.from] == INF)
        			continue;
        		
        		if(dist[edge.to] > dist[edge.from] + edge.cost) {
        			dist[edge.to] = dist[edge.from] + edge.cost;
        		}
        	}
        }
        
        /*
         * 음수 cycle 발생 체크 
         */
        for(Edge edge : edges) {
        	if(dist[edge.from] == INF)
        		continue;
        	if(dist[edge.to] > dist[edge.from] + edge.cost) {
        		System.out.println(-1);
        		return;
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i<=n; i++) {
        	sb.append(dist[i] == INF ? -1 : dist[i]).append('\n');
        }
        
        System.out.println(sb);
        	
    }
    static StringTokenizer st;
    static int n,m;
    static class Edge{
    	int from, to, cost;
    	public Edge(int from, int to, int cost) {
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    }
    static Edge edges[];
    static long INF = Long.MAX_VALUE;
    static long dist[];
}