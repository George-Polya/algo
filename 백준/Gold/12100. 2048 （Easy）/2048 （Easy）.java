import java.util.*;
import java.io.*;



public class Main {
	static StringTokenizer st;
	static int n;
	static int board[][], temp[][];
	static int moveDirs[] = new int[5];
	static int ans;
	
	static void initialize() {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				board[y][x] = temp[y][x];
		}
	}
	
	static void rotateLeft90() {
		int temp2[][] = new int[n+1][n+1];
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				temp2[y][x] = board[x][n+1-y];
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				board[y][x] = temp2[y][x];
		}
		
	}
	
	static void rotateRight90() {
		int temp2[][] = new int[n+1][n+1];
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				temp2[y][x] = board[n+1-x][y];
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				board[y][x] = temp2[y][x];
		}
		
	}
	
	static void reverseTilt(int dir) {
		if(dir == 0) {
			rotateLeft90();
			rotateLeft90();
		}else if(dir == 1) {
			rotateRight90();
		}else if(dir == 2) {
			return;
		}else if(dir == 3) {
			rotateLeft90();
		}
	}
	
	
	static void tilt(int dir) {
		if(dir == 0) {
			rotateRight90();
			rotateRight90();
		}else if(dir == 1) {
			rotateLeft90();
		}else if(dir == 2) {
			return;
		}else if(dir == 3) {
			rotateRight90();
		}
	}
	
	
	static void fall() {
		int temp2[][] = new int[n+1][n+1];
		
		for(int x = 1; x<=n; x++) {
			int idx = n;
			for(int y = n; y>= 1; y--) {
				if(board[y][x] != 0) {
					temp2[idx][x] = board[y][x];
					idx--;
				}
			}
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				board[y][x] = temp2[y][x];
		}
	}
	
	static int findEnd(int start,int x) {
		if(board[start-1][x] == board[start][x])
			return start-1;
		return start;
	}
	
	
	static void explode() {
		for(int x= 1; x<=n; x++) {
			for(int start = n ; start>=1; start--) {
				if(board[start][x] == 0)
					continue;
				
				int end = findEnd(start, x);
				
				if(start-end == 1) {
					board[start][x] *= 2;
					board[end][x] = 0;
				}
				
			}
		}
	}
	
	
	static int getMax() {
		int ret = 0;
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				ret = Math.max(ret, board[y][x]);
		}
		return ret;
	}
	
	
	
	static void solve(int cur) {
		if(cur == 5) {
			initialize();
			
			for(int i = 0; i<5;i++) {
				tilt(moveDirs[i]);
				fall();
				explode();
				fall();
				reverseTilt(moveDirs[i]);
			}
			
			ans = Math.max(ans, getMax());
			
			return;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			moveDirs[cur] = dir;
			solve(cur + 1);
		}
	}
	
	static void printBoard(int board[][]) {
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				sb.append(board[y][x]).append(' ');
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1];
		temp = new int[n+1][n+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				temp[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
//		initialize();
//		tilt(1);
//		printBoard(board);
//		System.out.println("-----");
//		fall();
//		printBoard(board);
//		System.out.println("-----");
//		explode();
//		printBoard(board);
//		System.out.println("-----");
//		fall();
//		printBoard(board);
//		System.out.println("-----");
//		reverseTilt(1);
//		printBoard(board);
//		System.out.println("-----");
		
		
		
		solve(0);
		System.out.println(ans);
		
	}
}