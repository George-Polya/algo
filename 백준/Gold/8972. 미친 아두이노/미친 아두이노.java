import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		monsters = new ArrayList<>();
		count = new int[R+1][C+1];
		for(int y=1; y<=R; y++) {
			String line = br.readLine();
			for(int x = 1; x<=C; x++) {
				char ch = line.charAt(x-1);
				if(ch == 'I') {
					human = new Human(y,x);
				}else if(ch == 'R') {
					monsters.add(new Monster(y,x));
					count[y][x]++;
				}
			}
		}
		
		String line = br.readLine();
		for(char ch : line.toCharArray()) {
			int dir = (ch - '0') - 1;
			human.move(dir);
//			System.out.println("human: "+human);
			moveCnt++;
			if(count[human.y][human.x] != 0) {
				System.out.println("kraj "+moveCnt);
				System.exit(0);
			}
			
			moveAll();
		}
		printBoard();
	}
	
	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=R; y++) {
			for(int x=1; x<=C; x++) {
				if(human.isSame(y, x)) {
					sb.append('I');
				}else if(count[y][x] == 1) {
					sb.append('R');
				}else {
					sb.append('.');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
	
	static void moveAll() {
		nxtCount = new int[R+1][C+1];
		nxtMonsters = new ArrayList<>();
		for(Monster monster : monsters) {
			monster.move();
		}
		
		for(int y=1;y<=R; y++) {
			for(int x=1; x<=C; x++) {
				if(nxtCount[y][x] >= 2) { // 두개 이상의 몬스터가 한곳에 있으면 모두 파괴 
					nxtCount[y][x] = 0;
				}else if(nxtCount[y][x] == 1) {
					nxtMonsters.add(new Monster(y,x));
				}
			}
		}
		
		monsters = nxtMonsters;
		count = nxtCount;
	}
	
	
	static int moveCnt;
	static StringTokenizer st;
	static int R,C;
	static List<Monster> monsters, nxtMonsters;
	static int dy[] = {1,1,1,0, 0, 0,-1,-1,-1};
	static int dx[] = {-1,0,1,-1,0,1,-1,0,1};
	static class Human{
		int y,x;
		public Human(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public void move(int dir) {
			y = y + dy[dir];
			x = x + dx[dir];
		}
		
		public String toString() {
			return y+" "+x;
		}
		
		public boolean isSame(int y,int x) {
			return this.y == y && this.x == x;
		}
	}
	static int count[][], nxtCount[][];
	static Human human;
	static class Monster{
		int y,x;
		public Monster(int y,int x) {
			this.y = y;
			this.x = x;
		}
		public boolean isSame(int y,int x) {
			return this.y == y && this.x == x;
		}
		public void move() {
			Monster nxt = getNxtPos(y,x);
//			System.out.println("nxt: "+nxt);
			if(human.isSame(nxt.y,  nxt.x)) {
				System.out.println("kraj "+moveCnt);
//				printBoard();
				System.exit(0);
			}
			
			nxtCount[nxt.y][nxt.x]++;
		}
		
		private Monster getNxtPos(int y,int x) {
			int dist = getDistance(y,x, human.y, human.x);
			Monster ret = null;
			for(int dir = 0; dir < 9; dir++) {
				if(dir == 4)
					continue;
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				int nDist = getDistance(ny,nx, human.y, human.x);
				if(ret == null || nDist < dist) {
					dist = nDist;
					ret = new Monster(ny,nx);
				}
			}
			
			return ret;
		}
		public String toString() {
			return y+" "+x;
		}
	}
	
	static int getDistance(int y1,int x1, int y2, int x2) {
		return Math.abs(y1-y2) + Math.abs(x1-x2);
	}
}