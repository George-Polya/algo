import java.io.*;
import java.util.*;


public class Main {
	static int n,k;
	static class Jewel implements Comparable<Jewel>{
		int weight, value;
		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		public int compareTo(Jewel jewel) {
			return weight - jewel.weight;
		}
	}
	static PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
	static StringTokenizer st;
	static int bags[];
	static Jewel jewels[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		jewels = new Jewel[n];
		for(int i = 0 ; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(weight, value);
		}
		
		bags = new int[k];
		for(int i = 0; i < k;i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(jewels);
		Arrays.sort(bags);
		
		int idx = 0;
		long ans = 0;
		for(int i = 0; i < k ;i++) {
			while(idx <n && jewels[idx].weight <= bags[i]) {
				pq.add((long)jewels[idx].value);
				idx++;
			}
			
			if(!pq.isEmpty()) {
				ans += pq.poll();
			}
		}
		System.out.println(ans);
	}
}