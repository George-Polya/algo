import java.util.*;
import java.io.*;

public class Main {
	static int n,m;
	static int board[][];
	static boolean visited[][];
	static StringTokenizer st;
	static int ans;
	
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	static boolean possible(int y,int x, int type) {
		if(type == 0 && (OOB(y,x-1) || OOB(y+1,x) || visited[y][x-1] || visited[y+1][x])) {
			return false;
		}else if(type == 1 && (OOB(y,x-1) || OOB(y-1, x) || visited[y][x-1] || visited[y-1][x])) {
			return false;
		}else if(type == 2 && (OOB(y-1, x) || OOB(y, x+1)|| visited[y-1][x] || visited[y][x+1])) {
			return false;
		}else if(type == 3 && (OOB(y+1, x) || OOB(y, x+1)|| visited[y+1][x] || visited[y][x+1])){
			return false;
		}
		return true;
	}
	
	static int fill(int y,int x, int type, boolean flag) {
		if(type == 0) {
			visited[y][x] = visited[y][x-1] = visited[y+1][x] = flag;
			return 2 * board[y][x] + board[y][x-1] + board[y+1][x];
		}else if(type == 1) {
			visited[y][x] = visited[y][x-1] = visited[y-1][x] = flag;
			return 2 * board[y][x] + board[y][x-1] + board[y-1][x];
		}else if(type == 2) {
			visited[y][x] = visited[y-1][x] = visited[y][x+1] = flag;
			return 2 * board[y][x] + board[y-1][x] + board[y][x+1];
		}else {
			visited[y][x] = visited[y+1][x] = visited[y][x+1] = flag;
			return 2 * board[y][x] + board[y+1][x] + board[y][x+1];
		}
	}
	
	static void printBoard() {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				System.out.print((visited[y][x] ? 1 : 0) +" ");
			}
			System.out.println();
		}
	}
	
	static void solve(int y, int x, int sum) {
//		System.out.println("-----");
//		System.out.printf("(y,x) : (%d,%d), sum: %d\n", y,x, sum);
//		printBoard();
		if(y == n+1) {
			ans = Math.max(ans, sum);
			return;
		}
		
		
		if(x == m+1) {
			solve(y+1,1,sum);
			return;
		}
		
		if(visited[y][x]) {
			solve(y,x+1,sum);
			return;
		}
		
		// 현재 위치를 기준으로 부메랑을 만들 경우 
		for(int type=0;type<4;type++) {
			if(!possible(y,x,type))
				continue;
			
			int plus = fill(y,x,type, true);
			solve(y, x+1, sum + plus);
			fill(y,x,type, false);
		}
		
		// 만들지 않을 경우 
		solve(y, x+1,sum);
	}
	
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
				
		solve(1,1,0);
		System.out.println(ans);
//		System.out.println(96+88+71+83+75+56);
	}
    
}