import java.io.*;
import java.util.*;

public class Main{
	
	static long k;
	static long len[];
	static int solve(long n) {
		if(n == 1)
			return 0;
		
		for(int i = 0; i < 65; i++) {
			if(n <= len[i]) {
				return 1 - solve(n - len[i-1]);
			}
		}
		
		return 0;
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Long.parseLong(br.readLine());
		
		len = new long[65];
		len[0] = 1;
		for(int i = 1; i < 65;i++) {
			len[i] = len[i-1] * 2;
		}
		
		System.out.println(solve(k));
		
	}
}