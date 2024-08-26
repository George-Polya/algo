import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		board = new int[h+2][w+2];
		visited = new boolean[h+2][w+2];
		for(int y=1;y<=h; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=w; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
//				if(board[y][x] == 1)
//					visited[y][x] = true;
			}
		}
		
		counts = new int[h+2][w+2];
		
		bfs(0,0);
		int ans = 0;
		for(int y=0; y<=h+1; y++) {
			for(int x=0; x<=w+1; x++)
				ans += counts[y][x];
		}
		
		System.out.println(ans);
		
		
		
	}
	static int w,h;
	static int board[][];
	static int counts[][];
	static StringTokenizer st;
	static int dy[] = {-1,-1,0,1,1,0};
	static int dx1[] = {0,1,1,1,0,-1};
	static int dx2[] = {-1,0,1,0,-1,-1};
	static boolean OOB(int y,int x) {
		return y< 0 || y >= h + 2 || x<0 || x>=w+2;
	}
	
	static boolean visited[][];
	static void bfs(int sy,int sx) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sy,sx));
		
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int dir = 0; dir< 6;dir++) {
				Pair nxt = getNxtPos(cur, dir);
				if(OOB(nxt.y, nxt.x))
					continue;
				
				if(board[nxt.y][nxt.x] == 1) {
					counts[cur.y][cur.x]++;
					continue;
				}
				
				if(visited[nxt.y][nxt.x])
					continue;
				visited[nxt.y][nxt.x] = true;
				q.add(nxt);
			}
		}
		
		
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
	
	static Pair getNxtPos(Pair cur,int dir) {
		if(cur.y % 2 == 1) {
			int ny = cur.y + dy[dir];
			int nx = cur.x + dx1[dir];
			return new Pair(ny,nx);
		}else {
			int ny = cur.y + dy[dir];
			int nx = cur.x + dx2[dir];
			return new Pair(ny,nx);
		}
	}
	
}