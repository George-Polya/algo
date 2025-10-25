import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R+1][C+1];
		
		for(int y=1;y<=R; y++) {
			String line = br.readLine();
			for(int x=1; x<=C; x++) {
				board[y][x] = line.charAt(x-1);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ;i++) {
			int h = Integer.parseInt(st.nextToken());
//			System.out.printf("-----i: %d, h: %d-------\n", i,h);
			h = (R + 1) - h;
			pitch(h, (i % 2) == 0);
//			System.out.println("before fall");
//			printBoard(board);
			
			List<Pair> cluster = makeCluster();
			if(!cluster.isEmpty()) {
				fall(cluster);
			}
		}
		printBoard(board);
	}
	
	static List<Pair> makeCluster() {
		
		visited = new boolean[R+1][C+1];
		
		for(int x=1; x<=C;x++) {
			if(visited[R][x])
				continue;
			bfs(R,x);
		}
		List<Pair> lists = new ArrayList<>();
		
		for(int y=1; y<=R; y++) {
			for(int x=1; x<=C; x++) {
				if(board[y][x] == 'x' && !visited[y][x])
					lists.add(new Pair(y,x));
			}
		}
		
		return lists;
	}
	
	static void bfs(int y, int x){
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(y,x));
		visited[y][x] = true;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int dir =0;dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(ny,nx) || visited[ny][nx] || board[ny][nx] == '.')
					continue;
				q.add(new Pair(ny,nx));
				visited[ny][nx] = true;
			}
		}
	}
	
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,-1,1};
	
	static boolean OOB(int y,int x) {
		return y<=0 || y > R || x<=0 || x>C;
	}
	
	static boolean visited[][];
	
	static boolean check(List<Pair> list) {
		for(Pair p : list) {
			int ny = p.y + 1;
			int nx = p.x;
			if(OOB(ny,nx) || board[ny][nx] == 'x')
				return true;
		}
		return false;
	}
	
	static void fall(List<Pair> list) {
		for(Pair p : list)
			board[p.y][p.x] = '.';
		
		for(int i = 1; i<=R; i++) {
			int nxt = i;
			List<Pair> list2 = list.stream().map(p-> new Pair(p.y + nxt, p.x)).collect(Collectors.toList());
			if(check(list2)) {
				for(Pair p : list2) {
					board[p.y][p.x] = 'x';
				}
				break;
			}
		}
	}
	
	static class Pair implements Comparable<Pair>{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public int compareTo(Pair o) {
			return o.y - this.y;
		}
		
		public String toString() {
			return String.format("(%d, %d)", y,x);
		}
	}
	
	static void pitch(int y, boolean direction) {
		if(direction) {
			for(int x=1; x<=C; x++) {
				if(board[y][x] == 'x') {
					board[y][x] = '.';
					break;
				}
			}
		}else {
			for(int x = C; x>=1; x--) {
				if(board[y][x] == 'x') {
					board[y][x] = '.';
					break;
				}
			}
		}
		
	}
	
	static void printBoard(char borad[][]) {
		for(int y=1; y<=R; y++) {
			for(int x=1; x<=C; x++) {
				System.out.printf("%s", board[y][x]);
			}
			System.out.println();
		}
	}
	
	static char board[][];
	static int R,C,N;
	static StringTokenizer st;
}
