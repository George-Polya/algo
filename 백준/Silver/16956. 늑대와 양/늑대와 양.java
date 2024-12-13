import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board =new char[r+1][c+1];
        
        
        visited = new boolean[r+1][c+1];
        for(int y=1; y<=r; y++) {
        	String line = br.readLine();
        	for(int x=1; x<=c; x++) {
        		board[y][x] = line.charAt(x-1);
        		
        		if(board[y][x] == 'W') {
        			q.add(new Pair(y,x));
        			visited[y][x] = true;
        		}
        	}
        }
        
        for(int y=1; y<=r; y++) {
        	for(int x=1; x<=c; x++) {
        		if(board[y][x] == 'S') {
        			buildWall(y,x);
        		}
        	}
        }
        
        
        System.out.println(bfs() ? 0 : 1);
        StringBuilder sb = new StringBuilder();
        for(int y=1; y<=r; y++) {
        	for(int x=1; x<=c; x++) {
        		sb.append(board[y][x]);
        	}
        	sb.append('\n');
        }
        System.out.println(sb);
        
    }
    
    
    static boolean bfs() {
    	while(!q.isEmpty()) {
    		Pair cur = q.poll();
    		
    		if(board[cur.y][cur.x] == 'S')
    			return true;
    		
    		for(int dir = 0; dir <4 ;dir++) {
    			int ny = cur.y + dy[dir];
    			int nx = cur.x + dx[dir];
    			
    			if(OOB(ny,nx) || visited[ny][nx])
    				continue;
    			
    			if(board[ny][nx] == '.' || board[ny][nx] == 'S') {
    				q.add(new Pair(ny,nx));
    				visited[ny][nx] = true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    static Queue<Pair> q = new ArrayDeque<>();
    static boolean visited[][];
    
    static class Pair{
    	int y,x;
    	public Pair(int y,int x) {
    		this.y = y;
    		this.x = x;
    	}
    }
    
    static void buildWall(int y,int x) {
    	for(int dir = 0; dir< 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		if(OOB(ny,nx) || board[ny][nx] != '.')
    			continue;
    		
    		board[ny][nx] = 'D';
    		
    	}
    }
    
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<=0 ||y > r|| x<=0 || x> c;
    }
    static int r,c;
    static StringTokenizer st;
    static char board[][];
}