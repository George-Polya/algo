import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				dist[y][x] = INF;
			}
		}
		
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		dist[1][1] = 0;
		pq.add(new Tuple(1,1,0));
		
		while(!pq.isEmpty()) {
			Tuple cur = pq.poll();
			
			if(cur.y == N && cur.x == N) {
				System.out.println(cur.cost);
				return;
			}
			
			if(dist[cur.y][cur.x] < cur.cost)
				continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx))
					continue;
				
				int slope = Math.abs(board[cur.y][cur.x] - board[ny][nx]);
				int newSlope = Math.max(cur.cost, slope);
				
				if(dist[ny][nx] > newSlope) {
					dist[ny][nx] = newSlope;
					pq.add(new Tuple(ny,nx, newSlope));
				}
			}
			
		}
		
		System.out.println(-1);
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>N || x<=0 || x>N;
	}
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static int dist[][];
	static class Tuple implements Comparable<Tuple>{
		int y,x,cost;
		
		public Tuple(int y,int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		
		public int compareTo(Tuple o) {
			return cost - o.cost;
		}
	}
	
	static StringTokenizer st;
	static int N;
	static int board[][];
}