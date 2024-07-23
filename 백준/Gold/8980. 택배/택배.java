import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int n,c,m;
	
	static class Delivery implements Comparable<Delivery>{
		int from, to, box;
		public Delivery(int from, int to, int box) {
			this.from = from;
			this.to = to;
			this.box = box;
		}
		
		public int compareTo(Delivery d) {
			
			if(to != d.to)
				return to - d.to;
			return from - d.from;
			
			
			
		}
		
		public String toString() {
			return String.format("from: %d, to: %d, box:%d", from, to, box);
		}
	}
	
	static Delivery deliveries[];
//	static ArrayList<Delivery> deliveries = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		deliveries = new Delivery[m];
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int box = Integer.parseInt(st.nextToken());
//			deliveries.add(new Delivery(from, to, box));
			deliveries[i] = new Delivery(from, to, box);
		}
		
		Arrays.sort(deliveries);
		int weights[] = new int[n];
		Arrays.fill(weights, c);
		int ans = 0;
		
		
		for(int i = 0; i<m;i++) {
//			System.out.println(deliveries[i]);
			Delivery delivery = deliveries[i];
			int max = Integer.MAX_VALUE;
			
			for(int j = delivery.from; j< delivery.to;j++) {
				max = Math.min(max, weights[j]);
			}
			
			if(max >= delivery.box) {
				for(int j = delivery.from; j < delivery.to;j++) {
					weights[j] -= delivery.box;
				}
				ans += delivery.box;
			}else {
				for(int j = delivery.from; j < delivery.to;j++) {
					weights[j] -= max;
				}
				ans += max;
			}
			
		}
		
		
		System.out.println(ans);
		
	}
}