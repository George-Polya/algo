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
       dist = new int[2][k+1][n+1][m+1];
       
       
       
       for(int y=1; y<=n; y++) {
          String line = br.readLine();
          for(int x=1; x<=m; x++) {
             board[y][x] = line.charAt(x-1) - '0';
          }
       }
       
       Queue<State> q = new ArrayDeque<>();
       dist[1][0][1][1] = 1;
       q.add(new State(1,1,0, 1));
       
       
       int ans = INF;
       
       while(!q.isEmpty()) {
//       for(int i = 0; i < 100; i++) {
          State cur = q.poll();
          
          if(cur.y == n && cur.x == m) {
        	  System.out.println(dist[cur.isDay][cur.limit][cur.y][cur.x]);
        	  return;
          }
          
          for(int dir = 0; dir < 4; dir++) {
             int ny = cur.y + dy[dir];
             int nx = cur.x + dx[dir];
             if(OOB(ny,nx))
                continue; 
             
             if(board[ny][nx] == 0 && dist[1 - cur.isDay][cur.limit][ny][nx] == 0) {
            	 dist[1-cur.isDay][cur.limit][ny][nx] = dist[cur.isDay][cur.limit][cur.y][cur.x] + 1;
            	 q.add(new State(ny,nx, cur.limit, 1- cur.isDay));
             }
             
             if(board[ny][nx] == 1 && cur.limit < k) {
            	 if(cur.isDay == 1 && dist[1 - cur.isDay][cur.limit + 1][ny][nx] == 0) { // 낮이면
            		 dist[1 - cur.isDay][cur.limit + 1][ny][nx] = dist[cur.isDay][cur.limit][cur.y][cur.x] + 1;
            		 q.add(new State(ny,nx,cur.limit + 1, 1 - cur.isDay));
            		 
            	 }else if(dist[1 - cur.isDay][cur.limit][cur.y][cur.x] == 0) { // 밤이면
            		 dist[1 - cur.isDay][cur.limit][cur.y][cur.x] = dist[cur.isDay][cur.limit][cur.y][cur.x] + 1;
            		 q.add(new State(cur.y, cur.x, cur.limit, 1 - cur.isDay));
            	 }
             }
             
        
             
       
          }
          
          
       }
//       
//       for(int limit = 0; limit <=k ;limit++) {
//    	   System.out.println("-------");
//    	   System.out.println("limit: "+limit);
//    	   printBoard(dist[limit]);
//       }
       
//       for(int i = 0; i < 2; i++)
//    	   for(int limit = 0; limit<=k;limit++)
//    		   ans = Math.min(ans, dist[i][limit][n][m]);
//       System.out.println(ans);
       
       
       System.out.println(-1);
       
    }
    static int INF = Integer.MAX_VALUE; 
    static void printBoard(int board[][]) {
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=m; x++) {
    			System.out.printf("%3d", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    static int dy[] = {-1,1,0,0,0};
    static int dx[] = {0,0,-1,1,0};
    static boolean OOB(int y,int x) {
       return y<=0 || y>n || x<=0 || x>m;
    }
    
    static class State{
       int y,x,limit, isDay;
       public State(int y,int x,int limit, int isDay) {
          this.y = y;
          this.x = x;
          this.limit = limit;
          this.isDay = isDay;
       }
       
       public String toString() {
          return y+" "+x+" "+limit+" "+isDay;
       }
    }
    
    static int n,m,k;
    static StringTokenizer st;
    static int board[][], dist[][][][];
}