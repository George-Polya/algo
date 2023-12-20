import java.io.*;
import java.util.*;


class Node{
	Node prev, next;
	int value;
}


class Stack{
	Node top;
	int size;
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	Node getNewNode(int num) {
		Node node = new Node();
		node.value = num;
		return node;
	}
	
	void push(int num) {
		Node node = getNewNode(num);
		if(top == null) {
			top = node;
		}else {
			top.next = node;
			node.prev = top;
			top = node;
		}
		size++;
	}
	
	int pop() {
		if(top == null)
			return -1;
		else {
			size--;
			int ret = top.value;
			top = top.prev;
			return ret;
		}
	}
	
	int getSize() {
		return size;
	}
	
	boolean isEmpty() {
		return top == null;
	}
	
	int getTop() {
		return top.value;
	}
}


public class Main {
	static int n;
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack stk = new Stack();
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			if(op.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				stk.push(num);
			}else if(op.equals("pop")) {
				sb.append(stk.pop()).append('\n');
			}else if(op.equals("size")) {
				sb.append(stk.getSize()).append('\n');
			}else if(op.equals("empty")) {
				sb.append(stk.isEmpty() ? 1 : 0).append('\n');
			}else if(op.equals("top")){
				sb.append(stk.isEmpty() ? -1 : stk.getTop()).append('\n');
			}
		}
		
		System.out.println(sb);
	}

}