import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dist = new long[n-1];
		cost = new long[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <n-1;i++) {
			dist[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <n;i++) {
			cost[i] = Long.parseLong(st.nextToken());
		}
		
		
		long cur = Long.MAX_VALUE;
		long sum = 0;
		for(int i = 0; i < n-1; i++) {
			if(cur > cost[i])
				cur = cost[i];
//			System.out.printf("%d| cur: %d | dist: %d|\n", i, cur, dist[i]);
			sum += cur * dist[i];
		}
		System.out.println(sum);
	}
	static StringTokenizer st;
	static int n;
	static long dist[], cost[];
}