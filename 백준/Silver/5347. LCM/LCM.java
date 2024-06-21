import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int T;
	static int GCD(int a, int b) {
		if(b == 0)
			return a;
		return GCD(b, a % b);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int gcd = GCD(a,b);
			sb.append((long)a * (b / gcd)).append('\n');
		}
		System.out.println(sb);
	}
}