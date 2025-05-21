import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().toCharArray();
		N = line.length;
		Stack<Character> stk = new Stack<>();
		
		for(int i =0; i < N; i++) {
			char ch = line[i];
			stk.push(ch);
			if(ch == 'P' && isPPAP(stk)) {
				for(int p = 0; p < 4; p++)
					stk.pop();
				
				stk.push('P');
			}
		}
		
		System.out.println(stk.size() == 1 && stk.peek() == 'P' ? "PPAP" : "NP");
	}
	static int N;
	static char[] line;
	static boolean isPPAP(Stack<Character> stk) {
		int size = stk.size();
		if(size < 4)
			return false;
		return stk.get(size-1)=='P' && stk.get(size-2) == 'A' && stk.get(size-3) == 'P' && stk.get(size - 4) == 'P';
	}
}