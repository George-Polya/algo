import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static long x,y,z;
	static int INT_MAX = (int)1e9;
	static boolean possible(int num) {
		
		
		long ret = (long)((double)((y+num) * 100) / (x + num) );
		
		return ret != z;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());
		
		z = (long)((double)(y * 100) / x);
		
		int l = 1;
		int r = INT_MAX + 1;
		
		int ans = -1;
		while(l<=r) {
			int mid = (l + r) /2;
			
			if(possible(mid)) {
				r = mid - 1;
				ans = mid;
			}else {
				l = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
}