import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static StringTokenizer st;
	static class Node{
		int idx, value;
		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Deque<Node> deq = new ArrayDeque<>();
		for(int i = 1; i<=n; i++) {
			Node node = new Node(i, Integer.parseInt(st.nextToken()));
			deq.addLast(node);
		}
		
		Node cur = deq.pollFirst();
		int value = cur.value;
		StringBuilder sb = new StringBuilder();
		sb.append(cur.idx).append(' ');
		while(!deq.isEmpty()) {
			if(value > 0) {
				
				for(int i =1;i<value;i++) {
					deq.addLast(deq.pollFirst());
				}
				
				cur = deq.pollFirst();
				sb.append(cur.idx).append(' ');
				value = cur.value;
				
			}else if(value < 0) {
				for(int i = 1; i<-value;i++) {
					deq.addFirst(deq.pollLast());
				}
				
				cur = deq.pollLast();
				sb.append(cur.idx).append(' ');
				value = cur.value;
			}
		}
		System.out.println(sb);
	}
}