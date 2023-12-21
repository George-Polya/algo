import java.io.*;
/**
 * dp
 */
import java.util.*;
public class Main {
	static int T,n,m;
	static int arr[],dp[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	T = Integer.parseInt(br.readLine());
	 	StringBuilder sb = new StringBuilder();
	 	for(int tc=1;tc<=T;tc++) {
	 		n = Integer.parseInt(br.readLine());
	 		arr = new int[n+1];
	 		st = new StringTokenizer(br.readLine());
	 		for(int i = 1; i<=n; i++) {
	 			arr[i] = Integer.parseInt(st.nextToken());
	 		}
	 		
	 		m = Integer.parseInt(br.readLine());
	 		dp = new int[m+1];
	 		dp[0] = 1;
	 		for(int i = 1; i<=n; i++) {
	 			for(int j = 1; j<=m; j++) {
	 				if(j - arr[i] >= 0) {
	 					dp[j] += dp[j-arr[i]];
	 				}
	 			}
	 		}
//	 		System.out.println(dp[m]);
	 		sb.append(dp[m]).append('\n');
	 	}
	 	System.out.println(sb);
	}
}