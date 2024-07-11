import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int n,m, t;
	static int board[][], dist[][];
	static class Tuple{
		int y,x, value;
		public Tuple(int y,int x,int value) {
			this.y = y;
			this.x = x;
			this.value = value;
		}
	}
	static int INF = 987654321;
	
	static int calc(int y1,int x1, int y2, int x2) {
		return Math.abs(y1-y2) + Math.abs(x1-x2);
	}
	static int WALL = 1;
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		board = new int[n+1][m+1];
		dist = new int[n+1][m+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				dist[y][x] = INF;
			}
		}
		
		Queue<Tuple> q = new LinkedList<>();
		dist[1][1] = 0;
		q.add(new Tuple(1,1,board[1][1]));
		Tuple tuple = null;
		while(!q.isEmpty()) {
			Tuple cur = q.poll();
			
			if(cur.value == 2) {
				tuple = cur;
			}
			
			for(int dir = 0; dir< 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx) || board[ny][nx] == WALL || dist[ny][nx] != INF)
					continue;
				
				q.add(new Tuple(ny,nx,board[ny][nx]));
				dist[ny][nx] = Math.min(dist[ny][nx], dist[cur.y][cur.x] + 1);
			}
		}
		if(tuple != null)
			dist[n][m] = Math.min(dist[n][m], dist[tuple.y][tuple.x] + calc(tuple.y, tuple.x, n,m));
		System.out.println(dist[n][m] <= t ? dist[n][m] : "Fail");
	}
}