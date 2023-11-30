import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static long LONG_MAX = (long)1e10;
	static int n;
	static long m;
	static StringTokenizer st;
	static long arr[];
	
	static boolean decide(long bag) {
		long sum = 0;
		for(int i = 1; i<=n;i++) {
			sum += arr[i] / bag;
		}
		
		return sum >= m;
	}
	
	static long LONG_MIN = Long.MIN_VALUE;
	static long max;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Long.parseLong(st.nextToken());
			arr = new long[n+1];
			st = new StringTokenizer(br.readLine());
			max = LONG_MIN;
			for(int i = 1; i<=n; i++) {
				arr[i] = Long.parseLong(st.nextToken());
				max = Math.max(max, arr[i]);
			}
			
			long l = 1, r = max + 1;
			long ans = 0;
			while(l<=r) {
				long mid = (l + r) / 2;
				if(decide(mid)) {
					l = mid + 1;
					ans = mid;
				}else {
					r = mid - 1;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
			
		}
		System.out.print(sb);
	}
}