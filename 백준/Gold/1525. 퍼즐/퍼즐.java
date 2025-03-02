import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	board = new int[3][3];
    	sy = sx = -1;
    	int tmp[][] = new int[3][3];
    	for(int y=0; y<3; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x =0 ; x<3; x++) {
    			board[y][x] = Integer.parseInt(st.nextToken());
    			tmp[y][x] = board[y][x];
    			if(board[y][x] == 0) {
    				sy = y;
    				sx = x;
    			}
    				
    		}
    			
    	}
    	
    	State start = new State(sy,sx, board);
    	Queue<State> q = new ArrayDeque<>();
    	Map<State, Integer> visited = new HashMap<>();
    	q.add(start);
    	visited.put(start, 0);
    	
    	while(!q.isEmpty()) {
    		State cur = q.poll();
    		
    		for(int dir = 0; dir < 4; dir++) {
    			int ny = cur.y + dy[dir];
    			int nx = cur.x + dx[dir];
    			
    			if(OOB(ny,nx))
    				continue;
    			
    			State nxt = swap(cur.y, cur.x, ny,nx, cur.board);
    			if(visited.containsKey(nxt))
    				continue;
    			
    			visited.put(nxt, visited.get(cur) + 1);
    			q.add(nxt);
    		}
    	}
    	
    	int temp[][] = new int[3][3];
    	for(int y=0;y<3; y++) {
    		for(int x=0; x<3; x++) {
    			temp[y][x] = y * 3 + x + 1;
    		}
    	}
    	temp[2][2] = 0;
    	State end = new State(2,2,temp);
    	System.out.println(visited.getOrDefault(end, -1));
    }
    
    static State swap(int curY, int curX, int ny, int nx, int board[][]) {
    	int ret[][] = new int[3][3];
    	for(int y=0;y <3; y++) {
    		for(int x = 0; x< 3; x++) {
    			ret[y][x] = board[y][x];
    		}
    	}
    	
    	int temp = board[ny][nx];
    	ret[ny][nx] = 0;
    	ret[curY][curX] = temp;
    	
    	return new State(ny,nx, ret);
    }
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<0 || y>=3 || x<0 || x>=3;
    }
    
    static int board[][];
    static int sy, sx;
    static StringTokenizer st;
    static class State{
    	int y,x;
    	int board[][];
    	
    	public State(int y, int x, int board[][]) {
    		this.y = y;
    		this.x = x;
    		this.board = board;
    	}
    	
    	public boolean equals(Object o) {
    		if(this == o)
    			return true;
    		
    		if(o == null || getClass()!=o.getClass())
    			return false;
    		
    		State tmp = (State)o;
    		return Arrays.deepEquals(board, tmp.board);
    		
    	}
    	
    	public int hashCode() {
    		return Arrays.deepHashCode(board);
    	}
    	
    }
    
}