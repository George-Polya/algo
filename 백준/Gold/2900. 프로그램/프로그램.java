import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int value = Integer.parseInt(st.nextToken());
			map.put(value, map.getOrDefault(value, 0) + 1);
		}
		
		
		for(int jump : map.keySet()) {
			int cnt = map.get(jump);
			something(jump, cnt);
		}
		
		pSum = new long[n+1];
		pSum[0] = arr[0];
		
		for(int i = 1; i<n; i++) {
			pSum[i] = pSum[i-1] + arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		q = Integer.parseInt(br.readLine());
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(l == 0 ? pSum[r] : pSum[r] - pSum[l-1]).append('\n');
		}
		System.out.println(sb);
	}
	static void something(int jump, int cnt) {
		int i = 0;
		while(i < n ) {
			arr[i] = arr[i] + cnt;
			i = i + jump;
		}
	}
	
	static int n,k,q;
	static StringTokenizer st;
	static long arr[], pSum[];
	static Map<Integer, Integer> map = new HashMap<>();
}