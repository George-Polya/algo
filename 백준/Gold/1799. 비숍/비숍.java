import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int board[][];
	static boolean used[][];
	static StringTokenizer st;
	static int dy[] = {-1,-1};
	static int dx[] = {1,-1};
	
	static boolean OOB(int y,int x) {
		return y<0 || y>=n || x<0 || x>=n;
	}
	
	static boolean possible(int y,int x) {
		for(int dir = 0; dir < 2; dir++) {
			for(int dist = 1; dist<n; dist++) {
				int ny = y + dy[dir] * dist;
				int nx = x + dx[dir] * dist;
				
				if(OOB(ny,nx))
					break;
				
				if(used[ny][nx])
					return false;
			}
		}
		return true;
	}
	
	static int result[] = new int[2];
	
	static boolean black[][];
	
	static void dfs(int idx, boolean isBlack, int cnt) {
		for(int i = idx ; i < n*n;i++) {
			int y = i / n;
			int x = i % n;
			
			if(board[y][x] == 0 || black[y][x] != isBlack || !possible(y,x))
				continue;
			
			used[y][x] = true;
			dfs(i + 1, isBlack, cnt + 1);
			used[y][x] = false;
		}
		
		result[isBlack ? 0 : 1] = Math.max(result[isBlack ? 0 : 1], cnt);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        used = new boolean[n][n];
        black = new boolean[n][n];
        for(int y=0; y<n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x=0; x<n; x++) {
        		board[y][x] = Integer.parseInt(st.nextToken());
        		
        		black[y][x] = (y % 2 == 0 && x % 2 == 0) || (y % 2 == 1 && x % 2 == 1);
        		
        	}
        }
        dfs(0, true,0);
        dfs(0, false,0);
        System.out.println(result[0] + result[1]);
    }
}