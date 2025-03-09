import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	board = new char[N+1][M+1];
    	dist = new int[N+1][M+1][4][2][2];
    	int sy = 0, sx = 0;
    	int cnt = 0;
    	for(int y=1; y<=N; y++) {
    		String line = br.readLine();
    		for(int x=1; x<=M; x++) {
    			board[y][x] = line.charAt(x-1);
    			
    			if(board[y][x] == 'S') {
    				sy = y;
    				sx = x;
    				board[y][x] = '.';
    			}
    			
    			if(board[y][x] == 'C') {
    				if(cnt != 0) {
    					board[y][x] = 'D';
    				}
    				cnt++;
    			}
    			
    			
    			for(int dir = 0; dir < 4; dir++) {
    				for(int c = 0; c < 2; c++) {
    					for(int d= 0; d<2; d++) {
    						dist[y][x][dir][c][d] = INF;
    					}
    				}
    			}
    			
    			
    		}
    	}
    	
    	Queue<State> q = new ArrayDeque<>();
    	
    	for(int dir = 0; dir < 4; dir++) {
    		dist[sy][sx][dir][0][0] = 0;
    		q.add(new State(sy,sx,dir,0,0));
    	}
    	
    	while(!q.isEmpty()) {
    		State cur = q.poll();
    		
    		if(cur.c == 1 && cur.d == 1) {
    			System.out.println(dist[cur.y][cur.x][cur.dir][cur.c][cur.d]);
    			System.exit(0);
    		}
    		
    		for(int moveDir = 0 ;moveDir<4 ;moveDir++) {
    			if(moveDir == cur.dir)
    				continue;
    			
    			int ny = cur.y + dy[moveDir];
    			int nx = cur.x + dx[moveDir];
    			if(OOB(ny,nx) || board[ny][nx] == '#')
    				continue;
    			if(cur.c == 0 && board[ny][nx] == 'C' && dist[ny][nx][moveDir][1][cur.d] == INF) {
    				dist[ny][nx][moveDir][1][cur.d] = dist[cur.y][cur.x][cur.dir][cur.c][cur.d] + 1;
    				q.add(new State(ny,nx,moveDir, 1, cur.d));
    			}
    			
    			if(cur.d == 0 && board[ny][nx] == 'D' && dist[ny][nx][moveDir][cur.c][1] == INF) {
    				dist[ny][nx][moveDir][cur.c][1] = dist[cur.y][cur.x][cur.dir][cur.c][cur.d] + 1;
    				q.add(new State(ny,nx,moveDir, cur.c, 1));
    			}
    			
    			if(dist[ny][nx][moveDir][cur.c][cur.d] == INF && board[ny][nx] == '.') {
    				dist[ny][nx][moveDir][cur.c][cur.d] = dist[cur.y][cur.x][cur.dir][cur.c][cur.d] + 1;
    				q.add(new State(ny,nx, moveDir, cur.c,cur.d));
    			}
    		}
    	}
    	System.out.println(-1);
    }
    static class State{
    	int y,x,dir,c,d;
    	public State(int y,int x,int dir, int c, int d) {
    		this.y = y;
    		this.x = x;
    		this.dir = dir;
    		this.c = c;
    		this.d = d;
    	}
    }
    static int INF = Integer.MAX_VALUE / 2;
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>N || x<=0 || x>M;
    }
    static int N,M;
    static StringTokenizer st;
    static char board[][];
    static int dist[][][][][];
}