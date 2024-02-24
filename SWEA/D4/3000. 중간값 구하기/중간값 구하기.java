import java.io.*;
import java.util.*;
public class Solution {
	static int T;
	static StringTokenizer st;
	static int n,a;
	static PriorityQueue<Integer> minHeap;
	static PriorityQueue<Integer> maxHeap;
	static int MOD = 20171109;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			
			minHeap = new PriorityQueue<>();
			maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			minHeap.add(a);
			
			int result = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int min = Math.min(x, y);
				int max = Math.max(x,y);
				
				minHeap.add(max);
				maxHeap.add(min);
				
				if(minHeap.peek() < maxHeap.peek()) {
					min = minHeap.poll();
					max = maxHeap.poll();
					minHeap.add(max);
					maxHeap.add(min);
				}
				
				result += minHeap.peek() % MOD;
				result %= MOD;
			}
			
//			int result = minHeap.peek();
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
				
	}
	
	
}