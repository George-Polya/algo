import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static StringTokenizer st;
	static class Circle implements Comparable<Circle>{
		int s, e;
		public Circle(int s,int e) {
			this.s = s;
			this.e = e;
		}
		
		public int compareTo(Circle c) {
			if(s!=c.s)
				return s - c.s;
			return e - c.e;
		}
		
		public String toString() {
			return s+ " "+e;
		}
	}
	
	static Circle NO_CIRCLE = new Circle(-(int)1e9-1, -(int)1e9-1);
	static Circle circles[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		circles = new Circle[n];
		for(int i = 0; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			circles[i] = new Circle(x-r, x+r);
		}
		Arrays.sort(circles);
//		System.out.println(Arrays.toString(circles));
		boolean flag = true;
		Circle prev = NO_CIRCLE;
		
		for(int i = 0; i < n;i++) {
			Circle cur = circles[i];
			if(cur.s <= prev.e && prev.e <= cur.e) {
				flag = false;
				break;
			}
			prev = cur;
		}
		System.out.println(flag ? "YES" : "NO");
		
	}
}