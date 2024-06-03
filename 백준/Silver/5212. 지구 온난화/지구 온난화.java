import java.io.*;
import java.util.*;

public class Main {
	
	static int r,c;
	static char board[][], nxtBoard[][];
	static StringTokenizer st;
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>r || x<= 0|| x>c;
	}
	
	static boolean check(int y,int x) {
		int cnt = 0;
		for(int dir =0;dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(OOB(ny,nx)||board[ny][nx] == '.')
				cnt++;
		}
		return cnt == 3 || cnt == 4;
	}
	
	static void solve() {
		for(int y=1; y<=r;y++) {
			for(int x=1; x<=c;x++) {
				if(check(y,x)) {
					nxtBoard[y][x] = '.';
				}
			}
		}
	}
	
	static void printBoard(char board[][]) {
		for(int y=1; y<=r; y++) {
			for(int x=1; x<=c;x++)
				System.out.print(board[y][x]);
			System.out.println();
		}
	}
	
	static boolean allSea(char arr[]) {
		for(int x = 1; x<=c;x++) {
			if(arr[x] == 'X')
				return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
	
		board = new char[r+1][c+1];
		nxtBoard = new char[r+1][c+1];
		for(int y=1;y<=r;y++) {
			String str = br.readLine();
			for(int x=1; x<=c; x++) {
				board[y][x] = str.charAt(x-1);
				nxtBoard[y][x] = board[y][x];
			}
		}
		
		solve();
		board = nxtBoard;
		
		int minY = r + 1;
		int maxY = 1;
		int minX = c + 1;
		int maxX = 1;
		
		for(int y=1;y<=r;y++) {
			for(int x=1; x<=c; x++) {
				if(board[y][x] == 'X') {
					if(y < minY)
						minY = y;
					if(y > maxY)
						maxY = y;
					if(x < minX)
						minX = x;
					if(x > maxX)
						maxX = x;
				}
			}
		}
		
//		printBoard(board);
//		System.out.println("----");
		StringBuilder sb = new StringBuilder();
		for(int y= minY; y<=maxY; y++) {
			for(int x=minX; x<=maxX; x++)
				sb.append(board[y][x]);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}