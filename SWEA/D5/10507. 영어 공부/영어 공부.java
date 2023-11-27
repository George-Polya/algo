import java.io.*;
import java.util.*;

public class Solution{
	static int T,n,p;
	static int arr[];
	static StringTokenizer st;
	
	static boolean possible(int len, int start) {
		int cnt = (arr[len] - arr[start] + 1) - (len - start + 1);
		return cnt <= p;
	}
	
	static int solve() {
		int ret = 0;
		
		for(int start = 0; start <n ; start++) {
			int l = start;
			int r = n - 1;
			
			while(l<=r) {
				int mid = (l + r) / 2;
				int blank = (arr[mid] - arr[start] + 1) - (mid - start + 1);
				
				if(blank <= p) {
					l = mid + 1;
					ret = Math.max(ret, (arr[mid] - arr[start] + 1) + (p-blank));
				}else {
					r = mid - 1;
				}
				
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solve();
			
			sb.append("#").append(tc).append(' ').append(ans).append('\n');
			
			
		}
		System.out.println(sb);
	}
}