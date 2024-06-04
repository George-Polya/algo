import java.io.*;
import java.util.*;

public class Main {
	static long n;
	static boolean decide(long mid) {
		return mid * mid >=n;
	}
	static long LONG_MAX = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println(LONG_MAX);
		n = Long.parseLong(br.readLine());
		
		if(n == 0) {
			System.out.println(0);
			return;
		}
		long l = 1;
		long r = (long)Math.sqrt(n) + 1;
		long ans = r;
		while(l<=r) {
			long mid = (l+r)/2;
			
			if(decide(mid)) {
				r = mid - 1;
				ans = mid;
			}
			else
				l = mid + 1;
		}
		System.out.println(ans);
	}
}