import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static StringTokenizer st;
	static int powers[];
	static String titles[];
	
	static boolean decide(int idx, int power) {
		return powers[idx] >= power;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		powers = new int[n];
		titles = new String[n];
		for(int i = 0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			titles[i] = st.nextToken();
			powers[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			int power = Integer.parseInt(br.readLine());
			
			int l = 0;
			int r = n-1;
			int ans = n-1;
			while(l<=r) {
				int mid = (l+r)/2;
				
				if(decide(mid, power)) {
					r = mid - 1;
					ans = mid;
				}else {
					l = mid + 1;
				}
				
			}
//			System.out.println(titles[ans]);
			sb.append(titles[ans]).append('\n');
		}
		System.out.println(sb);
	}
}