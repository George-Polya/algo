import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int n,a;
	static StringTokenizer st;
	static PriorityQueue<Integer> maxH;
	static PriorityQueue<Integer> minH;
	static int MOD = 20171109;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			maxH = new PriorityQueue<>(Collections.reverseOrder());
			minH = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			minH.add(a);
			int answer = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int value1 = Integer.parseInt(st.nextToken());
				int value2 = Integer.parseInt(st.nextToken());
				
				if(value1 < value2) {
					maxH.add(value1);
					minH.add(value2);
				}else {
					maxH.add(value2);
					minH.add(value1);
				}
				
				while(minH.peek() < maxH.peek()) {
					int max = maxH.poll();
					int min = minH.poll();
					
					maxH.add(min);
					minH.add(max);
				}
				
				answer = (minH.peek() + answer) % MOD;
			}
			
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
			
			
		
		}
		System.out.println(sb);
	}
}