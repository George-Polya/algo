import java.io.*;
import java.util.*;
public class Solution {
	static int T,n,m;
	static int board[][];
	static StringTokenizer st;
	static int ans;
	static boolean visited[][];
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>n;
	}
	
	static int bfs(int y,int x,int k) {
		// 매번 visited배열 초기화
		for(int i=1; i<=n;i++)
			Arrays.fill(visited[i], false);
		
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(y,x));
		visited[y][x] = true;
		int dist = 0; // 각 위치에서 중심에서부터 떨어진 최단거리
		int cnt = 0; // k가 주어졌을때 중심으로부터 커버하는 범위에서의 1의 개수
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Pair cur = q.poll();
				if(Math.abs(y - cur.y) + Math.abs(x - cur.x) < k && board[cur.y][cur.x] == 1)
					cnt++;
				if(dist > k)
					return cnt;
				
				for(int dir = 0; dir < 4 ;dir++) {
					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					if(OOB(ny,nx) || visited[ny][nx])
						continue;
					q.add(new Pair(ny,nx));
					visited[ny][nx] = true;
				}
			}
			
			dist++; // 레벨별 bfs
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			board = new int[n+1][n+1];
			visited = new boolean[n+1][n+1];

			int maxProfit = 0;
			for(int y=1; y<=n; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x=1; x<=n; x++) {
					board[y][x] = Integer.parseInt(st.nextToken());
					if(board[y][x] == 1)
						maxProfit += m;
				}
			}
			
			// 각 좌표에서 가능한 K를 모두 시도해본다
			for(int cy=1; cy<=n; cy++) {
				for(int cx=1; cx<=n; cx++) {
					// 가능한 k 는 1부터 2n-1까지 이다.
					for(int k = 1; k< 2 * n ; k++) {
						int cost = k * k + (k-1) * (k-1); // 비용
						if(cost > maxProfit)
							break;

//						int cnt = bfs(cy,cx,k);
						int cnt = 0;
						for(int y = cy - (k-1); y<= cy + (k-1); y++) {
							for(int x= cx - (k-1); x<= cx + (k-1); x++) {
								if(OOB(y,x))
									continue;
								if(Math.abs(cy-y) + Math.abs(cx - x)<k && board[y][x] == 1)
									cnt++;
							}
						}
						int profit = m  * cnt; // 수익
						
						// 수익이 비용보다 크거나 같을때, 즉 손해를 보지 않을때
						if(profit >= cost) {
							ans = Math.max(ans,  cnt);
						}
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", tc,ans));
		}
		System.out.println(sb);
	}

}