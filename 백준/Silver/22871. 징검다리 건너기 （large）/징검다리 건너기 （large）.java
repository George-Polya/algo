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
	static void decide(int start, long k) {
		if(start == n) {
//			return true;
			flag = true;
			return;
		}
		
		for(int i = start + 1 ; i <=n; i++) {
			long  result = (long)(i - start) * ( 1 + Math.abs(arr[start] - arr[i]));
			
			if(result <= k && !visited[i]) {
				visited[i] = true;
//				return decide(i,k);
				decide(i,k);
			}
		}
//		return false;
		
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
			flag = false;
			visited[1] = true;
			long mid = (l + r) / 2;
			decide(1,mid);
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