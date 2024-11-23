import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String line;
      StringBuilder sb = new StringBuilder();
      int idx = 1;
      while((line = br.readLine()) != null && !line.isEmpty()) {
    	  st = new StringTokenizer(line);
    	  n = Integer.parseInt(st.nextToken());
    	  m = Integer.parseInt(st.nextToken());
    	  board = new char[n+1][m+1];
    	  ans = INT_MAX;
    	  total = 0;
    	  for(int y=1; y<=n; y++) {
    		  String str = br.readLine();
    		  for(int x=1; x<=m; x++) {
    			  board[y][x] = str.charAt(x-1);
    			  if(board[y][x] == '.')
    				  total++;
    		  }
    	  }
    	  sb.append("Case "+idx+": ");
    	  idx++;
    	  
    	  if(total == 1) {
    		  sb.append(0).append('\n');
    		  continue;
    	  }
    	  visited = new boolean[n+1][m+1];
    	  
    	  for(int y=1; y<=n; y++) {
    		  for(int x=1; x<=m; x++) {
    			  if(board[y][x] == '*')
    				  continue;
    			  visited[y][x] = true;
    			  
    			  for(int dir = 0; dir < 4; dir++) {
//    				  System.out.println("-----");
    				  solve(y,x,1,0,dir);
    			  }
    					  
    			  visited[y][x] = false;
    		  }
    	  }
    	  sb.append(ans == INT_MAX ? -1 : ans).append('\n');
      }
      System.out.println(sb);
    }
    static int total;
    static void solve(int y, int x,int cnt ,int num, int dir) {
//    	System.out.printf("%d %d\n", y,x);
    	if(cnt == total) {
    		ans = Math.min(ans, num + 1);
//    		return;
    	}
    	
    	int ny = y + dy[dir];
    	int nx = x + dx[dir];
    	
    	if(OOB(ny,nx) || board[ny][nx] == '*' || visited[ny][nx]) {
    		for(int ndir = 0; ndir < 4; ndir++) {
    			ny = y + dy[ndir];
    			nx = x + dx[ndir];
    			if(OOB(ny,nx) || board[ny][nx] == '*' || visited[ny][nx])
    				continue;
    			visited[ny][nx] = true;
    			solve(ny,nx,cnt + 1, num + 1, ndir);
    			visited[ny][nx] = false;
    		}
    	}else {
    		visited[ny][nx] = true;
    		solve(ny,nx, cnt + 1, num, dir);
    		visited[ny][nx] = false;
    	}
    }
    
    
    static int dy[] = {0,1,0,-1};
    static int dx[] = {1,0,-1,0};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>n || x<=0 || x>m;
    }
    
    static boolean visited[][];
    static int INT_MAX = Integer.MAX_VALUE;
    static int ans;
    static int n,m;
    static char board[][];
   static StringTokenizer st;
}