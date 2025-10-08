import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		visited = new boolean[R+1][C+1];
		board = new char[R+1][C+1];
		int sy = 0;
		int sx = 0;
		for(int y=1; y<=R; y++) {
			String line = br.readLine();
			for(int x=1; x<=C;x++) {
				board[y][x] = line.charAt(x-1);
				if(board[y][x] == 'G') {
					sy = y;
					sx = x;
				}
					
			}
		}
		visited[sy][sx] = true;
		System.out.println(solve(sy,sx,0,0));
		
	}
	
	static int solve(int y,int x,int cnt ,int t) {
		if(t == T)
			return cnt;
		
		int ret = solve(y,x,cnt , t+1 );
		for(int dir = 0; dir< 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(OOB(ny,nx) ||  board[ny][nx] == '#')
				continue;
			if(board[ny][nx]=='S') {
				board[ny][nx] = '.';
				ret = Math.max(ret,  solve(ny,nx,cnt+1, t + 1));
				board[ny][nx] = 'S';
			}else {
				ret = Math.max(ret,  solve(ny,nx,cnt, t + 1));
			}
		}
		
		return ret;
	}
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static boolean OOB(int y,int x) {
		return y<=0 || y> R || x<=0 ||x > C;
	}
	
	static StringTokenizer st;
	static int R,C,T;
	static boolean visited[][];
	static char board[][];
}
