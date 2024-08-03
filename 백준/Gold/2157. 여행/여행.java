import java.io.*;
import java.util.*;

public class Main {
	static int n,m,k;
	static StringTokenizer st;
	static int INT_MIN = Integer.MIN_VALUE / 2;
	static int adj[][], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new int[n+1][n+1];
		dp = new int[n+1][m+1];
		for(int i = 0;i<=n;i++) {
			Arrays.fill(adj[i], INT_MIN);
			Arrays.fill(dp[i], INT_MIN);
		}
		
		for(int i = 0; i < k;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a][b] = Math.max(adj[a][b], c);
		}
		
		dp[1][1] = 0;
		
		for(int i =2 ;i<=n;i++) {
			for(int cnt = 2; cnt<=m; cnt++) {
				
				for(int c = 1; c<=n; c++) {
					if(dp[c][cnt-1] != INT_MIN && adj[c][i] != INT_MIN) {
//						System.out.printf("i: %d, cnt: %d, c: %d, cnt-1: %d\n", i, cnt,c, cnt-1);
						dp[i][cnt] = Math.max(dp[i][cnt], dp[c][cnt - 1] + adj[c][i]);
						
					}
				}
			}
		}
		
		int ans = INT_MIN;
		for(int i = 2; i<=m; i++) {
			ans = Math.max(ans, dp[n][i]);
		}
		System.out.println(ans);
	}
}