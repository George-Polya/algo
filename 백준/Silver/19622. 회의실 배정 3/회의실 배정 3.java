import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		meetings = new int[n + 1][3];
		for(int i = 0 ;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			meetings[i] = new int[] {start, end, cnt};
			
		}
		
		dp = new int[n + 1];
		
		
		dp[0] = meetings[0][2];
		dp[1] = Math.max(dp[0], meetings[1][2]);
		
		for(int i = 2; i<n; i++) {
			dp[i] = Math.max(dp[i-2] + meetings[i][2], dp[i-1]);
		}
		System.out.println(dp[n-1]);
	}
	static int dp[];
	static int n;
	static StringTokenizer st;
	static int meetings[][];
}