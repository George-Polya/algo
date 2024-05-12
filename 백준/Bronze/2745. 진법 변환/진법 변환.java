import java.util.*;
import java.io.*;



public class Main {
	static String n;
	static int b;
	static int toNum(char ch) {
		if('A'<=ch && ch <= 'Z')
			return ch - 'A' + 10;
		return ch - '0';
	}
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = st.nextToken();
		b = Integer.parseInt(st.nextToken());
		
		long sum = 0;
		
		for(int i = 0; i < n.length();i++) {
			char ch = n.charAt(i);
			int num = toNum(ch);
			sum = b * sum + num;
		}
		System.out.println(sum);
	}
}