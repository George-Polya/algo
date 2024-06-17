import java.util.*;
import java.io.*;
public class Main {
	static int n,m;
	static int uf[];
	static StringTokenizer st;
	
	static int find(int x) {
		if(uf[x] == x)
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int x, int y) {
		if(x < y) {
			int temp = y;
			y = x;
			x = temp;
		}
		
		x = find(x);
		y = find(y);
		if(x == y)
			return;
		uf[x] = y;
	}
	
	static boolean possible() {
		int cur = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i<m;i++) {
			int nxt = Integer.parseInt(st.nextToken());
			int a = find(cur);
			int b = find(nxt);
			if(a == b)
				nxt = cur;
			else
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		uf = new int[n+1];
		for(int i = 1; i<=n; i++)
			uf[i] = i;
		
		for(int i = 1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=n; j++) {
				int cmd = Integer.parseInt(st.nextToken());
				
				if(cmd == 1) {
					union(i,j);
				}
			}
		}
//		System.out.println(Arrays.toString(uf));
		st = new StringTokenizer(br.readLine());
		System.out.println(possible() ? "YES" : "NO");
	}
}