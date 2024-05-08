import java.util.*;
import java.io.*;



public class Main {
	static int n;
	static String expression;
	static int operands[] = new int[26];
	
	static int toNum(char ch) {
		return (ch - 'A');
	}
	
	static Stack<Double> stk = new Stack<>();
	
	static double calc(double oper1, double oper2, char ch) {
		if(ch == '+') {
			return oper1 + oper2;
		}else if(ch == '-') {
			return oper1 - oper2;
		}else if(ch == '*') {
			return oper1 * oper2;
		}else {
			return oper1 / oper2;
		}
			
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		expression = br.readLine();
		
		for(int i = 0; i < n ; i++) {
			operands[i] = Integer.parseInt(br.readLine());
		}
		
		
		for(int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			
			if('A'<=ch && ch <= 'Z') {
				double value = (double)operands[toNum(ch)];
				stk.add(value);
//				stk.add((double)operands[toNum(ch)]);
			}else {
				double operand1 = stk.pop();
				double operand2 = stk.pop();
				
				double result = calc(operand2, operand1, ch);
				
				stk.add(result);
			}
			
		}
		System.out.printf("%.2f",stk.peek());
		
	}
}