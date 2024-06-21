import java.util.*;
import java.io.*;
public class Main {
	static int T;
	static StringTokenizer st;
	static int GCD(int a, int b) {
		if(b == 0)
			return a;
		return GCD(b, a % b);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int gcd = GCD(a,b);
//			System.out.println(gcd);
			sb.append(gcd * (a / gcd) *(b / gcd) ).append('\n');
		}
		System.out.println(sb);
	}
}