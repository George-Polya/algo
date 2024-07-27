import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n = 9;
	static int board[][];
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public String toString() {
			return y+" "+x;
		}
	}
	
	static boolean check() {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				if(board[y][x] == 0)
					return false;
			}
		}
		return true;
	}
	
	static Pair NO_PAIR = new Pair(11,11);
	
	static Pair getNxt() {
		for(Pair pair : zeros) {
			if(board[pair.y][pair.x] == 0)
				return pair;
		}
		return NO_PAIR;
	}
	
	static boolean possible(int ty, int tx, int num) {
		for(int y=1; y<=9; y++) {
			if( y != ty && board[y][tx] == num)
				return false;
			
//			if( i != tx && board[ty][i] == num)
//				return false;
		}
		
		for(int x=1;x<=9; x++) {
			if(x != tx && board[ty][x] == num)
				return false;
		}
		
		int sy = (ty % 3 == 0 ) ? ty - 2 : ty / 3 * 3 + 1;
		int sx = (tx % 3 == 0 ) ? tx - 2 : tx / 3 * 3 + 1;
		
		int ey = sy + 2;
		int ex = sx + 2;
		
		for(int y= sy;y<=ey;y++) {
			for(int x=sx; x<=ex;x++) {
				if(board[y][x] == num)
					return false;
			}
		}
		
		return true;
	}
	static void printBoard() {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				System.out.print(board[y][x] +" ");
			System.out.println();
		}
		
	}
	
	static void solve(int ty, int tx) {
//		System.out.println("-----");
//		System.out.printf("(y,x) : (%d,%d)\n", ty,tx);
		if(ty == 11 && tx == 11) {
			printBoard();
			System.exit(0);
		}
		
		for(int num= 1; num<=9; num++) {
			if(!possible(ty,tx,num))
				continue;
			board[ty][tx] = num;
			Pair nxt = getNxt();
//			System.out.println("nxt: "+nxt);
			solve(nxt.y, nxt.x);
			board[ty][tx] = 0;
		}
	}
	
	static List<Pair> zeros = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[n+1][n+1];
		for(int y= 1;y<=n;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n ; x++) {
				board[y][x]= Integer.parseInt(st.nextToken());
				if(board[y][x] == 0)
					zeros.add(new Pair(y,x));
			}
		}
		
		solve(zeros.get(0).y, zeros.get(0).x);
		
		
	}
}