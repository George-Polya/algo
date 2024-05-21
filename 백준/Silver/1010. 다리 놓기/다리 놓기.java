import java.io.*;
import java.util.*;
public class Main {
	static int T;
	static int dp[][] = new int[31][31];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int i = 1; i<=30; i++) {
			for(int k = 0; k<=i; k++) {
				if(k == 0 || k == i)
					dp[i][k] = 1;
				else
					dp[i][k] = dp[i-1][k] + dp[i-1][k-1];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sb.append(dp[m][n]).append('\n');
		}
		System.out.println(sb);
		
	}

}
