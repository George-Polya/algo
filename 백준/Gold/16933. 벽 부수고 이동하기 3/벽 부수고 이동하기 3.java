import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       board = new int[n+1][m+1];
       for(int y=1; y<=n; y++) {
          String line = br.readLine();
          for(int x=1; x<=m; x++) {
             board[y][x] = line.charAt(x-1) - '0';
          }
       }
       visited = new boolean[2][k+1][n+1][m+1];
       
       Queue<State> q = new ArrayDeque<>();
       visited[1][0][1][1] = true;
       q.add(new State(1, 0, 1,1,1));
       
       while(!q.isEmpty()) {
          int size = q.size();
          for(int s = 0; s < size; s++) {
        	  State cur = q.poll();
        	  if(cur.y == n && cur.x == m) {
        		  System.out.println(cur.dist);
        		  return;
        	  }
        	  
        	  for(int dir =0; dir <4 ;dir++) {
        		  int ny = cur.y + dy[dir];
        		  int nx = cur.x + dx[dir];
        		  int nDay = 1 - cur.day;
        		  
        		  if(OOB(ny,nx))
        			  continue;
        		  
        		  if(board[ny][nx] == 0) {
        			  if(!visited[nDay][cur.limit][ny][nx]) {
        				  visited[nDay][cur.limit][ny][nx] = true;
        				  q.add(new State(nDay,cur.limit, ny,nx, cur.dist+1));
        			  }
        			  
        			  
        		  }else {
        			  if(cur.day == 0) {
        				  if(!visited[nDay][cur.limit][cur.y][cur.x]) {
        					  visited[nDay][cur.limit][cur.y][cur.x] = true;
        					  q.add(new State(nDay, cur.limit, cur.y, cur.x, cur.dist+1));
        				  }
        			  }else if(cur.limit < k && !visited[nDay][cur.limit][ny][nx]) {
        				  visited[nDay][cur.limit][ny][nx] = true;
        				  q.add(new State(nDay, cur.limit + 1, ny,nx, cur.dist + 1));
        			  }
        			  
        			  
        		  }
        		  
        	  }
          }
       }
       
       System.out.println(-1);
       
    }
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
       return y<=0 || y>n || x<=0 || x>m;
    }
    
    static class State{
       int y,x, day, limit, dist;
       public State(int day, int limit, int y,int x, int dist) {
          this.day = day;
          this.limit = limit;
          this.y = y;
          this.x = x;
          this.dist = dist;
       }
       
       public String toString() {
    	   return String.format("(%d, %d) | day: %d" , y,x,day);
       }
    }
    static int n,m,k;
    static int board[][];
    static boolean visited[][][][];
    static StringTokenizer st;
}