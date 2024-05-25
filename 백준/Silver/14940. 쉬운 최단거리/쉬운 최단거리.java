import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static StringTokenizer st;
	static int board[][];
	static int dist[][];
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
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	static void bfs(int y,int x) {
		Queue<Pair> q = new LinkedList<>();
		dist[y][x] = 0;
		q.add(new Pair(y,x));
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int dir = 0; dir<4;dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx) || dist[ny][nx] != -1 || board[ny][nx] == 0)
					continue;
				
				dist[ny][nx] = dist[cur.y][cur.x] + 1;
				q.add(new Pair(ny,nx));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		dist = new int[n+1][m+1];
		Pair start = null;
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m;x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if(board[y][x] == 2) {
					start = new Pair(y,x);
				}
				dist[y][x] = -1;
			}
		}
		
		bfs(start.y, start.x);
		
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				if(board[y][x] == 0)
					sb.append(0).append(' ');
				else {
					sb.append(dist[y][x]).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
}