import java.util.*;
import java.io.*;

public class Main {
	static int n,m,r;
	static int board[][];
	static StringTokenizer st;
	
	
	
	static void printBoard(int board[][]) {
		StringBuilder sb = new StringBuilder();
		int n = board.length - 1;
		int m = board[1].length - 1;
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++)
				sb.append(board[y][x]).append(' ');
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void reverseUpDown() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		for(int y = 1; y<=n/2; y++) {
			int temp[] = board[n+1-y];
			board[n+1-y] = board[y];
			board[y] = temp;
		}
		
	}
	
	static void reverseLeftRight() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		for(int y= 1; y<=n; y++) {
			for(int x = 1; x<=m/2; x++) {
				int tmp = board[y][x];
				board[y][x] = board[y][m+1-x];
				board[y][m+1-x] = tmp;
			}
		}
		
	}
	
	static void rotateRight90() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		int temp[][] = new int[m+1][n+1];
		
		for(int y = 1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				 temp[x][n+1-y]= board[y][x];
			}
		}
		
		board = temp;
	}
	
	static void rotateLeft90() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		int temp[][] = new int[m+1][n+1];
		
		for(int y = 1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				 temp[m+1-x][y]= board[y][x];
			}
		}
		
		board = temp;
	}
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static void shiftCounterClockWise() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		int temp[][] = new int[n+1][m+1];
		
		Pair pairs[] = {
				new Pair(1,1),
				new Pair(1, m/2+1),
				new Pair(n/2+1, m/2+1),
				new Pair(n/2+1, 1)
		};
		
		for(int d = 0; d < 4; d++) {
			Pair cur = pairs[d];
			Pair nxt = pairs[(d+1) % 4];
			
			for(int y = 0; y<n/2;y++) {
				for(int x= 0; x<m/2;x++) {
					temp[cur.y+y][cur.x+x] = board[nxt.y+y][nxt.x+x];
				}
			}
			
		}
		
		board = temp;
	}
	
	static void shiftClockWise() {
		int n = board.length - 1;
		int m = board[1].length - 1;
		int temp[][] = new int[n+1][m+1];
		
		Pair pairs[] = {
				new Pair(1,1),
				new Pair(1, m/2+1),
				new Pair(n/2+1, m/2+1),
				new Pair(n/2+1, 1)
		};
		
		for(int d = 0; d < 4; d++) {
			Pair cur = pairs[d];
			Pair nxt = pairs[(d+3) % 4];
			
			for(int y = 0; y<n/2;y++) {
				for(int x= 0; x<m/2;x++) {
					temp[cur.y+y][cur.x+x] = board[nxt.y+y][nxt.x+x];
				}
			}
			
		}
		
		board = temp;
	}
	
	
	static void operation(int op) {
		switch(op) {
		case 1:
			reverseUpDown();
			break;
		case 2:
			reverseLeftRight();
			break;
		case 3:
			rotateRight90();
			break;
		case 4:
			rotateLeft90();
			break;
		case 5:
			shiftClockWise();
			break;
		case 6:
			shiftCounterClockWise();
			break;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		board = new int[n+1][m+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< r;i++) {
			int op = Integer.parseInt(st.nextToken());
			operation(op);
		}
		
		printBoard(board);
		
	}
}