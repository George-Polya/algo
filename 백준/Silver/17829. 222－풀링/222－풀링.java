import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int board[][];
	static StringTokenizer st;
	
	static void printBoard(int board[][]) {
		int n = board.length;
		int m = board[0].length;
		for(int y=0; y<n; y++) {
			for(int x=0;x<m;x++) {
				System.out.printf("%-4d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	static int getMax2(int sy, int ey, int sx,int ex,int board[][]) {
		int ret[] = new int[4];
		int cnt = 0;
		for(int y=sy; y<=ey; y++) {
			for(int x=sx;x<=ex;x++) {
				ret[cnt] = board[y][x];
				cnt++;
			}
		}
		
		Arrays.sort(ret);
		return ret[2];
	}
	
	static void solve(int sy,int sx ,int board[][], int size){
		if(size == 1) {
			System.out.println(board[0][0]);
			return;
		}
		int ret[][] = new int[size/2][size/2];
		for(int y=sy; y < sy+size; y+=2) {
			for(int x= sx; x<sx+size; x+=2) {
				int max2 = getMax2(y,y+1, x,x+1, board);
				ret[y/2][x/2] = max2;
			}
		}
		
//		printBoard(ret);
		
		solve(0,0,ret,size/2);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		
		for(int y=0; y<n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<n; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
//		int ret[][] = solve(0,0,board,n);
		solve(0,0,board,n);
	}
}