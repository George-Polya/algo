import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int arr[][];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		
//		for(int y= 0; y<n; y++) {
//			st = new StringTokenizer(br.readLine());
//			for(int x= 0; x<2; x++) {
//				arr[y][x] = Integer.parseInt(st.nextToken());
//			}
//		}
		
		for(int x = 0; x<2; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y = 0; y<n ; y++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr,(a,b)->{
			if(a[1] != b[1])
				return a[1] - b[1];
			return a[0] - b[0];
		});
		
//		for(int y =0; y<n; y++) {
//			System.out.println(Arrays.toString(arr[y]));
//		}
		
		int p = arr[0][0];
		int th = arr[0][1];
		int ans = 0;
		for(int i = 0; i <n ;i++) {
			if(th > arr[i][0]) {
				int cnt = (th - arr[i][0] + 29) / 30;
				ans += cnt;
				arr[i][0] += cnt * 30;
			}
			
			p = Math.max(p,  arr[i][0]);
			
			if((i+1) < n && arr[i][1] != arr[i+1][1]) {
				th = Math.max(p, arr[i+1][1]);
			}
		}
		
		System.out.println(ans);
	}
}