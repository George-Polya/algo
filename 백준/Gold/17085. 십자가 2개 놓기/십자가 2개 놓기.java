import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N+1][M+1];
		for(int y=1; y<=N; y++) {
			String line = br.readLine();
			for(int x=1; x<=M; x++) {
				board[y][x] = line.charAt(x-1);
				if(board[y][x] == '#')
					pairs.add(new int[] {y,x});
			}
		}
		int temp = Math.min(N, M);
		MAX_N = temp / 2;
				
//		printBoard(board);
		solve(0, 1);
		System.out.println(ans);
	}
	static void solve(int cnt, int area) {
		if(cnt == 2) {
			if(ans < area) {
				ans = Math.max(ans, area);
			}
			return;
		}
		
		for(int[] pair : pairs) {
			for(int n = 0; n <= MAX_N; n++) {
				if(possible(pair[0], pair[1], n)) {
					cover(pair[0], pair[1],n , '*');
					solve(cnt+1, area * calc(n));
					cover(pair[0], pair[1],n , '#');
				}
			}
		}
		
	}
	
	static int calc(int n) {
		return 4 * n + 1;
	}
	
	static boolean possible(int cy, int cx, int n) {
		if(board[cy][cx] != '#')
			return false;
		
		for(int num = 1; num<=n; num++) {
			for(int dir = 0; dir < 4; dir++) {
				int ny = cy + dy[dir] * num;
				int nx = cx + dx[dir] * num;
				
				if(OOB(ny,nx) || board[ny][nx] != '#')
					return false;
			}
		}
		return true;
	}
	
	static void cover(int cy, int cx, int n, char ch) {
		board[cy][cx] = ch;
		for(int num = 1; num<=n; num++) {
			for(int dir = 0; dir < 4; dir++) {
				int ny = cy + dy[dir] * num;
				int nx = cx + dx[dir] * num;
				
				board[ny][nx] = ch;
			}
		}
	}
	static int MAX_N = 0;
	static void printBoard(char board[][]) {
		for(int y=1;y<=N; y++) {
			for(int x=1; x<=M; x++) {
				System.out.printf("%3s", board[y][x]);
			}
			System.out.println();
		}
	}
	
	static List<int[]> pairs = new ArrayList<>();
	static int ans;
	static StringTokenizer st;
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>N || x<=0 || x>M;
	}
	static int N,M;
	static char board[][];
}