import java.io.*;
import java.util.*;


public class Main {
	static int n,m;
	static StringTokenizer st;
	static long arr[];
	
	static boolean decide(long time) {
		long cnt = 0;
		for(int i = 1; i<=n;i++) {
			cnt += time / arr[i];
			if(cnt >=m)
				return true;
		}
		
		return cnt >= m;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		long max = 0;
		long min = Long.MAX_VALUE; 
		arr = new long[n+1];
		for(int i = 1; i<=n; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}
		
//		Arrays.sort(arr,1,n+1);
		
		long l = 0;
		long r = max * m;
		long ans = Long.MAX_VALUE;
		while(l<=r) {
			long mid = (l + r)/ 2;
			
			if(decide(mid)) {
				r = mid - 1;
				ans = Math.min(ans, mid);
			}else {
				l = mid + 1;
			}
		}
		System.out.println(ans);
	}
}