import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		meetings = new Meeting[n + 1];
		for(int i = 0 ;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end, cnt);
			
		}
		
		
		dp = new int[n];
		int ans = -1;
		
		
		dp[0] = meetings[0].cnt;
		if(n >= 1)
			ans = dp[0];
		if(n >= 2)
			dp[1] = meetings[1].cnt;
		
		
		for(int i = 2; i < n ;i++) {
			dp[i] = meetings[i].cnt + ans;
			ans = Math.max(ans,  dp[i-1]);
		}
		
		ans = Math.max(ans,  dp[n-1]);
		
		System.out.println(ans);
	}
	static int dp[];
	static int n;
	static StringTokenizer st;
	static Meeting meetings[];
	static class Meeting implements Comparable<Meeting>{
		int start, end, cnt;
		public Meeting(int start, int end, int cnt) {
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}
		
		public int compareTo(Meeting o) {
			return end - o.end;
		}
	}
	
}