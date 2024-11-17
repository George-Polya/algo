import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		uf = new int[n*m+1];
		for(int y=1; y<=n; y++) {
			String str = br.readLine();
			for(int x=1; x<=m; x++) {
				char ch = str.charAt(x-1);
				if(ch == 'U') {
					board[y][x] = 0;
				}else if(ch == 'R') {
					board[y][x] = 1;
				}else if(ch == 'D') {
					board[y][x] = 2;
				}else {
					board[y][x] = 3;
				}
			}
		}
		
		for(int i = 1; i<=n*m;i++) {
			uf[i] = i;
		}
		
		for(int id = 1; id<=n*m;id++) {
			dfs(id);
		}
		
//		System.out.println(Arrays.toString(uf));
		Set<Integer> set = new HashSet<>();
		for(int id =1; id<=n*m;id++) {
			set.add(find(id));
		}
		System.out.println(set.size());
	}
	
	static void dfs(int id) {
//		System.out.println("id: "+id);
		int y = (id - 1) / m + 1;
		int x = (id - 1) % m + 1;
//		System.out.printf("%d %d\n", y,x);
		int dir = board[y][x];
		
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		if(OOB(ny,nx))
			return;
		
		int nxt = nx + (ny - 1) * m;
//		System.out.println("nxt: "+nxt);
		
		nxt = find(nxt);
		id = find(id);
		
		if(nxt == id)
			return;
		
		uf[id] = nxt;
		dfs(nxt);
	}
	static int find(int x) {
		if( x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	static boolean OOB(int y,int x) {
		return y<=0 || y>n || x<=0 || x>m;
	}
	
	static int uf[];
	static StringTokenizer st;
	static int n,m;
	static int board[][];
}