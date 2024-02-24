import java.io.*;
import java.util.*;
public class Main {
	static int T;
	static String A, B;
	static int dp[][];
	static StringTokenizer st;
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		st = new StringTokenizer(br.readLine());
//		A = st.nextToken();
//		B = st.nextToken();
		A = br.readLine();
		B = br.readLine();
		n = A.length();
		m = B.length();
		A = "#"+A;
		B = "#"+B;
		
		dp = new int[n+1][m+1];
		
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=m; j++) {
				if(A.charAt(i) != B.charAt(j)) {
					dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
				}else {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
				}
			}
		}
		
//			for(int i = 1; i<=n; i++) {
//				for(int j = 1; j<=m; j++) {
//					System.out.print(dp[i][j]+" ");
//				}
//				System.out.println();
//			}
		System.out.println(dp[n][m]);
				
	}
	
	
}