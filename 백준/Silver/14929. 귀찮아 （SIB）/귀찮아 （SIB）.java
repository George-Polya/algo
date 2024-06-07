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
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		pSum = new int[n+1];
		
		int sum = 0;
		for(int i = 0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			pSum[i+1] = sum;
		}

//		System.out.println(Arrays.toString(pSum));
		
		long ans = 0;
		for(int i = 0; i <n;i++) {
			int cur = arr[i];
			ans += cur * (pSum[n]-pSum[i+1]);
		}
	
		System.out.println(ans);
	}
}