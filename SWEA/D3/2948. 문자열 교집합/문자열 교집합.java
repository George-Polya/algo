import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int n,m;
	static HashSet<String> a;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			a = new HashSet<>();
			
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				a.add(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m ;i++) {
				String str = st.nextToken();
				
				if(a.contains(str))
					ans++;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}