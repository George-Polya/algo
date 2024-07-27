import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int board[][];
	static int n = 10;
	static boolean visited[][] = new boolean[n+1][n+1];
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
	
	static Pair NO_POS = new Pair(12,12);
	
	static List<Pair> ones = new ArrayList<>();
	static int INT_MAX = Integer.MAX_VALUE;
	static int ans = INT_MAX;
	
	static boolean check() {
		for(Pair p : ones) {
			if(!visited[p.y][p.x])
				return false;
		}
		return true;
	}
	
	static int colors[] = {0,5,5,5,5,5};
	
	
	static Pair getNxtPos() {
		for(Pair p : ones) {
//			System.out.printf("visited[%d][%d] : %b\n", p.y,p.x, visited[p.y][p.x]);
			if(!visited[p.y][p.x])
				return p;
		}
		return NO_POS;
	}
	
	
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x > n;
	}
	/*
	 * 덮을 수 있을 때란 언제인가?
	 * 1. 종이의 경계를 벗어나지 않고,
	 * 2. 이미 덮은 곳에 또 덮지 않을 때, 
	 */
	static boolean fillable(int sy,int sx ,int num) { // 채우면서 동시에 판단. 
		int ey = sy + num - 1;
		int ex = sx + num - 1;
		
		for(int y= sy; y<=ey; y++) {
			for(int x= sx; x<=ex; x++) {
				if(OOB(y,x) || visited[y][x] || board[y][x] == 0)
					return false;
//				visited[y][x] = true;
			}
		}
		return true;
	}
	
	static void fill(int sy, int sx, int num, boolean flag) {
		int ey = sy + num - 1;
		int ex = sx + num - 1;
		
		for(int y= sy; y<=ey; y++) {
			for(int x= sx; x<=ex; x++) {
				if(OOB(y,x))
					continue;
				visited[y][x] = flag;
			}
		}
	}
	
	static void printBoard() {
		for(int  y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				System.out.print((visited[y][x] ? 1 : 0) +" " );
			System.out.println();
		}
	}
	
	static void solve(int sy,int sx ,int cnt) {
//		printBoard();
		if(ans == 4)
			return;
		if(sy == 12 && sx == 12) {
			if(check())
				ans = Math.min(ans, cnt);
			return;
		}
		
		
		for(int num=5; num>=1;num--) {
//			System.out.println("-----");
//			boolean fillable = fillable(sy,sx,num);
//			System.out.println("fillable: "+fillable);
			if(colors[num] == 0 || !fillable(sy,sx,num))
				continue;
			
			fill(sy,sx,num, true);
			colors[num]--;
//			System.out.printf("(sy,sx): (%d,%d), cnt: %d\n", sy,sx,cnt);
//			System.out.println("colors: "+Arrays.toString(colors));
//			printBoard();
			Pair nxt = getNxtPos(); 
//			System.out.println("nxt: "+nxt);
			solve(nxt.y, nxt.x, cnt + 1);
			colors[num]++;
//			restore(sy,sx,num); // 복원
			fill(sy,sx,num, false);
		
			// 사용하려는 색종이를 모두 소진했거나
//			if(colors[num] == 0) 
//				continue;
//			boolean fillable = fillable(sy,sx,num);
//			if(colors[num] != 0 && fillable ) {
//				printBoard();
//			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[n+1][n+1];
		for(int y= 1;y<=n;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n ; x++) {
				board[y][x]= Integer.parseInt(st.nextToken());
				if(board[y][x] == 1)
					ones.add(new Pair(y,x));
			}
		}
		
		if(ones.isEmpty()) {
			System.out.println(0);
			return;
		}
		
		int sy = ones.get(0).y;
		int sx = ones.get(0).x;
//		System.out.println(ones);
		
		solve(sy,sx,0);
		System.out.println(ans == INT_MAX ? -1 : ans);
	}
}