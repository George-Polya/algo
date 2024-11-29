import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        for(int y=0; y<n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x= 0; x<m; x++) {
        		board[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        idBoard = new int[n][m];
        sizes.add(-1);
        for(int y= 0; y<n ;y++) {
        	for(int x= 0; x<m; x++) {
        		if(idBoard[y][x] != 0 || board[y][x] == 0)
        			continue;
        		int size = bfs(y,x, ++id);
        		sizes.add(size);
        	}
        }
        
        visited = new boolean[id + 1];
        int ans = 0;
        for(int y= 0; y<n; y++) {
        	for(int x=0; x<m; x++) {
        		if(board[y][x] == 1)
        			continue;
        		int cnt = solve(y,x);
        		ans = Math.max(ans, cnt);
        	}
        }
        System.out.println(ans);
    }
    
    static int solve(int y,int x) {
    	int ret = 1;
    	
    	for(int dir = 0; dir < 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		if(OOB(ny,nx) || board[ny][nx] == 0)
    			continue;
    		
    		int id = idBoard[ny][nx];
    		
    		if(visited[id])
    			continue;
    		
    		ret += sizes.get(id);
    		visited[id] = true;
    	}
    	
    	for(int dir = 0; dir < 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		if(OOB(ny,nx) || board[ny][nx] == 0)
    			continue;
    		
    		int id = idBoard[ny][nx];
    		
    		visited[id] = false;
    	}
    	
    	return ret;
    }
    
    static boolean visited[];
    
    static int id;
    static ArrayList<Integer> sizes = new ArrayList<>();
    
    static int bfs(int y,int x, int id) {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {y,x});
    	idBoard[y][x] = id;
    	int size = 0;
    	while(!q.isEmpty()) {
    		int cur[] = q.poll();
    		size++;
    		
    		for(int dir = 0; dir < 4; dir++) {
    			int ny = cur[0] + dy[dir];
    			int nx = cur[1] + dx[dir];
    			
    			if(OOB(ny,nx) || idBoard[ny][nx] != 0 || board[ny][nx] == 0)
    				continue;
    			idBoard[ny][nx] = id;
    			q.add(new int[] {ny,nx});
    		}
    		
    	}
    	
    	return size;
    	
    }
    
    static void printBoard(int board[][]) {
    	for(int y= 0; y<n; y++) {
    		for(int x=0; x<m; x++) {
    			System.out.printf("%3d", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    static int idBoard[][];
    static int n,m;
    static StringTokenizer st;
    static int board[][];
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<0 || y>=n || x<0 || x>=m;
    }
}