import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	sy = Integer.parseInt(st.nextToken());
    	sx = Integer.parseInt(st.nextToken());
    	ey = Integer.parseInt(st.nextToken());
    	ex = Integer.parseInt(st.nextToken());
    	
    	board = new int[N+1][M+1];
    	dist = new int[2][N+1][M+1];
    	for(int y=1; y<=N; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x=1; x<=M; x++) {
    			board[y][x] = Integer.parseInt(st.nextToken());
    			for(int i = 0; i < 2; i++) {
    				dist[i][y][x] = INF;
    			}
    		}
    	}
    	
    	Queue<Tuple> q = new ArrayDeque<>();
    	dist[0][sy][sx] = 0;
    	q.add(new Tuple(sy,sx,0));
    	yVisited = new boolean[N+1];
    	xVisited = new boolean[M+1];
    	while(!q.isEmpty()) {
    		Tuple cur = q.poll();
    		
    		if(cur.y == ey && cur.x == ex) {
    			System.out.println(dist[cur.cnt][cur.y][cur.x]);
    			System.exit(0);
    		}
    		
    		int value = board[cur.y][cur.x];
    		for(int dir = 0; dir < 4; dir++) {
    			int ny = cur.y + dy[dir] * value;
    			int nx = cur.x + dx[dir] * value;
    			
    			if(OOB(ny,nx) || dist[cur.cnt][ny][nx] != INF)
    				continue;
    			dist[cur.cnt][ny][nx] = dist[cur.cnt][cur.y][cur.x] + 1;
    			q.add(new Tuple(ny,nx,cur.cnt));
    		}
    		
    		
    		if(cur.cnt == 0) {
    			if(!yVisited[cur.y]) {
    				yVisited[cur.y] = true;
    				int ny = cur.y;
    				
    				for(int nx=1; nx<=M; nx++) {
    					if(dist[1][ny][nx] == INF) {
    						dist[1][ny][nx] = dist[0][cur.y][cur.x] + 1;
    						q.add(new Tuple(ny,nx,1));
    					}
    				}
    			}
    			
    			if(!xVisited[cur.x]) {
    				xVisited[cur.x] = true;
    				int nx = cur.x;
    				
    				for(int ny = 1; ny<=N; ny++) {
    					if(dist[1][ny][nx] == INF) {
    						dist[1][ny][nx] = dist[0][cur.y][cur.x] + 1;
    						q.add(new Tuple(ny,nx,1));
    					}
    				}
    			}
    			
    		}
    		
    	}
    	System.out.println(-1);
    }
    static boolean yVisited[], xVisited[];
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<=0 || y>N || x<=0 || x>M;
    }
    
    static int N,M;
    static int INF = Integer.MAX_VALUE / 2;
    static StringTokenizer st;
    static int sy,sx,ey,ex;
    static int board[][];
    static int dist[][][];
    static class Tuple{
    	int y,x,cnt;
    	public Tuple(int y,int x,int cnt) {
    		this.y = y;
    		this.x = x;
    		this.cnt = cnt;
    	}
    }
}