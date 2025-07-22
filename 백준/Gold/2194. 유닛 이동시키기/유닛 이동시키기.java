import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][M+1];
		pSum = new int[N+1][M+1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			board[y][x] = 1;
		}
		
		prefixSumInit();
		
		
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int ey = Integer.parseInt(st.nextToken());
		int ex = Integer.parseInt(st.nextToken());
		
		
		System.out.println(bfs(sy,sx, ey,ex));
	}
	
	static int bfs(int sy, int sx ,int ey, int ex) {
		Queue<Pair> q = new ArrayDeque<>();
		int dist[][] = new int[N+1][M+1];
		for(int row[]: dist) {
			Arrays.fill(row,  -1);
		}
		
		q.add(new Pair(sy,sx));
		dist[sy][sx] = 0;
		
		if(check(sy,sx, sy + H - 1, sx + W - 1))
			return -1;
		
		if(check(ey,ex, sy + H - 1, ex + W - 1))
			return -1;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			if(cur.y == ey && cur.x == ex)
				return dist[ey][ex];
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx, ny + H - 1, nx + W - 1) || dist[ny][nx] != -1 || check(ny,nx, ny + H - 1, nx + W -1))
					continue;
				
				dist[ny][nx] = dist[cur.y][cur.x] + 1;
				q.add(new Pair(ny,nx));
				
				
			}
		}
		
		return -1;
		
	}
	
	static void prefixSumInit() {
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=M; x++) {
				pSum[y][x] = board[y][x] + pSum[y-1][x] + pSum[y][x-1] - pSum[y-1][x-1];
			}
		}
	}
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,-1,0,1};
	static boolean OOB(int y1,int x1, int y2, int x2) {
		return y1 <= 0 || y2 > N || x1 <= 0 || x2 > M;
	}
	
	static boolean check(int y1, int x1, int y2, int x2) {
		int cnt = pSum[y2][x2] - pSum[y1-1][x2] - pSum[y2][x1-1] + pSum[y1-1][x1-1];
		return cnt > 0;
	}
	
	static int pSum[][], board[][];
	static StringTokenizer st;
	static int N,M,H,W,K;
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}

