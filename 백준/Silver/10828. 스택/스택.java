import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int MAX_N = 10000;
	static int stk[] = new int[MAX_N];
	static int top = -1;
	static StringTokenizer st;
	static void printStack() {
		for(int i =0; i < top; i++) {
			System.out.print(stk[i]+ " ");
		}
		System.out.println();
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
				top++;
				stk[top] = num;
//				System.out.println(stk[top]);
				
			}else if(op.equals("pop")) {
				if(top == -1) {
					System.out.println(-1);
//					sb.append(-1).append('\n');
				}else {
					int ret = stk[top];
					top--;
					System.out.println(ret);
				}
				
				
			}else if(op.equals("size")) {
				System.out.println(top+1);
			}else if(op.equals("empty")) {
				System.out.println(top == -1 ? 1 : 0);
			}else if(op.equals("top")){
				if(top == -1) {
					System.out.println(-1);
				}else {
					System.out.println(stk[top]);
				}
			}
		}
		
	}

}