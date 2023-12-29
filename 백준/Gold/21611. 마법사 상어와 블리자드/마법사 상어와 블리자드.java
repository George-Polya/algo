import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int board[][];
	static int arr[];
	static StringTokenizer st;
	static int cy, cx;
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	static void initialize() {
		int dir = 2;
		int y = cy;
		int x = cx;
		int cnt = 1;
		int moveNum = 1;
		while(true) {
			for(int num = 1; num<=moveNum; num++) {
				if(y == 1 && x==1)
					break;
				y = y + dy[dir];
				x = x + dx[dir];
				
				arr[cnt] = board[y][x];
				board[y][x] = cnt;
				cnt++;
			}
			if(y == 1 && x == 1)
				break;
			
			dir = (dir < 2) ? dir + 2 :3 - dir;
			
			if(dir == 2 || dir == 3)
				moveNum++;
		}
		
//		System.out.println(Arrays.toString(arr));
	}
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				System.out.printf("%-3d", board[y][x]);
			System.out.println();
		}
	}
	static int scores[] = new int[4];
	
	
	static void destroy(int d,int s) {
		int y = cy;
		int x = cx;
		
		for(int dist = 1; dist<= s; dist++) {
			int ny = y + dy[d] * dist;
			int nx = x + dx[d] * dist;
			arr[board[ny][nx]] = 0;
		}
		
//		System.out.println(Arrays.toString(arr));
	}
	static int temp[];
	static void drop() {
		Arrays.fill(temp, 0);
		int idx = 1;
		for(int i = 1; i <= n * n - 1;i++) {
			if(arr[i] != 0) {
				temp[idx] = arr[i];
				idx++;
			}
		}
		
		System.arraycopy(temp, 0, arr, 0, n*n);
//		System.out.println(Arrays.toString(arr));
	}
	
	static int findEnd(int start) {
		int end = start;
		
		while(true) {
			if(arr[end] == arr[start])
				end++;
			else
				break;
		}
		return end - 1;
	}
	
	static void fillZero(int start, int end) {
		for(int i = start; i<=end;i++) {
			scores[arr[i]]++;
			arr[i] = 0;
		}
	}
	
	static void explodeAll() {
		boolean allExplode;
		
		do {
			allExplode = false;
			
			for(int start = 1; start < n * n; start++) {
				if(arr[start] == 0)
					continue;
				int end = findEnd(start);
				if(end - start + 1  >= 4) {
//					System.out.println(start+" "+end);
					fillZero(start, end);
					allExplode = true;
				}
			}
			
			drop();
			
		}while(allExplode);
	}
	
	static void change() {
		Arrays.fill(temp, 0);
		
		int value = arr[1];
		int cnt = 1;
		int idx = 1;
		for(int i = 2; i< n*n; i++) {
			if(value == arr[i]) {
				cnt++;
			}else {
				temp[idx++] = cnt;
				if(idx == n * n)
					break;
				temp[idx++] = value;
				if(idx == n * n)
					break;
				value = arr[i];
				cnt = 1;
			}
		}
		System.arraycopy(temp, 0, arr, 0, n*n);
	}
	
	static void simulate(int d, int s) {
		// 1. 구슬 파괴와 이동 
		destroy(d, s);
		drop();
		
		// 2. 폭발하는 구슬이 없을떄까지 폭발 
		explodeAll();
//		System.out.println(Arrays.toString(arr));
		
		// 3. 구슬 변화 
		change();
//		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n+1][n+1];
		cy = (n+1)/2;
		cx = (n+1)/2;
		arr = new int[n*n];
		temp = new int[n*n];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++)
				board[y][x] = Integer.parseInt(st.nextToken());
		}
		
		
		initialize();
		
		for(int i = 0; i < m;i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			simulate(d,s);
		}
		
		int ans = 0;
		for(int i = 1; i<=3; i++) {
			ans += i * scores[i];
		}
		
		System.out.println(ans);
		
	}
}