import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int n,m;
	static boolean pairs[][];
	static int ans;
	static int combi[][];
	static int selected[] = new int[3];
	static boolean check() {
		boolean flag = pairs[selected[0]][selected[1]] || pairs[selected[1]][selected[2]] || pairs[selected[2]][selected[0]];
		return !flag;
	}
	
	static void solve(int cur, int cnt) {
		if(cnt == 3) {
			if(check())
				ans++;
			return;
		}
		
		if(cur == n + 1)
			return;
		
		selected[cnt] = cur;
		solve((cur + 1), cnt + 1);
		selected[cnt] = 0;
		
		solve((cur + 1), cnt);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pairs = new boolean[n+1][n+1];
		for(int i = 0; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pairs[a][b] = true;
			pairs[b][a] = true;
		}
		solve(1,0);
		System.out.println(ans);
	}
}