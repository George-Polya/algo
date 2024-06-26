import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int n;
	static int arr[], dp[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); 
		st = new StringTokenizer(br.readLine());
		arr = new int[n+1];
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1];
		
		for(int i = 1; i<=n; i++) {
			for(int j = 0; j<i;j++) {
				if(arr[j] >= arr[i])
					continue;
				dp[i] = Math.max(dp[i], dp[j] + arr[i]);
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		int ans = 0;
		for(int i = 1; i<=n;i++)
			ans = Math.max(ans, dp[i]);
		System.out.println(ans);
	}
}