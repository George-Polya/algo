import java.util.*;
import java.io.*;
 class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		original = new int[N][N][N];
		cube = new int[N][N][N];
		for(int z = 0; z<N; z++) {
			for(int y= 0 ; y< N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x= 0; x< N; x++) {
					original[z][y][x] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		used = new boolean[5];
		factorial(0, new ArrayList<Integer>());
		System.out.println(ans == INF ? -1 : ans);
		
	}
	
	static void factorial(int idx, List<Integer> arr) {
		if(idx == 5) {
			cube = makeCube(arr);
			solve(0);
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(used[i])
				continue;
			arr.add(i);
			used[i] = true;
			factorial(idx + 1, arr);
			used[i] = false;
			arr.remove(arr.size() - 1);
		}
	}
	
	static int bfs(Coord start, Coord end) {
		if(isBlock(start) || isBlock(end))
			return INF;
		dist = new int[N][N][N];
		for(int z = 0; z < N; z++) {
			for(int y = 0; y< N; y++) {
				Arrays.fill(dist[z][y], INF);
			}
		}
		
		Queue<Coord>  q = new ArrayDeque<>();
		q.add(start);
		dist[start.z][start.y][start.x] = 0;
		
		while(!q.isEmpty()) {
			Coord cur = q.poll();
			
			if(cur.z == end.z && cur.y == end.y && cur.x == end.x) {
				return dist[end.z][end.y][end.x];
			}
			
			for(int dir = 0; dir < 6 ;dir++) {
				int nz = cur.z + dz[dir];
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(OOB(nz,ny,nx) || dist[nz][ny][nx] != INF || isBlock(new Coord(nz,ny,nx)))
					continue;
				q.add(new Coord(nz,ny,nx));
				dist[nz][ny][nx] = dist[cur.z][cur.y][cur.x] + 1;
			}
		}
		
		return INF;
	}
	
	static int dz[] = {-1,1,0,0,0,0};
	static int dy[] = {0,0,-1,1,0,0};
	static int dx[] = {0,0,0,0,-1,1};
	static boolean OOB(int z, int y, int x) {
		return z < 0 || z>=N || y < 0 || y >= N || x < 0 || x >= N;
	}
	
	static boolean isBlock(Coord coord) {
		return cube[coord.z][coord.y][coord.x] == 0;
	}
	
	
	static void solve(int cur) {
		if(cur == 5) {
			
			for(Coord[] coords : buildMaze()) {
				Coord start = coords[0];
				Coord end = coords[1];
				
				int cnt = bfs(start, end);
				ans = Math.min(ans,  cnt);
			}
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			cube[cur] = rotate(cube[cur]);
			solve(cur + 1);
		}
	}
	
	static Coord[][] buildMaze() {
	    return new Coord[][] {
	        { new Coord(0,0,0), new Coord(4,4,4) },
	        { new Coord(0,0,4), new Coord(4,4,0) },
	        { new Coord(0,4,0), new Coord(4,0,4) },
	        { new Coord(4,0,0), new Coord(0,4,4) }
	    };
	}
	
	static class Coord{
		int z,y,x;
		public Coord(int z, int y,int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
	
	static int[][] rotate(int board[][]){
		int temp[][] = new int[N][N];
		
		for(int y = 0; y< N; y++) {
			for(int x= 0; x<N; x++) {
				int y1 = x;
				int x1 = 4 - y;
				temp[y1][x1] = board[y][x];
			}
		}
		
		return temp;
	}
	
	
	static int[][][] makeCube(List<Integer> arr){
		for(int i = 0; i < 5; i++) {
			cube[i] = original[arr.get(i)];
		}
		
		return cube;
	}
	
	
	static boolean used[];
	static int dist[][][];
	static int INF = Integer.MAX_VALUE / 2;
	static int ans = INF;
	static StringTokenizer st;
	static int N = 5;
	static int original[][][], cube[][][];
 }