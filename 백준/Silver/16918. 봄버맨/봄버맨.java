import java.io.*;
import java.util.*;

public class Main {
	static int r,c,n;
	static int board[][];
	static StringTokenizer st;
	
	static void timeFly() {
		for(int y=1; y<=r; y++) {
			for(int x=1; x<=c;x++) {
				if(board[y][x] > 0)
					board[y][x]--;
			}
		}
	}
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=r;y++) {
			for(int x=1; x<=c;x++) {
				System.out.print(board[y][x]+" ");
			}
			System.out.println();
		}
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y> r || x<=0 || x> c;
	}
	
	static void explode() {
		for(int y=1; y<=r;y++) {
			System.arraycopy(board[y], 1, nxtBoard[y], 1, c);
		}
		
		for(int y=1; y<=r;y++) {
			for(int x=1;x<=c;x++) {
				if(board[y][x] == 0) {
					for(int dir = 0; dir<4;dir++) {
						int ny = y + dy[dir];
						int nx = x + dx[dir];
						if(OOB(ny,nx))
							continue;
						nxtBoard[ny][nx] = 0;
					}
				}
			}
		}
		
//		System.out.println("nxtBoard: ");
//		printBoard(nxtBoard);
		
		for(int y=1; y<=r;y++) {
			System.arraycopy(nxtBoard[y], 1, board[y], 1, c);
		}
	}
	
	
	static void addBomb() {
		for(int y=1; y<=r; y++) {
			for(int x=1; x<=c; x++) {
				if(board[y][x] == 0)
					board[y][x] = 3;
			}
		}
	}
	
	static int nxtBoard[][];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		board = new int[r+1][c+1];
		nxtBoard = new int[r+1][c+1];
		for(int y=1; y<=r;y++) {
			String str = br.readLine();
			for(int x=1; x<=c;x++) {
				char ch = str.charAt(x-1);
//				System.out.println(ch);
				if(ch == 'O') {
					board[y][x] = 3;
				}
			}
		}
//		printBoard(board);
		timeFly();
//		System.out.println("-----");
//		printBoard(board);
		
		for(int t=2;t<=n;t++) {
//			System.out.println("-----");
//			System.out.println(t);
			timeFly();
//			printBoard(board);
			if(t % 2 == 0) {
				addBomb();
			}else {
				explode();
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int y=1;y<=r;y++) {
			for(int x=1; x<=c;x++) {
				if(board[y][x] > 0)
					sb.append('O');
				else
					sb.append('.');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}