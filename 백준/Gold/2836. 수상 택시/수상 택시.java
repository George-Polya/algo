import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lines = new ArrayList<>();
		for(int i = 0; i <N;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start > end) {
				lines.add(new Line(end, start));
			}
		}
		
		if(lines.isEmpty()) {
			System.out.println(M);
			return;
		}
		
		Collections.sort(lines);
		
		int start = lines.get(0).start;
		int end = lines.get(0).end;
		
		long ans = 0;
		for(int i = 1; i < lines.size();i++) {
			Line cur = lines.get(i);
			
			if(cur.start <= end) {
				end = Math.max(end,  cur.end);
			}else {
				ans += (end - start);
				start = cur.start;
				end = cur.end;
			}
		}
		
		ans += (end - start);
		System.out.println(ans * 2  + M);
		
	}
	
	static int N,M;
	static StringTokenizer st;
	static List<Line> lines;
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
			return String.format("[%d, %d]", start, end);
		}
	}
}

