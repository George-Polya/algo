import java.io.*;
import java.util.*;

public class Main {
	static int T;
	
	static boolean isVPS(String str) {
		Stack<Character> stk = new Stack<>();
		
		for(int i = 0; i < str.length();i++) {
			char ch = str.charAt(i);
			
			if(ch == '(')
				stk.push(ch);
			else {
				if(stk.isEmpty()) {
					return false;
				}
				stk.pop();
			}
		}
		
		return stk.isEmpty();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			String str = br.readLine();
			
			sb.append(isVPS(str) ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}
}