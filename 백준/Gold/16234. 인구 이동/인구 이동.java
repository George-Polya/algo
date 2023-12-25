import java.io.*;
import java.util.*;
/**
 * while 이동이 있는 한 :
 * 	visited초기화 	
 * 	모든 좌표에서 bfs => 인구이동 
 * 여러곳에서 인구이동이 발생해도 한 번이라고 간주  
 */
public class Main {
	static int n,l,r;
	static StringTokenizer st;
	static int board[][];
	static boolean visited[][];
	static void initialize() {
		for(int y=1; y<=n; y++) {
			Arrays.fill(visited[y], false);
		}
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>n;
	}
	
	static boolean exist(int y,int x) {
		for(int dir = 0; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(OOB(ny,nx))
				continue;
			
			int abs = Math.abs(board[y][x] - board[ny][nx]);
			if(l<= abs && abs <= r)
				return true;
		}
		return false;
	}
	
	static boolean end() {
		for(int y=1;y<=n;y++) {
			for(int x=1; x<=n; x++) {
				if(exist(y,x))
					return false;
			}
		}
		return true;
	}
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public String toString() {
			return y+" "+x+"|";
		}
	}
	
	static void bfs(int y,int x) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x));
		visited[y][x] = true;
		int size = 0;
		int sum = 0;
		ArrayList<Pair> union = new ArrayList<>();
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			size++;
			sum += board[cur.y][cur.x];
			union.add(cur);
			
			for(int dir = 0; dir< 4;dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx) || visited[ny][nx])
					continue;
				
				int abs = Math.abs(board[ny][nx] - board[cur.y][cur.x]);
				if(l<=abs && abs<=r) {
					visited[ny][nx] = true;
					q.add(new Pair(ny,nx));
				}
			}
		}
		
		for(Pair nation : union) {
			board[nation.y][nation.x] = sum / size;
		}
	}
	
	static void printBoard(int board[][]) {
		int n = board.length-1;
		int m = board[1].length - 1;
		for(int y = 1; y<=n; y++) {
			for(int x = 1; x<=m; x++) {
				System.out.printf("%-4d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	l = Integer.parseInt(st.nextToken());
	 	r = Integer.parseInt(st.nextToken());
	 	board = new int[n+1][n+1];
	 	for(int y=1; y<=n; y++) {
	 		st = new StringTokenizer(br.readLine());
	 		for(int x=1; x<=n; x++) {
	 			board[y][x] = Integer.parseInt(st.nextToken());
	 		}
	 	}
	 	
	 	visited = new boolean[n+1][n+1];
	 	int cnt = 0;
	 	
//	 	printBoard(board);
//	 	System.out.println("-----");
	 	while(!end()) {
	 		initialize();
	 		
	 		for(int y=1; y<=n; y++) {
	 			for(int x=1; x<=n; x++) {
	 				if(visited[y][x])
	 					continue;
	 				bfs(y,x);
	 			}
	 		}
	 		
//	 		printBoard(board);
//		 	System.out.println("-----");
	 		
	 		cnt++;
	 	}
	 	System.out.println(cnt);
	}
}