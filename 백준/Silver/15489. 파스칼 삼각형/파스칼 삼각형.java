import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[1][1] = 1;
		
		for(int i = 2; i<=30;i++) {
			dp[i][0] = 1;
			for(int j = 1; j<=i; j++) {
				dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
			}
		}

		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		w = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		for(int depth = 0; depth < w ; depth++) {
			for(int d = 0; d<=depth; d++) {
				sum += dp[r + depth][c + d];
			}
		}
		System.out.println(sum);
	}
	
	
	static int r,c,w;
	static StringTokenizer st;
	static int MAX_R = 30;
	static int MAX_C = 30;
	static int dp[][] = new int[MAX_R+1][MAX_C+1];
}