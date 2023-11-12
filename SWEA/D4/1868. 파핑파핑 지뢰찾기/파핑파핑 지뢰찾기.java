import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int n;
	static int board[][];
	static boolean visited[][];
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>n;
	}
	
	static void printBoard(int board[][]) {
		System.out.println("-----");
		for(int y=1; y<=n ;y++) {
			for(int x=1; x<=n; x++) {
				System.out.printf("%3d", board[y][x]);
				
			}
			System.out.println();
		}
	}
	
	static boolean isZero(int y,int x) {
		for(int dir = 0; dir<8; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(OOB(ny,nx))
				continue;
			if(board[ny][nx] == -2)
				return false;
		}
		return true;
	}
	
	static void bfs(int y,int x) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			board[cur.y][cur.x] = 0;
			for(int dir = 0; dir < 8; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				if(OOB(ny,nx) || visited[ny][nx] || board[ny][nx] == -2)
					continue;
				
				if(isZero(ny,nx))
					q.add(new Pair(ny,nx));
				visited[ny][nx] = true;
				
			}
			
			
		}
		
	}
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n+1][n+1];
			for(int y=1; y<=n; y++) {
				String str = br.readLine();
				for(int x=1; x<=n; x++) {
					if(str.charAt(x-1) == '*')
						board[y][x] = -2;
					else
						board[y][x] = -1;
				}
			}
			
			visited = new boolean[n+1][n+1];
			
			int ans = 0;
			for(int y=1; y<=n; y++) {
				for(int x=1; x<=n; x++) {
					if(visited[y][x] || board[y][x] == -2)
						continue;
					
					if(isZero(y,x)) {
						bfs(y,x);
						ans++;
					}
				}
			}
			
//			printBoard(board);
			for(int y=1; y<=n; y++) {
				for(int x=1; x<=n; x++) {
					if(board[y][x] == -1 && !visited[y][x])
						ans++;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
			
			
		}
		System.out.println(sb);
	}

}