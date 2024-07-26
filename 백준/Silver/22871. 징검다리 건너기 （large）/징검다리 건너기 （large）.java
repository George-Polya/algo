import java.io.*;
import java.util.*;


public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	
	static long dp[];
	static long LONG_MAX = Long.MAX_VALUE;
	static boolean visited[];
	static boolean flag;
	static boolean decide(int cur, long k) {
		if(cur == n) {
			return true;
		}
		
		for(int nxt = cur + 1 ; nxt <=n; nxt++) {
			long  result = (long)(nxt - cur) * ( 1 + Math.abs(arr[cur] - arr[nxt]));
			
			if(result <= k && !visited[nxt]) {
				visited[nxt] = true;
				if(decide(nxt,k))
					return true;
			}
		}
		return false;
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[n+1];
		long l = 1;
		long r = (long)(n) * (1 + Math.abs(arr[n] - arr[1]));
		
		long ans = 0;
		
		while(l <= r) {
			Arrays.fill(visited, false);
//			flag = false;
			visited[1] = true;
			long mid = (l + r) / 2;
			flag = decide(1,mid);
			if(flag) {
				r = mid - 1;
				ans = mid;
			}else {
				l = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
}