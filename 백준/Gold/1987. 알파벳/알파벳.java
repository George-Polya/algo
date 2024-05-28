import java.util.*;
import java.io.*;

public class Main {
	static int r,c;
	static StringTokenizer st;
	static char board[][];
	static boolean visited[][];
	static int ans = 1;
	
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>r || x<=0 || x>c;
	}
	
	static boolean check(int y,int x) {
		int cnt = 0;
		for(int dir = 0; dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) || visited[ny][nx] || alphabet[board[ny][nx]-'A'])
				continue;
			cnt++;
		}
		return cnt == 0;
	}
	
	static void dfs(int y,int x, int cnt) {
//		System.out.println(set);
//		System.out.println(check(y,x));
		if(check(y,x)) {
//			System.out.println(set);
			ans = Math.max(ans,  cnt);
			return;
		}

		for(int dir = 0 ; dir<4;dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) || visited[ny][nx] || alphabet[board[ny][nx]-'A'])
				continue;
			
			visited[ny][nx] = true;
			alphabet[board[ny][nx]-'A'] = true;
			dfs(ny,nx,cnt+1);
			visited[ny][nx] = false;
			alphabet[board[ny][nx]-'A'] = false;
		}
	}
	static boolean alphabet[];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r+1][c+1];
		for(int y= 1;y<=r;y++) {
			String str = br.readLine();
			for(int x=1; x<=c;x++) {
				board[y][x] = str.charAt(x-1);
			}
		}
		alphabet = new boolean[26];
		visited = new boolean[r+1][c+1];
		visited[1][1] = true;
		alphabet[board[1][1]-'A'] = true;
		dfs(1,1,1);
		System.out.println(ans);
		
	}

}