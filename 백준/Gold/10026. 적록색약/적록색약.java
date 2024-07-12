import java.util.*;
import java.io.*;

public class Main {
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
	
	static Pair idx2Pair[];
	static HashMap<Pair, Integer> pair2Idx = new HashMap<>();
	static char board[][];
	static int n;
	
	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x >n;
	}
	
	static boolean visited[][];
	
	static void dfs(int y,int x, char color) {
		for(int dir = 0; dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) || visited[ny][nx])
				continue;
			if(color == board[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny,nx, color);
			}
		}
	}
	
	static void dfs2(int y, int x,int color) {
		for(int dir = 0; dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) || visited[ny][nx])
				continue;
			if(color == board[ny][nx]) {
				visited[ny][nx] = true;
				dfs2(ny,nx, color);
			}
			
			if(color == 'R' && board[ny][nx] == 'G') {
				visited[ny][nx] = true;
				dfs2(ny,nx, color);
			}
			
			if(color == 'G' && board[ny][nx] == 'R') {
				visited[ny][nx] = true;
				dfs2(ny,nx, color);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new char[n+1][n+1];
		for(int y=1; y<=n; y++) {
			String str = br.readLine();
			for(int x=1; x<=n; x++) {
				board[y][x] = str.charAt(x-1);
			}
		}
//		
		
		visited = new boolean[n+1][n+1];
		int cnt1 = 0;
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				if(visited[y][x])
					continue;
				visited[y][x] = true;
				dfs(y,x,board[y][x]);
				cnt1++;
			}
		}
		
		visited = new boolean[n+1][n+1];
		int cnt2 = 0;
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++) {
				if(visited[y][x])
					continue;
				visited[y][x] = true;
				dfs2(y,x,board[y][x]);
				cnt2++;
			}
		}
		System.out.println(cnt1+" "+cnt2);
	}

}