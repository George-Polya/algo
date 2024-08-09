import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n;
	static int uf[];
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int a,int b) {
		a = find(a);
		b = find(b);
		if(a == b)
			return;
		
		uf[a] = b;
//		count[a] += count[b];
		count[b] += count[a];
	}
	static int MAX_N = (int)1e6;
	static int count[];
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		uf = new int[MAX_N+1];
		count = new int[MAX_N+1];
		for(int i = 1; i<=MAX_N;i++) {
			uf[i] = i;
			count[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			if(cmd == 'I') {
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
//				System.out.println("-----");
//				System.out.println(Arrays.toString(uf));
//				System.out.println(Arrays.toString(count));
			}else {
				a = find(a);
//				System.out.println(count[a]);
				sb.append(count[a]).append('\n');
			}
		}
		System.out.println(sb);
	}
}