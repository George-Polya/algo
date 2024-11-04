import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < n ;i++) {
			int cur = Integer.parseInt(br.readLine());
			if(minQ.size() == maxQ.size()) {
				maxQ.add(cur);
			}else {
				minQ.add(cur);
			}
			
			
			if(!minQ.isEmpty() && !maxQ.isEmpty()) {
				if(minQ.peek() < maxQ.peek()) {
					int temp = minQ.poll();
					minQ.add(maxQ.poll());
					maxQ.add(temp);
				}
			}
			
			System.out.println(maxQ.peek());
		}
	}
	static int n;
}