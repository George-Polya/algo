import java.util.*;
import java.io.*;
public class Main {
	static long arr[];
	static long k;
	
	static int solve(long n) {
		if(n == 1)
			return 0;
		
		for(int i = 1; i<64;i++) {
			if(n <= arr[i])
				return 1 - solve(n - arr[i-1]);
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Long.parseLong(br.readLine());
		
		arr = new long[64+1];
		arr[0] = 1;
		for(int i = 1; i < 65;i++) {
			arr[i] = 2 * arr[i-1];
		}
			
		System.out.println(solve(k));
		
	}
}