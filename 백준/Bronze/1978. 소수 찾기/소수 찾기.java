import java.io.*;
import java.util.*;
/**
 * 에라토스테네스의 체 
 */
public class Main {
	static int n;
//	static int MAX_N = 1000;
	static StringTokenizer st;
	
	static boolean isPrime(int num) {
		if(num == 1)
			return false;
		for(int i = 2; i * i <= num; i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(isPrime(num))
				ans++;
		}
		
		System.out.println(ans);
	}
}