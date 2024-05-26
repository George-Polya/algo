import java.util.*;
import java.io.*;
public class Main {
	static int n,m;
	static int board[][];
	static boolean visited[][];
	static StringTokenizer st;
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	static Queue<Pair> q;
	static void initialize() {
		for(int y=1; y<=n; y++) {
			Arrays.fill(visited[y], false);
		}
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	static boolean OOB(int y,int x) {
		return y<=0 || y> n|| x<=0 || x>m;
	}

	static void dfs(int y,int x) {
		for(int dir = 0; dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) || board[ny][nx] != 0 || visited[ny][nx])
				continue;
			visited[ny][nx] = true;
			dfs(ny,nx);
		}
	}
	
	static int ans;
	static int wallCnt = 3;
	static int solve() {
		initialize();
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				if(board[y][x] == 2) {
					visited[y][x] = true;
					dfs(y,x);
				}
			}
		}
		int size = 0;
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				if(board[y][x] == 0 && !visited[y][x])
					size++;
			}
		}
//		System.out.println(size);

		return size;
	}
	
	static List<Pair> fires = new ArrayList<>();
	static List<Pair> blanks = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if(board[y][x] == 2)
					fires.add(new Pair(y,x));
				if(board[y][x] == 1)
					wallCnt++;
				if(board[y][x] == 0)
					blanks.add(new Pair(y,x));
					
			}
		}
		
		for(int i = 0; i <blanks.size(); i++) {
			for(int j = i + 1; j<blanks.size(); j++) {
				for(int k = j + 1; k < blanks.size();k++) {
					Pair pair1 = blanks.get(i);
					Pair pair2 = blanks.get(j);
					Pair pair3 = blanks.get(k);
					
					board[pair1.y][pair1.x] = 1;
					board[pair2.y][pair2.x] = 1;
					board[pair3.y][pair3.x] = 1;
					
					ans = Math.max(ans, solve());
					
					board[pair1.y][pair1.x] = 0;
					board[pair2.y][pair2.x] = 0;
					board[pair3.y][pair3.x] = 0;
					
					
				}
			}
		}
		
		
		System.out.println(ans);
	}

}