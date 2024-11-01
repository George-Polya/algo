import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		locations = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			locations[i] = Integer.parseInt(st.nextToken());
		}

		int l = 1;
		int r = n;
		
		String ans="";
		while(l<=r) {
			int mid = (l + r) / 2;
			String str = make(mid);
			
			if(str != "") {
				l = mid + 1;
				ans = str;
			}else {
				r = mid - 1;
			}
		}
		
		System.out.println(ans);
		
	}
	static String make(int dist) {
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(1);
		cnt++;
		
		int prev = locations[0];
		
		for(int i = 1; i<k; i++) {
			if(cnt == m)
				sb.append(0);
			else {
				if(locations[i] - prev >= dist) {
					sb.append(1);
					cnt++;
					prev = locations[i];
				}else {
					sb.append(0);
				}
			}
		}
		
		return cnt == m ? sb.toString() : "";
	}
	static StringTokenizer st;
	static int n,m,k;
	static int locations[];
	
}