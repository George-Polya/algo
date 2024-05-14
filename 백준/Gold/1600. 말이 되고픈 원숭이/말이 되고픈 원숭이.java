import java.util.*;
import java.io.*;
public class Main {
	static int k, w, h;
	static int board[][];
	static int dist[][][];
	static StringTokenizer st;
	
	static void initialize() {
		dist = new int[k+1][h+1][w+1];
		for(int z = 0; z<=k; z++) {
			for(int y=1; y<=h; y++) {
				for(int x=1; x<=w; x++)
					dist[z][y][x] = -1;
			}
		}
	}
	
	static class Tuple{
		int y,x, count;
		public Tuple(int y,int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}
	
	static boolean OOB(int y,int x) {
		return y<=0 || y> h || x<=0 ||x > w;
	}
	
	static void bfs() {
		initialize();
		
		Queue<Tuple> q = new ArrayDeque<>();
		q.add(new Tuple(1,1,k));
		dist[k][1][1] = 0;
		
		int dy[] = null;
		int dx[] = null;
		
		while(!q.isEmpty()) {
			Tuple cur = q.poll();
			
			if(cur.count != 0) {
				dy = new int[] {-2,-2,-1,-1,1,1,2,2};
				dx = new int[] {-1,1,-2,2,-2,2,-1,1};
				
				for(int dir = 0; dir < 8; dir++) {
					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					int nCount = cur.count - 1;
					if(OOB(ny,nx) || board[ny][nx] == 1)
						continue;
					
					
					if(dist[nCount][ny][nx] != -1)
						continue;
					
//					System.out.printf("%d %d\n", nCount, cur.count);
					dist[nCount][ny][nx] = dist[cur.count][cur.y][cur.x] + 1;
					q.add(new Tuple(ny,nx, nCount));
					
				}
			}
			
			
			dy = new int[] {-1,1,0,0};
			dx = new int[] {0,0,-1,1};
			for(int dir = 0; dir< 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				int nCount = cur.count;
				if(OOB(ny,nx) || board[ny][nx] == 1)
					continue;
				
				if(dist[nCount][ny][nx] != -1)
					continue;
				
//				System.out.printf("%d %d\n", nCount, cur.count);
				dist[nCount][ny][nx] = dist[cur.count][cur.y][cur.x] + 1;
				q.add(new Tuple(ny,nx, nCount));
			}
			
		}
	}
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=h; y++) {
			for(int x=1; x<=w;x++) {
				System.out.printf("%-3d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine()); 
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		board = new int[h+1][w+1];
		for(int y=1; y<=h;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=w;x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		
		int ans = Integer.MAX_VALUE;
		for(int i = k; i>=0;i--) {
			if(dist[i][h][w] != -1)
				ans = Math.min(ans, dist[i][h][w]);
		}
		
		if(ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
		

		
	}

}