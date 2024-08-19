import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int arr[];
	static int n;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n ;i++) {
			for(int j = i + 1; j < n ; j++) {
				int sum1 = arr[i] + arr[j];
				
				int L = 0 ;
				int R = n - 1;
				
				while(L < R) {
					if(i == L || i == R) {
						L++;
						continue;
					}
					if(j == L || j == R) {
						R--;
						continue;
					}
					
					int sum2 = arr[L] + arr[R];
					ans = Math.min(ans, Math.abs(sum2 - sum1));
					if(sum1 > sum2) {
						L++;
					}else if(sum1 < sum2) {
						R--;
					}else {
						System.out.println(0);
						System.exit(0);
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}