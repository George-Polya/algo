import java.io.*;
import java.util.*;
public class Solution {
	static int T;
	static String a,b;
	static int n,m;
	static StringTokenizer st;
	static int dp[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			a = st.nextToken();
			b = st.nextToken();
			n = a.length();
			m = b.length();
			a = "#" + a;
			b = "#" + b;
			
			dp = new int[n+1][m+1];
			for(int y=1; y<=n; y++) {
				for(int x=1; x<=m; x++) {
					if(a.charAt(y) == b.charAt(x) ){
						dp[y][x] = dp[y-1][x-1] + 1;
					}else {
						dp[y][x] = Math.max(dp[y-1][x], dp[y][x-1]);
					}
				}
			}
			int ans = 0;
			for(int y=1;y<=n;y++) {
				for(int x=1;x<=m;x++)
					ans = Math.max(ans, dp[y][x]);
			}
			
			sb.append("#").append(tc).append(' ').append(ans).append('\n');
			
		}
		System.out.println(sb);
	}
}