import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		dist = new int[3][N+1][N+1];
		
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				for(int i = 0; i <3 ;i++) {
					dist[i][y][x] = INF;
				}
			}
		}
		
		PriorityQueue<State> pq = new PriorityQueue<>();
		pq.add(new State(1,1,0,0));
		dist[0][1][1] = 0;
		
		while(!pq.isEmpty()) {
			State cur = pq.poll();
			
			if(dist[cur.cnt][cur.y][cur.x] < cur.time)
				continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx))
					continue;
				
				int temp = cur.time + T;
				if(cur.cnt == 2)
					temp += board[ny][nx];
				
				int nxt = (cur.cnt + 1) % 3;
				if(dist[nxt][ny][nx] > temp) {
					dist[nxt][ny][nx] = temp;
					pq.add(new State(ny,nx, nxt, temp));
				}
			}
		}
		
		int ans = INF;
		for(int i = 0; i < 3; i++) {
			ans = Math.min(ans, dist[i][N][N]);
		}
		System.out.println(ans);
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>N || x<=0 || x>N;
	}
	
	static class State implements Comparable<State>{
		int y,x, cnt, time;
		
		public State(int y,int x, int cnt, int time) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.time = time;
		}
		
		public int compareTo(State o) {
			return time - o.time;
		}
		
		public String toString() {
			return String.format("[%d,%d] | cnt: %d | time: %d", y,x,cnt,time);
		}
	}
	
	static StringTokenizer st;
	static int N,T;
	static int board[][], dist[][][];
	static int INF = Integer.MAX_VALUE;
}

