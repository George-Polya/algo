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
	
	/*
	 * 다음 위치 구하는 메소드 
	 * 만약 모든 board[y][x] == 1인 (y,x)를 색종이로 채웠다면 NO_POS 리턴 
	 */
	static Pair getNxtPos() {
		for(Pair p : ones) {
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
	static boolean fillable(int sy,int sx ,int num) { 
		int ey = sy + num - 1;
		int ex = sx + num - 1;
		
		for(int y= sy; y<=ey; y++) {
			for(int x= sx; x<=ex; x++) {
				if(OOB(y,x) || visited[y][x] || board[y][x] == 0)
					return false;
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
		if(ans == 4 || ans <= cnt) // ans는 4보다 작아질 수 없다.
			return;
		if(sy == 12 && sx == 12) { // 마지막까지 도달 했을 경
			if(check())
				ans = Math.min(ans, cnt);
			return;
		}
		
		
		for(int num=5; num>=1 && colors[num] != 0;num--) {
			// 사용하려는 색종이를 모두 소진했거나 채울 수 없으면 스
			if(!fillable(sy,sx,num))
				continue;
			
			fill(sy,sx,num, true);
			colors[num]--;
			Pair nxt = getNxtPos(); // 다음 위치 구하기. 
			solve(nxt.y, nxt.x, cnt + 1);
			colors[num]++;
			fill(sy,sx,num, false);
		
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
		
		solve(sy,sx,0);
		System.out.println(ans == INT_MAX ? -1 : ans);
	}
}