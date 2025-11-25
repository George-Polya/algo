import java.util.*;
import java.io.*;
 class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N+1][M+1];
		visited = new int[N+1][M+1];
		cBoard = new int[N+1][M+1];
		
		
		for(int y=1; y<=N; y++) {
			String line = br.readLine();
			for(int x=1; x<=M; x++) {
				board[y][x] = line.charAt(x-1);
				visited[y][x] = -1;
				
			}
		}
		
		int id = 1;
		
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=M; x++) {
				if(visited[y][x] != -1 || board[y][x] =='1')
					continue;
				int cnt = bfs(y,x,id);
				counts.put(id,cnt);
				id++;
			}
		}
		
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=M; x++) {
				if(board[y][x] == '1') {
					int cnt = 1;
					Set<Integer> seend = new HashSet<>();
					
					for(int dir =0; dir<4; dir++) {
						int ny = y + dy[dir];
						int nx = x + dx[dir];
						
						if(OOB(ny,nx) || board[ny][nx] == '1' || visited[ny][nx] == -1)
							continue;
						
						id = visited[ny][nx];
						
						if(!seend.contains(id)) {
							seend.add(id);

						}
					}
					
					for(int idx : seend) {
						cnt += counts.get(idx);
					}
					cBoard[y][x] = cnt % 10;
				}
			}
		}
		printBoard(cBoard);
		
	}
	static int bfs(int y,int x, int id) {
		Queue<Pair> q = new ArrayDeque<>();
		visited[y][x] = id;
		q.add(new Pair(y,x));
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			cnt++;
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx) || board[ny][nx] == '1' || visited[ny][nx] != -1)
					continue;
				
				visited[ny][nx] = id;
				q.add(new Pair(ny,nx));
					
			}
		}
		return cnt;
	}
	
	static void printBoard(int board[][]) {
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=M; x++) {
				sb.append(board[y][x]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y > N || x<=0 || x>M;
	}
	static Map<Integer, Integer> counts = new HashMap<>();
	
	static int N,M;
	static StringTokenizer st;
	static int visited[][], cBoard[][];
	static char board[][];
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}
