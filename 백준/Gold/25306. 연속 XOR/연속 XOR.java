import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		System.out.println(calc(B) ^ calc(A-1));
	}
	static long calc(long num) {
		long mod = num % 4;
		if(mod == 1)
			return 1;
		else if(mod == 2) {
			return num + 1;
		}else if(mod == 3) {
			return 0;
		}
		return num;
	}
	static long A,B;
	static StringTokenizer st;
}

