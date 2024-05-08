import java.util.*;
import java.io.*;



public class Main {
	static int n;
	static int MAX_N = 2000000; 
	static StringTokenizer st;
	static class Queue{
		int arr[] = new int[MAX_N+1];
		int back, front;
		
		public Queue() {
			this.back = this.front = 0;
		}
		
		public void offer(int x) {
			arr[back] = x;
			back++;
		}
		
		public int poll() {
			return arr[front++];
		}
		
		public int size() {
			return back - front;
		}
		
		public boolean isEmpty() {
			return back==front;
		}
		
		public int peek() {
			return arr[front];
		}
		
		public int peekLast() {
//			System.out.println("back" + back);
			return arr[back-1];
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
//		LinkedList<Integer> q = new LinkedList<>();
		Queue q = new Queue();
		StringBuilder sb = new StringBuilder();
		for(int turn=1; turn<=n; turn++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			if(oper.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				q.offer(x);
			}else if(oper.equals("front")) {
				sb.append(q.isEmpty() ? -1 : q.peek()).append('\n');
			}else if(oper.equals("pop")) {
				sb.append(q.isEmpty() ? -1 : q.poll()).append('\n');
			}else if(oper.equals("size")) {
				sb.append(q.size()).append('\n');
			}else if(oper.equals("empty")) {
				sb.append(q.isEmpty() ? 1 : 0).append('\n');
			}else if(oper.equals("back")) {
				sb.append(q.isEmpty() ? -1 : q.peekLast()).append('\n');
			}
		}
		System.out.println(sb);
	}
}