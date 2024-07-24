import java.io.*;
import java.util.*;

public class Main {
	static int n,m,r;
	static StringTokenizer st;
	static int board[][];
	static int getDir(char ch) {
		if(ch == 'E') {
			return 0;
		}else if(ch == 'W') {
			return 1;
		}else if(ch == 'S') {
			return 2;
		}
		return 3;
	}
	
	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				System.out.printf("%3d", board[y][x]);
			}
			System.out.println();
		}
	}
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	static int offense(int y,int x,int dir) {
		Queue<Pair> q = new LinkedList<>();
		visited[y][x] = true;
		q.add(new Pair(y,x));
		int ret = 0;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			int value = board[cur.y][cur.x];
			ret++;
			
			for(int k=1;k<value;k++) {
				int ny = cur.y + dy[dir] * k;
				int nx = cur.x + dx[dir] * k;
				if(OOB(ny,nx))
					break;
				
				if(visited[ny][nx])
					continue;
				q.add(new Pair(ny,nx));
				visited[ny][nx] = true;
			}
			
		}
		return ret;
	}
	
	static void defense(int y, int x) {
		
		visited[y][x] = false;
	}
	
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		
		for(int y= 1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		long score = 0;
		for(int turn =1 ;turn<=r;turn++) {
//			System.out.println("------");
//			System.out.println("turn: "+turn);
			
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = getDir(st.nextToken().charAt(0));
//			char dir = st.nextToken().charAt(0);
//			System.out.println(dir);
			if(!visited[y][x])
				score += offense(y,x,dir);
			
			
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			defense(y,x);
//			printBoard(board);
		}
		
		StringBuilder sb= new StringBuilder();
		sb.append(score).append('\n');
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				sb.append(visited[y][x] ? 'F' : 'S').append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}