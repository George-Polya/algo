import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static long arr[], pSum[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new long[n+1];
		pSum = new long[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i == 1) {
				pSum[i] = arr[i];
			}else {
				pSum[i] = pSum[i-1] + arr[i];
			}
		}
		
		long ret = 0;
		for(int i = 1; i<=n;i++) {
			ret += arr[i] * (pSum[n] - pSum[i]);
		}
		System.out.println(ret);
	}
}