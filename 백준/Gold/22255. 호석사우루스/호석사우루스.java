import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       st = new StringTokenizer(br.readLine());
       start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
       end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
       board = new int[n+1][m+1];
       shock = new int[3][n+1][m+1];
       
       for(int y=1; y<=n; y++) {
    	   st =new StringTokenizer(br.readLine());
    	   for(int x=1; x<=m; x++) {
    		   board[y][x] = Integer.parseInt(st.nextToken());
    	   }
       }
       
       for(int turn = 0; turn < 3; turn++) {
    	   for(int y=1;y<=n;y++) {
    		   for(int x=1; x<=m; x++) {
    			   shock[turn][y][x] = INF;
    		   }
    	   }
       }
       
       PriorityQueue<State> pq = new PriorityQueue<>();
       pq.add(new State(start[0], start[1], 1, 0));
//       System.out.println(Arrays.toString(start));
       shock[1][start[0]][start[1]] = 0;
       
       while(!pq.isEmpty()) {
    	   State cur = pq.poll();
    	   
    	   if(shock[cur.turn % 3][cur.y][cur.x] < cur.shock)
    		   continue;
    	   if(cur.y == end[0] && cur.x == end[1]) {
    		   System.out.println(cur.shock);
    		   return;
    	   }
    	   
    	   if(cur.turn % 3 == 0) {
    		   for(int dir = 0; dir <4; dir++) {
    			   int ny = cur.y + dy[dir];
    			   int nx = cur.x + dx[dir];
    			   int nxtTurn = (cur.turn + 1) % 3;
    			   if(OOB(ny,nx) || board[ny][nx] == -1)
    				   continue;
    			   
    			   if(shock[nxtTurn][ny][nx] > shock[cur.turn][cur.y][cur.x] + board[ny][nx]) {
    				   shock[nxtTurn][ny][nx] = shock[cur.turn][cur.y][cur.x] + board[ny][nx];
    				   pq.add(new State(ny,nx, nxtTurn, shock[nxtTurn][ny][nx]));
    			   }
    		   }
    	   }else if(cur.turn % 3 == 1) {
    		   for(int dir : new int[] {0,2}) {
    			   int ny = cur.y + dy[dir];
    			   int nx = cur.x + dx[dir];
    			   int nxtTurn = (cur.turn + 1) % 3;
    			   if(OOB(ny,nx) || board[ny][nx] == -1)
    				   continue;
    			   
    			   if(shock[nxtTurn][ny][nx] > shock[cur.turn][cur.y][cur.x] + board[ny][nx]) {
    				   shock[nxtTurn][ny][nx] = shock[cur.turn][cur.y][cur.x] + board[ny][nx];
    				   pq.add(new State(ny,nx, nxtTurn, shock[nxtTurn][ny][nx]));
    			   }
    		   }
    	   }else {
    		   for(int dir : new int[] {1,3}) {
    			   int ny = cur.y + dy[dir];
    			   int nx = cur.x + dx[dir];
    			   int nxtTurn = (cur.turn + 1) % 3;
    			   if(OOB(ny,nx) || board[ny][nx] == -1)
    				   continue;
    			   
    			   if(shock[nxtTurn][ny][nx] > shock[cur.turn][cur.y][cur.x] + board[ny][nx]) {
    				   shock[nxtTurn][ny][nx] = shock[cur.turn][cur.y][cur.x] + board[ny][nx];
    				   pq.add(new State(ny,nx, nxtTurn, shock[nxtTurn][ny][nx]));
    			   }
    		   }
    	   }
       }
       System.out.println(-1);
    }
    static int INF = Integer.MAX_VALUE / 2;
    static void printBoard(int board[][]) {
    	for(int y=1; y<=n; y++) {
    		for(int x=1;x<=m; x++) {
    			System.out.printf("%3d", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,-1,0, 1};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>n || x<=0 || x>m;
    }
    
    static int shock[][][];
    
    static int n,m;
    static int[] start, end;
    static StringTokenizer st;
    static class State implements Comparable<State>{
    	 int y,x, turn, shock;
    	 public State(int y,int x, int turn, int shock ) {
    		 this.y = y;
    		 this.x = x;
    		 this.turn = turn;
    		 this.shock = shock;
    	 }
    	 
    	 public int compareTo(State o) {
    		 return shock - o.shock;
    	 }
    }
    static int board[][];
}