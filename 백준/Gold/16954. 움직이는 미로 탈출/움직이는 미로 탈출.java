import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int y=1; y<=n; y++) {
        	String line = br.readLine();
        	
        	for(int x=1; x<=n; x++) {
        		board[y][x] = line.charAt(x-1);
        	}
        }
        
        
        
        System.out.println(bfs() ? 1 : 0);
        
    }
    
    static boolean bfs() {

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(n,1));
        while(!q.isEmpty()) {
        	int size = q.size();
        	visited = new boolean[n+1][n+1];
        	
        	for(int s = 0; s < size; s++) {
        		
        		Pair cur = q.poll();
        		
        		if(board[cur.y][cur.x] == '#')
        			continue;
        		
        		if(cur.y == 1 && cur.x == n) {
        			return true;
        		}
        		
        		
        		for(int dir = 0; dir < 9; dir++) {
        			int ny = cur.y + dy[dir];
        			int nx = cur.x + dx[dir];
        			
        			if(OOB(ny,nx) || visited[ny][nx] || board[ny][nx] == '#')
        				continue;
        			q.add(new Pair(ny,nx));
        			visited[ny][nx] = true;
        		}
        	}
        	
        	shift();
        	
        }
        
        return false;
    }
    
    static void printBoard(char board[][]) {
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=n; x++) {
    			System.out.print(board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    static void shift() {
    	
    	for(int y=n; y>=1; y--) {
    		for(int x= 1; x<=n; x++) {
    			if(board[y][x] == '#') {
    				board[y][x] = '.';
    				if(y != n)
    					board[y+1][x] = '#';
    			}
    		}
    	}
    	
//    	for(int i = n; i>=2; i--) {
//    		board[i] = board[i-1];
//    	}
//    	
//    	for(int i = 1; i<=n; i++) {
//    		board[1][i] = '.';
//    	}
    	
    }
    
    static class Pair{
    	int y,x;
    	public Pair(int y,int x) {
    		this.y = y;
    		this.x = x;
    	}
    	
    	public String toString() {
    		return y+" "+x;
    	}
    }
    
    static int n = 8;
    static char board[][] = new char[n+1][n+1];
    static boolean visited[][] = new boolean[n+1][n+1];
    static int dy[] = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
    static int dx[] = {0, -1, 0, 1, 0, 1, -1, -1, 1};

    static boolean OOB(int y, int x) {
    	return y<=0 || y>n || x<=0 || x>n;
    }
}