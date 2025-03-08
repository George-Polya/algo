import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	size = 4 * N;
    	
 
    	init(br);
    	
    	dist[0][start.y][start.x] = 0;
    	Queue<Tuple> q = new ArrayDeque<>();
    	q.add(new Tuple(start.y, start.x, 0));
    	
    	
    	
    	while(!q.isEmpty()) {
    		Tuple cur = q.poll();
    		
    		if(board[cur.d][cur.y][cur.x] == 'E') {
    			System.out.println(dist[cur.d][cur.y][cur.x]);
    			System.exit(0);
    		}
    		
    		int div = getDivision(cur.y, cur.x);
    		
    		for(int moveDir = 0; moveDir < 5; moveDir++) {
    			int ny = cur.y + dy[moveDir];
    			int nx = cur.x + dx[moveDir];
    			
    			if(OOB(ny,nx))
    				continue;
    			
    			int nDiv = getDivision(ny,nx);
    			if(nDiv == -1)
    				continue;
    			
    			int nd = (div == nDiv) ? (cur.d + 1) % 4 : 1;
    			Pair nxt = rotate(ny,nx);
    			if(board[nd][nxt.y][nxt.x] == '#' || dist[nd][nxt.y][nxt.x] != INF)
    				continue;
    			dist[nd][nxt.y][nxt.x] = dist[cur.d][cur.y][cur.x] + 1;
    			q.add(new Tuple(nxt.y,nxt.x, nd));
    		}
    	}
    	System.out.println(-1);
    	
    }
    
    static Pair rotate(int y, int x) {
		int sy = (y / 4) * 4;
		int sx = (x / 4) * 4;
		
		int y1 = y - sy;
		int x1 = x - sx;
		
		int y2 = x1;
		int x2 = 3 - y1;
		
		int y3 = y2 + sy;
		int x3 = x2 + sx;
		
		return new Pair(y3,x3);
    }
    
    static int dy[] = {0,-1,1,0,0};
    static int dx[] = {0,0,0,-1,1};
    
    static int getDivision(int y,int x) {
    	if(OOB(y,x))
    		return -1;
    	
    	return (y / 4) * N + (x / 4) + 1;
    }
    
    
    static boolean OOB(int y,int x) {
    	return y<0 || y>=size || x<0 || x >= size;
    }
    
    
    static void init(BufferedReader br) throws IOException {
    	board = new char[4][size][size];
    	
    	dist = new int[4][size][size];
    	
       	for(int y=0;y<size;y++) {
    		String line = br.readLine();
    		for(int x= 0; x<size;x++) {
    			board[0][y][x] = line.charAt(x);
    			
    			if(board[0][y][x] == 'S') {
    				start = new Pair(y,x);
    			}
    			
    			int curY = y;
    			int curX = x;
    			dist[0][y][x] = INF;
    			for(int i = 1; i<4;i++) {
    				dist[i][y][x] = INF;
    				
    				Pair nxt = rotate(curY, curX);
    				
    				board[i][nxt.y][nxt.x]= board[i-1][curY][curX];
    				curY = nxt.y;
    				curX = nxt.x;
    				
    			}
    		}
    	}
    }
    static Pair start;
    static void printBoard(char board[][]) {
    	for(int y=0; y<size;y++) {
    		for(int x=0; x<size;x++) {
    			System.out.printf("%3s", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    static int N;
    static int size;
    static int sy,sx;
    static int INF = Integer.MAX_VALUE / 2;
    static char board[][][];
    static int dist[][][];
    static class Pair{
    	int y,x;
    	public Pair(int y,int x) {
    		this.y = y;
    		this.x = x;
    	}
    }
    
    static class Tuple{
    	int y,x,d;
    	public Tuple(int y,int x, int d) {
    		this.y = y;
    		this.x = x;
    		this.d = d;
    	}
    }
}