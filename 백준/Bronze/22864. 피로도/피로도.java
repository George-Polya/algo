import java.util.*;
import java.io.*;
public class Main {
	static int A,B,C,M;
	static StringTokenizer st;
	
	static boolean possible(int x) {
		if(A > M)
			return false;
		
		if(A*x - (24-x) * C < 0)
			return false;
		
		return (A+C) * x <= M + 24 * C;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int time = 0;
		int p = 0;
		int ans = 0;
		while(time != 24) {
			if(p + A <= M) {
				ans += B;
				p += A;
			}else {
				p = p-C <0 ? 0 : p-C;
			}
			time++;
		}
		System.out.println(ans);
	}
}