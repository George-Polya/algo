import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int n,q;
	static int arr[], dp[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		dp = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i > 1) {
				dp[i] = dp[i-1] + ((arr[i-1] > arr[i]) ? 1 : 0);
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		
		q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(dp[y] - dp[x]).append('\n');
		}
		System.out.println(sb);
		
		
	}
}