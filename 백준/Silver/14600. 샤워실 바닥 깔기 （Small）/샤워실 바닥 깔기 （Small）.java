import java.io.*;
import java.util.*;

public class Main {
	static int k,n;
	static int ey,ex;
	static StringTokenizer st;
	static int board[][];
	static int cnt;
	
	static boolean check(int sy,int sx, int size) {
		for(int y = sy; y <= sy + size -1; y++) {
			for(int x= sx; x<=sx + size - 1; x++) {
				if(board[y][x] != 0)
					return false;
			}
		}
		return true;
	}
	
	static void solve(int y,int x,int size) {
		cnt++;
		int half = size / 2;
		
		if(check(y,x,half))
			board[y+half-1][x+half-1] = cnt;
		
		if(check(y,x+half,half))
			board[y+half-1][x+half] = cnt;
		
		if(check(y+half, x, half))
			board[y+half][x+half-1] = cnt;
		
		if(check(y+half, x+half, half))
			board[y+half][x+half] = cnt;
		
		if(size == 2)
			return;
		
		solve(y,x,half);
		solve(y,x+half,half);
		solve(y+half, x,half);
		solve(y+half,x+half,half);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		n = (1 << k);
		st = new StringTokenizer(br.readLine());
		ey = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][n+1];
		board[ey][ex] = -1;
		
		solve(1,1,n);
		
		StringBuilder sb = new StringBuilder();
		for(int x = n; x>=1; x--) {
			for(int y = 1; y<=n; y++) {
				sb.append(board[y][x]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}