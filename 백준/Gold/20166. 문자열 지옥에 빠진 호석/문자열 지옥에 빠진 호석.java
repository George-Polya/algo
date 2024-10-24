import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	board = new char[n+1][m+1];
    	for(int y=1; y<=n; y++) {
    		String str = br.readLine();
    		for(int x=1; x<=m; x++) {
    			board[y][x] = str.charAt(x-1);
    		}
    	}
    	
    	init();
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < k ;i++) {
    		String str = br.readLine();
    		sb.append(map.get(str) == null ? 0 : map.get(str)).append('\n');
    	}
    	System.out.println(sb);
    	
    }

    static void init() {
    	for(int cnt = 1; cnt<=5; cnt++) {
    		for(int y=1; y<=n; y++) {
    			for(int x=1; x<=m; x++) {
    				dfs(y,x,cnt, 1, String.valueOf(board[y][x]));
    			}
    		}
    	}
    }
    
    static void dfs(int y,int x, int limit, int cnt, String str) {
    	if(cnt == limit) {
    		map.put(str, map.getOrDefault(str, 0)+1);
    		return;
    	}
    	
    	for(int dir = 0; dir < 8; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		ny = (ny - 1 + n) % n + 1;
    		nx = (nx - 1 + m) % m + 1;
    		
    		dfs(ny,nx, limit, cnt+1, str + board[ny][nx]);
    	}
    }
    static HashMap<String,Integer> map = new HashMap<>();
    
    static int dy[] = {-1,-1,0,1,1,1,0,-1};
    static int dx[] = {0,1,1,1,0,-1,-1,-1};
    
    static StringTokenizer st;
    static int n,m,k;
    static char board[][];
}