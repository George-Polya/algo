import java.io.*;
import java.util.*;

public class Main {
	static int n,h,d;
	static int board[][];
	static int BLANK = 0;
	static int END = -1;
	static int START = -2;
	static int INF = Integer.MAX_VALUE;
	static int dist[][][];
	static StringTokenizer st;
	static int sy, sx;
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				System.out.printf("%3d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>n;
	}
	
	static class State{
		int y,x,h,d,u; // 좌표, 체력, 내구도, 우산
		public State(int y,int x, int h, int d, int u) {
			this.y = y;
			this.x = x;
			this.h = h;
			this.d = d;
			this.u = u;
		}
		
		public String toString() {
			return String.format("(%d,%d), h: %d, d: %d, u: %d", y,x,h,d,u);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        board = new int[n+1][n+1];
        dist = new int[11][n+1][n+1];
        int idx = 1;
        for(int y=1; y<=n; y++) {
        	String str = br.readLine();
        	for(int x=1; x<=n; x++) {
        		char ch = str.charAt(x-1);
        		if(ch == 'S') {
        			sy = y;
        			sx = x;
        			board[y][x] = START;
        		}else if(ch == 'U') {
        			board[y][x] = idx++;
        		}else if(ch == 'E') {
//        			ey = y;
//        			ex = x;
        			board[y][x] = END;
        		}
        	}
        }
        
        for(int i = 0; i<=10;i++) {
        	for(int y = 0; y<=n; y++) {
        		Arrays.fill(dist[i][y], INF);
        	}
        }
        
        
        
        dist[0][sy][sx] = 0;
        Queue<State> q = new LinkedList<>();
        q.add(new State(sy,sx,h,0,0)); 
        
        while(!q.isEmpty()){
        	State cur = q.poll();
        	if(cur.h == 0 && cur.d == 0)
        		continue;
        	if(board[cur.y][cur.x] == END) {
        		System.out.println(dist[cur.u][cur.y][cur.x]);
        		System.exit(0);
        	}
        	
        	for(int dir = 0; dir < 4; dir++) {
        		int ny = cur.y + dy[dir];
        		int nx = cur.x + dx[dir];
        		if(OOB(ny,nx) || dist[cur.u][ny][nx] != INF)
        			continue;
        		if(board[ny][nx] > 0) {
        			int nu = board[ny][nx];
        			dist[nu][ny][nx] = Math.min(dist[nu][ny][nx], dist[cur.u][cur.y][cur.x] + 1);
        			board[ny][nx] = 0;
        			q.add(new State(ny,nx,cur.h, d-1, nu));
        		}else if(board[ny][nx] == BLANK) {
        			dist[cur.u][ny][nx] = Math.min(dist[cur.u][ny][nx], dist[cur.u][cur.y][cur.x] + 1);
        			if(cur.d != 0) {
        				q.add(new State(ny,nx,cur.h, cur.d - 1, cur.u));
        			}else if(cur.h > 0) {
        				q.add(new State(ny,nx,cur.h-1, cur.d,cur.u));
        			}
        		}else if(board[ny][nx] == END || board[ny][nx] == START) {
        			dist[cur.u][ny][nx] = Math.min(dist[cur.u][ny][nx], dist[cur.u][cur.y][cur.x] + 1);
        			q.add(new State(ny,nx,cur.h, cur.d, cur.u));
        		}
        	}
        }
        
        System.out.println(-1);
    }
}