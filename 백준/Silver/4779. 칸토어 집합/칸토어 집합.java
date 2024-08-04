import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static StringBuilder sb = new StringBuilder();
	static void solve(int n) {
		if(n == 0) {
			for(int i = 0; i<1;i++)
				sb.append('-');
			return;
		}
		
		solve(n-1);
		
		int start = (int)Math.pow(3, n-1);
		int end = (int)Math.pow(3, n-1) * 2 - 1;
		for(int i = start; i<=end;i++)
			sb.append(' ');
		
		solve(n-1);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input = br.readLine();
			if(input == null || input.isBlank())
				break;
			n = Integer.parseInt(input);
			
			
			solve(n);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}