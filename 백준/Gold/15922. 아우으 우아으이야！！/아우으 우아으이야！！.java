import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lines = new Line[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lines[i] = new Line(start, end);
		}
		Arrays.sort(lines);
		
		Line cur = lines[0];
		long ans = 0;
		int curStart = cur.start;
		int curEnd = cur.end;
		for(int i = 1; i < N;i++) {
			Line nxt = lines[i];
			if(nxt.start <= curEnd) {
				curEnd = Math.max(curEnd, nxt.end);
			}else {
				ans += (curEnd - curStart);
				curStart = nxt.start;
				curEnd = nxt.end;
			}
		}
		ans += (curEnd - curStart);
		System.out.println(ans);
	}
	static StringTokenizer st;
	static int N;
	static Line lines[];
	static class Line implements Comparable<Line>{
		int start, end;
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Line o) {
			if(start != o.start)
				return start - o.start;
			return end - o.end;
		}
		public String toString() {
			return start + " " + end;
		}
	}
}