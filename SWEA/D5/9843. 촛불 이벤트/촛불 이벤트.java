import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static long n;
	static long LONG_MAX = 10000000000L;
	static boolean check(long size) {
		long cnt = size * (size + 1) / 2;
		return cnt <= n;
	}
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			n = Long.parseLong(br.readLine());
			
			long l = 1, r = LONG_MAX;
			long size = 0;
			while(l<=r) {
				long mid = (l+r) / 2;
				if(check(mid)) {
					l = mid + 1;
					size = mid;
				}else {
					r = mid - 1;
				}
			}
			
			long cnt = size * (size + 1)/ 2;
			
//			System.out.println(cnt);
			
			
			sb.append('#').append(tc).append(' ').append(cnt != n ? -1 : size).append('\n');
			
		}
		System.out.print(sb);
	}
}