import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[MAX_R+1][MAX_R+1];
		
		int sy = MAX_R/2;
		int sx = MAX_R/2;
		visited[sy][sx] = true;
		
		solve(sy,sx,0,0);
		System.out.println(ans);
	}
	
	
	static int dy[] = {1,2,1,-1,-2,-1};
	static int dx[] = {1,0,-1,-1,0,1};
	static void solve(int y,int x ,int dir, int cnt) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		if(visited[ny][nx]) {
			if(cnt == N)
				ans++;
			return;
		}
		
		if(cnt == N)
			return;
		
		visited[ny][nx] = true;
		solve(ny,nx,(dir + 1) % 6, cnt + 1);
		solve(ny,nx, (dir + 5)% 6, cnt + 1);
		visited[ny][nx] = false;
	}
	
	
	static int ans;
	static int N;
	static int MAX_R = 100;
	static boolean visited[][];
	
}
