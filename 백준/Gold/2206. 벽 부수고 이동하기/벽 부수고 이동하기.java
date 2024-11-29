import java.io.*;
import java.util.*;
public class Main {
	static int n,m;
	static StringTokenizer st;
	static char board[][];
	static int dist[][][];
	
	static class Tuple{
		int y,x,broken;
		
		public Tuple(int y,int x, int broken) {
			this.y = y;
			this.x = x;
			this.broken = broken;
		}
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	static final int INT_MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n+1][m+1];
		for(int y=1; y<=n; y++) {
			String str = br.readLine();
			for(int x=1; x<=m; x++)
				board[y][x] = str.charAt(x-1);
		}
		
		dist = new int[n+1][m+1][2];
		for(int i = 0; i<=n;i++) {
			for(int j = 0; j<=m; j++)
				Arrays.fill(dist[i][j], INT_MAX);
		}
		
		Queue<Tuple> q = new LinkedList<>();
		q.add(new Tuple(1,1,0));
		dist[1][1][0] = 1;
//		dist[1][1][1] = 1;
		
		
		while(!q.isEmpty()) {
			Tuple cur = q.poll();
			
//			if(cur.y == n && cur.x == m) {
//				System.out.println(dist[cur.y][cur.x][cur.broken]);
//				return;
//			}
			
			for(int dir = 0; dir<4;dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				int nBroken = cur.broken;
				if(OOB(ny,nx))
					continue;
				
				if(cur.broken == 0 && board[ny][nx] == '1' && dist[ny][nx][1] == INT_MAX) {
					dist[ny][nx][1] = dist[cur.y][cur.x][cur.broken] + 1;
					q.add(new Tuple(ny,nx,1));
				}else if(board[ny][nx] == '0' && dist[ny][nx][nBroken] == INT_MAX) {
					dist[ny][nx][nBroken] = dist[cur.y][cur.x][cur.broken] + 1;
					q.add(new Tuple(ny,nx,nBroken));
				}
			}
		}
		
//		System.out.println(dist[n][m][0]+" "+dist[n][m][1]);
		int ans = Math.min(dist[n][m][0], dist[n][m][1]);
		if(ans == INT_MAX)
			ans = -1;
		System.out.println(ans);
	}
}