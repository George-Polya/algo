import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      board = new int[m+1][n+1];
      for(int y=1; y<=m; y++) {
    	  st = new StringTokenizer(br.readLine());
    	  for(int x=1; x<=n; x++) {
    		  board[y][x] = Integer.parseInt(st.nextToken());
    	  }
      }
      st = new StringTokenizer(br.readLine());
//      start = new Tuple(Integer.parseInt(st.nextToken()),
//                		Integer.parseInt(st.nextToken()),
//                		Integer.parseInt(st.nextToken()) - 1);
      
      start = new int[] { Integer.parseInt(st.nextToken()), 
    		  			  Integer.parseInt(st.nextToken()),
    		  			  Integer.parseInt(st.nextToken()) - 1};
      
      st = new StringTokenizer(br.readLine());
      end = new int[] {  Integer.parseInt(st.nextToken()), 
			  		   	  Integer.parseInt(st.nextToken()),
			  			  Integer.parseInt(st.nextToken()) - 1};      
      
      dist = new int[4][m+1][n+1];
      
      for(int z = 0; z<4; z++) {
    	  for(int y = 0; y<=m; y++) {
    		  for(int x=0; x<=n; x++)
    			  dist[z][y][x] = INT_MAX;
    	  }
      }
      
      bfs();
      System.out.println(ans);
    }
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    
    static int ans = INT_MAX;
    static void bfs() {
    	Queue<State> q = new ArrayDeque<>();
    	q.add(new State(start[0], start[1], start[2]));
    	dist[start[2]][start[0]][start[1]] = 0;
    	
    	while(!q.isEmpty()) {
    		State cur = q.poll();
    		
//    		System.out.println(cur);
    		if(cur.isSame(end[0], end[1], end[2])) {
//    			System.out.println(dist[cur.dir][cur.y][cur.x]);
    			ans = Math.min(ans, dist[cur.dir][cur.y][cur.x]);
//    			return;
    		}
    		
    		
    		
    		for(int ndir : new int[] {left(cur.dir), right(cur.dir),cur.dir}) {
    			
    			if(ndir == cur.dir) {
    				// 명령 1
    				for(int k = 3; k >= 1; k--) {
    					State nxt = getNxtPos(cur.y, cur.x, cur.dir, k);
    					if(nxt != NO_STATE) {
//    				System.out.printf("nxt: %s, k: %d\n", nxt, k);
    						dist[nxt.dir][nxt.y][nxt.x] = dist[cur.dir][cur.y][cur.x] + 1;
    						q.add(nxt);
    					}
    				}
    			}else {
    				if(dist[ndir][cur.y][cur.x] > dist[cur.dir][cur.y][cur.x] + 1) {
    					dist[ndir][cur.y][cur.x] = dist[cur.dir][cur.y][cur.x] + 1;
    					q.add(new State(cur.y, cur.x, ndir));
    				}
    				
    			}
    			

    		}
    	}
    }
    
    static State getNxtPos(int y,int x, int dir, int k) {
    	
    	int ny = y;
    	int nx = x;
//    	System.out.printf("k: %d | (%d, %d)\n", k, y,x);
    	while(k-- > 0) {
    		ny += dy[dir];
    		nx += dx[dir];
//    		System.out.printf("k: %d | (%d, %d)\n", k, ny,nx);
    		if(OOB(ny,nx) || dist[dir][ny][nx] <= dist[dir][y][x] + 1 || board[ny][nx] == 1)
    			return NO_STATE;
    		
    		
    	}
    	
    	return new State(ny,nx,dir);
    }
    
    static int left(int dir) {
    	if(dir == 0)
    		return 3;
    	if(dir == 1)
    		return 2;
    	if(dir == 2)
    		return 0;
    	return 1;
    }
    
    static int right(int dir) {
    	if(dir == 0)
    		return 2;
    	if(dir == 1)
    		return 3;
    	if(dir == 2)
    		return 1;
    	return 0;
    }
    
    static State NO_STATE = new State(-1,-1,-1);
    static class State{
    	int y,x, dir;
    	
    	public State(int y,int x,int dir) {
    		this.y = y;
    		this.x = x;
    		this.dir = dir;
    	}
    	
    	public String toString() {
    		return String.format("(%d, %d) | dir: %d", y,x,dir);
    	}
    	
    	public boolean isSame(int y,int x, int dir) {
    		return this.y == y && this.x == x && this.dir == dir;
    	}
    }
    static int dist[][][];
    static int[] start, end;
    
    static int m,n;
    static int dy[] = {0,0,1,-1};
    static int dx[] = {1,-1,0,0};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>m || x<=0 || x>n;
    }
    static StringTokenizer st;
    static int board[][];
}