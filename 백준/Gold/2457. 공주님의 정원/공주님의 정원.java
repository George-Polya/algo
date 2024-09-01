import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static class Flower{
		int start, end;
		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static Flower flowers[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		flowers = new Flower[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			
			flowers[i] = new Flower(startMonth * 100 + startDay, endMonth * 100 + endDay);
		}
		
		
		int t = 301;
		int ans = 0;
		
		while(t < 1201) {
			int nxt = t;
			for(int i = 0; i < n ;i++) {
				if(flowers[i].start <= t && flowers[i].end > nxt)
					nxt = flowers[i].end;
			}
			
			if(nxt == t) {
				System.out.println(0);
				System.exit(0);
			}
			
			ans++;
			t = nxt;
		}
		System.out.println(ans);
	}
}