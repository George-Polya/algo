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
		
		
		/*
		 * dp[i][cnt] : i번째 도시로 이동을 고려하고 있고, 지금까지 방문한 도시수가 cnt일때, 
		 *              먹은 기내식 점수의 총합의 최댓값
		 *               
		 * dp[i][cnt] = max(dp[i][cnt], dp[c][cnt-1] + adj[c][i]) (단, c에서 i로 갈 수 있는 경우)
		 */
		
		dp[1][1] = 0; // 가장 처음엔 1번도시에 있으므로 방문한 도시수가 1이다. 그리고 이 때는 얻은 점수가 없다. 
		for(int i =2 ;i<=n;i++) {
			for(int cnt = 2; cnt<=m; cnt++) {
				for(int c = 1; c<i; c++) {
					/*
					 * adj[c][i]가 INT_MIN이다 : c에서 i로의 항로가 없다
					 * dp[c][cnt-1]이 INT_MIN이다 : cnt-1번의 이동을 했을 때, 1번에서 c로 도달할 수 없다. 					 
					 */
					if(dp[c][cnt-1] != INT_MIN && adj[c][i] != INT_MIN) {
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