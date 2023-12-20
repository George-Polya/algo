import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int MAX_N = 10000;
	
	static StringTokenizer st;
	
	static class Node{
		Node prev, next;
		int value;
	}
	
	static Node top = null;
	static int size = 0;
	static Node getNewNode(int num) {
		Node node = new Node();
		node.value = num;
		return node;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			if(op.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				Node node = getNewNode(num);
				if(top == null) {
					top = node;
				}else {
					top.next = node;
					node.prev = top;
					top = node;
				}
				size++;
			}else if(op.equals("pop")) {
				if(top == null) {
//					System.out.println(-1);
					sb.append(-1).append('\n');
				}else {
					size--;
					int ret = top.value;
					top = top.prev;
//					System.out.println(ret);
					sb.append(ret).append('\n');
				}
				
			}else if(op.equals("size")) {
//				System.out.println(size);
				sb.append(size).append('\n');
			}else if(op.equals("empty")) {
//				System.out.println(top==null ? 1 : 0);
				sb.append(top == null ? 1 : 0).append('\n');
			}else if(op.equals("top")){
//				System.out.println(top == null ? -1 : top.value);
				sb.append(top == null ? -1 : top.value).append('\n');
			}
		}
		
		System.out.println(sb);
	}

}