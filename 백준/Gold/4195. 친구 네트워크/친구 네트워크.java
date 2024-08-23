import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<= T; tc++) {
			F = Integer.parseInt(br.readLine());
			String temps[][] = new String[F][2];
			
			str2Code = new HashMap<>();
			int idx = 1;
			for(int i = 0; i <F; i++) {
				st = new StringTokenizer(br.readLine());
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				temps[i][0] = s1;
				temps[i][1] = s2;
				if(!str2Code.containsKey(s1))
					str2Code.put(s1, idx++);
				if(!str2Code.containsKey(s2))
					str2Code.put(s2, idx++);
			}
			
			n = str2Code.size();
			uf = new int[n+1];
			counts = new int[n+1];
			for(int i = 1; i<=n; i++) {
				uf[i] = i;
				counts[i] = 1;
			}
			
			
			for(int i = 0; i < F; i++) {
				String s1 = temps[i][0];
				String s2 = temps[i][1];
				int code1 = str2Code.get(s1);
				int code2 = str2Code.get(s2);
				
				union(code1, code2);
				code1 = find(code1);
				sb.append(counts[code1]).append('\n');
			}
			
			
		}
		System.out.println(sb);
	}
	
	static int T,F,n;
	static StringTokenizer st;
	static Map<String, Integer> str2Code; 
	static int uf[], counts[];
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		
		return uf[x] = find(uf[x]);
	}
	
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		if(x == y)
			return;
		
		uf[x] = y;
		counts[y] += counts[x];
		
	}
	
}