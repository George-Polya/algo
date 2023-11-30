import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int n,m, c1,c2;
	static StringTokenizer st;
//	static int getDistance()
	
	static int cows[];
	
	static int lower_bound(int target) {
		int ans = n;
		int l = 0;
		int r = n - 1;
		while(l<=r) {
			int mid = (l + r) / 2;
			
			if(target <= cows[mid]) {
				r = mid - 1;
				ans = mid;
			}else {
				l = mid + 1;
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			cows = new int[n];
			st = new StringTokenizer(br.readLine());
			c1 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				cows[i] = Integer.parseInt(st.nextToken());
			}
			
			int count = 0;
			int min = Integer.MAX_VALUE;
			
			Arrays.sort(cows);
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m ;i++) {
				int horse = Integer.parseInt(st.nextToken());
//				System.out.println(horse);
				int idx = lower_bound(horse);
				
				if( idx < n) {
					int dist = cows[idx] - horse;
					if(min == dist) {
						count++;
					}else if(min > dist) {
						min = dist;
						count = 1;
					}
					
				}
				
				
				if(idx - 1 >= 0) {
					int dist = horse - cows[idx-1];
					if(min == dist) {
						count++;
					}else if(min > dist) {
						min = dist;
						count = 1;
					}
				}
			}
			int dx = Math.abs(c1 - c2);
			
			sb.append('#').append(tc).append(' ').append(dx + min).append(' ').append(count).append('\n');
			
		}
		System.out.print(sb);
	}
}