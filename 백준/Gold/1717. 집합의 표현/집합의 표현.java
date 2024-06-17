import java.util.*;
import java.io.*;
public class Main {
	static int n,m;
	static StringTokenizer st;
	static int uf[];
	
	static int find(int x) {
		if(uf[x] == x)
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int x,int y) {
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
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		uf = new int[n+1];
		for(int i =0;i<=n;i++)
			uf[i] = i;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cmd == 0) {
				union(a,b);
			}else {
				a = find(a);
				b = find(b);
				sb.append(a == b ? "YES":"NO").append('\n');
//				System.out.println(a == b ? "YES": "NO" );
			}
//			System.out.println(Arrays.toString(uf));
//			sb.append(cmd+" "+a+" "+b).append('\n');
//			sb.append(Arrays.toString(uf)).append("\n----\n");
		}
		System.out.println(sb);
	}
}