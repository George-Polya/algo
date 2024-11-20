import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      a1 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      
      st = new StringTokenizer(br.readLine());
      a2 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      
      st = new StringTokenizer(br.readLine());
      b1 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      
      st = new StringTokenizer(br.readLine());
      b2 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      
      
      
      int ans = Math.min(findShortestPath(a1,a2,b1,b2), findShortestPath(b1,b2, a1,a2));
      
      System.out.println(ans == INT_MAX ? "IMPOSSIBLE" : ans);
      
    }
    
    static int findShortestPath(Pair s1, Pair e1, Pair s2, Pair e2) {
    	prev = new Pair[n+1][m+1];
    	visited = new boolean[n+1][m+1];
    	
    	int dist1 = bfs(s1,e1, s2, e2);
    	
    	// 경로구하기(역추적)
    	visited = new boolean[n+1][m+1];
    	backtrace(s1, e1);
    	
    	int dist2 = bfs(s2,e2, s1, e1);
    	
    	return dist2 == INT_MAX ? INT_MAX : dist1 + dist2;
    }
    
    static void backtrace(Pair s, Pair e) {
    	Pair cur = e;
    	visited[cur.y][cur.x] = true;
    	
    	while(!cur.isSame(s.y,s.x)) {
    		cur = prev[cur.y][cur.x];
    		visited[cur.y][cur.x] = true;
    	}
    }
    
    static Pair prev[][];
    static boolean visited[][];
    static int bfs(Pair s1, Pair e1, Pair s2, Pair e2) {
    	Queue<Pair> q = new ArrayDeque<>();
    	q.add(s1);
    	int dist = 0;
    	visited[s2.y][s2.x] = true;
    	visited[e2.y][e2.x] = true;
    	visited[s1.y][s1.x] = true;
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		
    		for(int sz = 0; sz < size; sz++) {
    			Pair cur = q.poll();
    			if(cur.isSame(e1.y, e1.x))
    				return dist;
    			
    			for(int dir = 0; dir < 4; dir++) {
    				int ny = cur.y + dy[dir];
    				int nx = cur.x + dx[dir];
    				
    				if(OOB(ny,nx) || visited[ny][nx])
    					continue;
    				prev[ny][nx] = cur;
    				visited[ny][nx] = true;
    				q.add(new Pair(ny,nx));
    			}
    		}
    		
    		dist++;
    	}
    	
    	return INT_MAX;
    }
    
    
    static void printBoard(int board[][]) {
    	for(int y=0; y<=n; y++) {
    		for(int x=0; x<=m; x++) {
    			System.out.printf("%3d", board[y][x] == INT_MAX ? -1 : board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    static int INT_MAX = Integer.MAX_VALUE;
    static int dist[][];
    static Pair a1,a2,b1,b2;
    
    static StringTokenizer st;
    static int n,m;
    static class Pair{
    	int y,x;
    	public Pair(int y,int x) {
    		this.y = y;
    		this.x = x;
    	}
    	
    	public boolean isSame(int y,int x) {
    		return this.y == y && this.x == x;
    	}
    	
    	public String toString() {
    		return y+" "+x;
    	}
    }
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<0 || y>n || x<0 || x>m;
    }
}