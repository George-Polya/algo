


import java.util.*;

public class Main{
	static int n,q;
	static int board[][], nxtBoard[][];
	static int L;
	static int boardSize;
	
	static void rotate(int y,int x,int size) {
//		int temp[][] = new int[size+1][size+1];
		
		for(int i = y; i< y+size;i++) {
			for(int j = x; j<x+size;j++) {
				int tempY = i - y + 1;
				int tempX = j - x + 1;
				int targetY = tempX;
				int targetX = size + 1 - tempY;
				targetY += y-1;
				targetX += x-1;
				nxtBoard[targetY][targetX] = board[i][j];
			}
		}
		
		
		
	}
	
	static void rotateAll() {
		if(L!=0) {
			int size = 1<<L;
			nxtBoard = new int[boardSize+1][boardSize+1];
			
			for(int y = 1; y<=boardSize; y+= size) {
				for(int x=1;x<=boardSize;x+=size) {
					rotate(y,x,size);
				}
			}
			
			
			
			for(int y = 1; y<=boardSize;y++)
				for(int x=1;x<=boardSize;x++)
					board[y][x] = nxtBoard[y][x];
		}
	}
	
	static int count[][];
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	static boolean OOB(int y, int x) {
		return y<= 0 || y>boardSize || x<=0 || x> boardSize;
	}
	
	static boolean existIce(int y,int x) {
		return !OOB(y,x) && board[y][x] > 0;
	}
	
	static void diminish() {
		count = new int[boardSize+1][boardSize+1];
		for(int y = 1; y<=boardSize; y++) {
			for(int x = 1; x<=boardSize;x++) {
				for(int dir = 0;dir<4;dir++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					if(OOB(ny,nx))
						continue;
					if(board[ny][nx]>=1)
						count[y][x]++;
				}
			}
		}
		
		for(int y = 1; y<=boardSize; y++) {
			for(int x = 1; x<=boardSize;x++) {
				if(count[y][x] < 3 && board[y][x] > 0)
					board[y][x]--;
			}
		}
		
		
		
	}
	
	
	static void simulate() {
		rotateAll();
		diminish();
	}
	
	static int getIceSum() {
		int sum = 0;
		for(int y = 1; y<=boardSize;y++) {
			for(int x = 1; x<=boardSize;x++)
				sum += board[y][x];
		}
		return sum;
	}
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static boolean visited[][];
	
	static int bfs(int y, int x) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x));
		visited[y][x] = true;
		int size = 0;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			size++;
			for(int dir = 0; dir<4;dir++) {
				int ny = cur.y+dy[dir];
				int nx = cur.x+dx[dir];
				
				if(OOB(ny,nx))
					continue;
				
				if(board[ny][nx] ==0 || visited[ny][nx])
					continue;
				
				q.add(new Pair(ny,nx));
				visited[ny][nx] = true;
			}
		}
		return size;
		
	}
	
	static int getMaxSize() {
		int maxSize = 0;
		
		visited = new boolean[boardSize+1][boardSize+1];
		
		for(int y = 1; y<=boardSize;y++) {
			for(int x = 1; x<=boardSize;x++) {
				if(visited[y][x] || board[y][x] == 0)
					continue;
				int size = bfs(y,x);
				maxSize = Math.max(maxSize, size);
			}
		}
		
		return maxSize;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		q = sc.nextInt();
		boardSize = 1<<n;
		board = new int[boardSize+1][boardSize+1];
		
		for(int y=1; y<=boardSize;y++) {
			for(int x = 1; x<= boardSize;x++)
				board[y][x] = sc.nextInt();
		}
		
		for(int i = 0; i<q;i++) {
			L = sc.nextInt();
			simulate();
		}
		
		int iceSum = getIceSum();
		int maxSize = getMaxSize();
		System.out.println(iceSum+"\n"+maxSize);
		
	}
}