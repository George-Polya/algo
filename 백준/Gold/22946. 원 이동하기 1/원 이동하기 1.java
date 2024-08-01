import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static class Circle implements Comparable<Circle>{
		int x,y,r;
		public Circle(int x,int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		public int compareTo(Circle circle) {
			return circle.r - r;
		}
		
		public String toString() {
			return y+" "+x+" "+r;
		}
	}
	
	static Circle circles[];
	static int INF = Integer.MAX_VALUE / 2;
	static StringTokenizer st;
	
	static List<Integer> adj[];
	static boolean visited[];
	static double getDistance(int i, int j) {
		double x1 = circles[i].x;
		double y1 = circles[i].y;
		
		double x2 = circles[j].x;
		double y2 = circles[j].y;
		
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
	}
	
	static void dfs(int cur) {
		double curR = circles[cur].r;
		
		for(int nxt = cur + 1; nxt<=n; nxt++) {
			if(visited[nxt])
				continue;
			
			double dist = getDistance(cur,nxt);
			double nxtR = circles[nxt].r;
			double diffR = (double)Math.abs(nxtR - curR);
			if(dist < diffR) {
				visited[nxt] = true;
				adj[cur].add(nxt);
				adj[nxt].add(cur);
				dfs(nxt);
			}
		}
	}
	
	static int dist[];
	static void dfs2(int cur) {
		for(int nxt : adj[cur]) {
			if(dist[nxt] != -1)
				continue;
			dist[nxt] = dist[cur] + 1;
			dfs2(nxt);
		}
	}
	
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		circles = new Circle[n+1];
		circles[0] = new Circle(0,0,INF);
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			circles[i] = new Circle(x,y,r);
		}
		Arrays.sort(circles);
		adj = new ArrayList[n+1];
		for(int i =0;i<=n;i++) {
			adj[i] = new ArrayList<>();
		}
		visited = new boolean[n+1];
//		for(int i =0; i<=n; i++) {
//			System.out.println(circles[i]);
//		}
		
		visited[0] = true;
		dfs(0);
		
//		for(int i = 0; i <=n; i++) {
//			System.out.printf("i: %d, %s\n", i, adj[i]);
//		}
		
		dist = new int[n+1];
		Arrays.fill(dist, -1);
		dist[0] = 0;
		dfs2(0);
//		System.out.println(Arrays.toString(dist));
		int ans = -1;
		int idx = -1;
		for(int i = 0; i<=n;i++) {
			if(ans < dist[i]) {
				ans = dist[i];
				idx = i;
			}
		}
		Arrays.fill(dist, -1);
		dist[idx] = 0;
		dfs2(idx);
//		System.out.println(Arrays.toString(dist));
		ans = -1;
		for(int i = 0; i<=n;i++)
			ans = Math.max(ans, dist[i]);
		System.out.println(ans);
	}
    
}