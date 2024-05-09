import java.util.*;
import java.io.*;



public class Main {
	static int T,n;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			
			int arr[] = new int[n+1];
			
			int idx = 1;
			for(int i = 0; i < n / 10 + 1; i++) {
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					int value = Integer.parseInt(st.nextToken());
					arr[idx++] = value;
				}
			}
			
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			
			List<Integer> medians = new ArrayList<>();
			
			maxHeap.offer(arr[1]);
			medians.add(maxHeap.peek());
			
			for(int i = 2; i<=n;i+=2) {
				int a = arr[i];
				int b = arr[i+1];
				int max = Math.max(a, b);
				int min = Math.min(a, b);
				minHeap.offer(max);
				maxHeap.offer(min);
				if(minHeap.peek() < maxHeap.peek()) {
					a = minHeap.poll();
					b = maxHeap.poll();
					
					maxHeap.offer(a);
					minHeap.offer(b);
				}
				medians.add(maxHeap.peek());
			}
			
			StringBuilder sb2 = new StringBuilder();
			sb2.append(medians.size()).append('\n');
			int cnt = 0;
			for(int i = 0; i < medians.size(); i++) {
				cnt++;
				sb2.append(medians.get(i)).append(' ');
				if(cnt % 10 == 0)
					sb2.append('\n');
			}
			sb.append(sb2).append('\n');
		}
		System.out.println(sb);
	}
}