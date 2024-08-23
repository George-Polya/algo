import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<= T; tc++) {
			uf = new HashMap<>();
			counts = new HashMap<>();
			F = Integer.parseInt(br.readLine());
			String temps[][] = new String[F][2];
			for(int i = 0; i <F; i++) {
				st = new StringTokenizer(br.readLine());
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				temps[i][0] = s1;
				temps[i][1] = s2;
				uf.put(s1, s1);
				uf.put(s2, s2);
				counts.put(s1, 1);
				counts.put(s2, 1);
			}
			
			for(int i = 0; i < F; i++) {
				String s1 = temps[i][0];
				String s2 = temps[i][1];
				
				union(s1,s2);
				s1 = find(s1);
//				System.out.println(counts.get(s1));
				sb.append(counts.get(s1)).append('\n');
			}
			
			
		}
		System.out.println(sb);
	}
	
	static int T,F;
	static StringTokenizer st;
	static Map<String, String> uf;
	static Map<String, Integer> counts;
	
	static String find(String x) {
		if(x.equals(uf.get(x)))
			return x;
		
		uf.put(x, find(uf.get(x)));
		return uf.get(x);
	}
	
	static void union(String x, String y) {
		x = find(x);
		y = find(y);
//		System.out.printf("%s %s\n", x,y);
		if(x.equals(y))
			return;
		uf.put(x, y);
		counts.put(y, counts.get(y) + counts.get(x));
	}
	
}