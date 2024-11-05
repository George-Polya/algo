import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			ans = INT_MIN;
			arr = new int[n+1];
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}
		
			int pSum = 0;
			int ans = INT_MIN;
			for(int i = 1; i<=n; i++) {
				pSum = Math.max(0, pSum) + arr[i];
				ans = Math.max(ans, pSum);
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static int INT_MIN = Integer.MIN_VALUE / 2;
	static int T,n, arr[], ans;
	static StringTokenizer st;
}