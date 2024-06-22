import java.util.*;
import java.io.*;
public class Main {
	static int n,m,l;
	static StringTokenizer st;
	static int arr[];
	static boolean decide(int target) {
		int cnt = 0;
		
		for(int i = 0; i <= arr.length-2; i++) {
			int len = arr[i+1] - arr[i];
			
			cnt += len / target;
			
			if(len % target == 0)
				cnt--;
		}
		return cnt <= m;
	}
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 2];
		arr[0] = 0;
		arr[n+1] = l;
		
		if(n != 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
		
		}
		
		
		
		int left = 1;
		int right = l-1;
		
		
		int ans = Integer.MAX_VALUE;
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(decide(mid)) {
				right = mid - 1;
				ans = mid;
			}else {
				left = mid + 1;
			}
			
		}
		
		System.out.println(ans);
	}
}