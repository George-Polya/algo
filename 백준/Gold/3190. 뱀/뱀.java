import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			board[y][x] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			
			directions.put(t,  d);
		}
		
		deq = new ArrayDeque<>();
		deq.add(new Pair(1,1));
		int dir = 0;
		int time = 0;
		
		while(true) {
			Pair cur = deq.peekFirst();
			Pair nxt = getNxtPos(cur.y, cur.x, dir);
			
			if(end(nxt.y, nxt.x))
				break;
			
			deq.addFirst(new Pair(nxt.y,nxt.x));
			
			if(appleExist(nxt.y,nxt.x))
				board[nxt.y][nxt.x] = 0;
			else
				deq.pollLast();
			
			time++;
			dir = update(dir, time);
			
		}
		
		System.out.println(time + 1);
	}
	
	static boolean appleExist(int y, int x) {
		return board[y][x] == 1;
	}
	
	static int update(int dir, int time) {
		if(! directions.containsKey(time))
			return dir;
		
		int direction = directions.get(time);
		
		if(direction == 'D')
			return (dir + 1) % 4;
		else
			return (dir + 3) % 4;
	}
	
	static boolean end(int y,int x) {
		return OOB(y,x) || collide(y,x);
	}
	
	static boolean OOB(int y,int x) {
		return y<= 0 || y>N || x<=0 || x>N;
	}
	
	static boolean collide(int y,int x) {
		for(Pair pair : deq) {
			if(y == pair.y && x == pair.x)
				return true;
		}
		return false;
	}
	
	static Pair getNxtPos(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		return new Pair(ny,nx);
	}
	
	static int dy[] = {0,1,0,-1};
	static int dx[] = {1,0,-1,0};
	static ArrayDeque<Pair> deq;
	static Map<Integer, Character> directions = new HashMap<>();
	static int board[][]; 
	static int N,K,L;
	static StringTokenizer st;
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}

