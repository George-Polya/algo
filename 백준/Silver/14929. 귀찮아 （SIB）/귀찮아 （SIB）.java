import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	
	static int pSum[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		
		pSum = new int[n+1];
		
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i == 1) {
				pSum[i] = arr[i];
			}else {
				pSum[i] = pSum[i-1] + arr[i];
			}
		}

		
		long ans = 0;
		for(int i = 1; i <=n;i++) {
			int cur = arr[i];
			ans += cur * (pSum[n]-pSum[i]);
		}
	
		System.out.println(ans);
	}
}