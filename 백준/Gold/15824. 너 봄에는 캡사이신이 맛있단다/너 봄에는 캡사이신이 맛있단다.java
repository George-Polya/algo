import java.io.*;
import java.util.*;

public class Main {
	static int MOD = (int)1e9+7;
	static int n;
	static long arr[];
	static long pows[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new long[n+1];
		pows = new long[n+1];
		st = new StringTokenizer(br.readLine());
		pows[0] = 1;
		for(int i = 1; i <= n ; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			pows[i] = (pows[i-1] * 2) % MOD;
		}
		
		Arrays.sort(arr);
		long ans = 0;
		
		for(int i = 1; i <= n ;i++) {
			ans =  (ans + (pows[i-1] - pows[n - i]) * arr[i]) % MOD; 
			
		}
		System.out.println(ans);
	
	}
}