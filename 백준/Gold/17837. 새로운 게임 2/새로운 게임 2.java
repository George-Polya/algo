import java.util.*;
import java.io.*;

public class Main {
	static int n,k;
	static StringTokenizer st;
	static class Horse implements Comparable<Horse>{
		int y,x,id, dir;
		public Horse(int y,int x,int id, int dir) {
			this.y = y;
			this.x = x;
			this.id = id;
			this.dir = dir;
		}
		
		public int compareTo(Horse h) {
			return id - h.id;
		}
		
		
		public String toString() {
			return String.format("%d %d %d", y,x,id);
		}
		
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if(o == null || getClass() != o.getClass())
				return false;
			Horse h = (Horse)o;
			return y == h.y && x == h.x && id == h.id;
		}
		
		public int hashCode() {
			return Objects.hash(y,x,id);
		}
		
	}
	
	static int dy[] = {0,0,-1,1};
	static int dx[] = {1,-1,0,0};
	static int color[][];
	static Stack<Horse> board[][];
	static int ans;
	
	static boolean simulate() {
		pq = new PriorityQueue<>(set); // 가져올 말의 순서 정하
		set.clear();
		while(!pq.isEmpty()) { // id가 낮은거부터 가져
			Horse target = pq.poll();
			int y = target.y;
			int x = target.x;
			int moveDir = target.dir; // 말들이 이동할 방향 
			
			// 이동할 칸 
			int ny = y + dy[moveDir];
			int nx = x + dx[moveDir];
			
			// 쌓아올려진 것부터 그 위에 있는거까지 이동하기 위한 임시 스택
			// target을 찾을떄까지 tmp에 push하고 차후에 다음 위치로 옮길떄는
			// tmp에서 꺼내오면 target부터 시작해서 맨 위까지 있는 말들을 같이 옮길 수 있다.
			Stack<Horse> tmp = new Stack<>(); 
			while(!board[y][x].peek().equals(target)) {
				tmp.push(board[y][x].pop());
			}
			
			target = board[y][x].pop();
			
			// 만약 다음위치가 blue
			if(isBlue(ny,nx)) {
				// 타겟의 방향을 바꿔주고 이동방향도 바꿔야한
				target.dir = (target.dir % 2 == 0 ? target.dir +1 : target.dir - 1);
				moveDir = target.dir;
				
				// 이동방향을 바꾸고 난 후의 이동할 칸 
				ny = y + dy[moveDir];
				nx = x + dx[moveDir];
				
				// 방향바꾸고 난 후에 이동할 칸도 blue면은 가만히 있는다.
				if(isBlue(ny,nx)) {
					tmp.push(target);
					while(!tmp.isEmpty()) {
						Horse cur = tmp.pop();
						board[y][x].push(cur);
						set.add(cur);
					}
					continue;
				}
			}
			
			tmp.push(target);
			
			// 다음 이동할 칸이 red면 tmp를 역순으로 만들어서 쌓아올려진 순서를 뒤집는다.
			if(isRed(ny,nx)) {
				Collections.reverse(tmp);
			}
			
			// tmp에서 꺼내서 board[ny][nx]에 push한다.
			while(!tmp.isEmpty()) {
				Horse cur = tmp.pop();
				
				// 꺼내진 말의 위치는 ny,nx로 변경되어야 함.
				cur.y = ny;
				cur.x = nx;
				board[ny][nx].push(cur);
				
				// 다음위치에 말이 4개 이상있으면 시뮬레이션 종료.
				if(board[ny][nx].size() >= 4) {
					return true;
				}
				// 말 관리
				set.add(cur);
			}
		}
		return false;
	}
	
	static TreeSet<Horse> set = new TreeSet<>();
	static PriorityQueue<Horse> pq;
	static boolean isRed(int y,int x) {
		return color[y][x] == 1;
	}
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>n;
	}

	static boolean isBlue(int y,int x) {
		return OOB(y,x) || color[y][x] == 2; 
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		color = new int[n+1][n+1];
		board = new Stack[n+1][n+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				color[y][x] = Integer.parseInt(st.nextToken());
				board[y][x] = new Stack<>();
			}
		}
		
		for(int id = 1; id <= k; id++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			Horse horse = new Horse(y,x,id,dir);
			board[y][x].push(horse);
			set.add(horse);
		}
		
		int ans = -1;
		for(int t = 1; t<=1000;t++) {
			boolean isDone = simulate();
			if(isDone) {
				ans = t;
				break;
			}
		}
		System.out.println(ans);
		
	}
}