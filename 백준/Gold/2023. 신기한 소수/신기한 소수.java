import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int number[];
	static StringBuilder sb = new StringBuilder();
	
	static boolean isPrime(int num) {
		if(num <= 1)
			return false;
		
		for(int i = 2; i*i <= num;i++)
			if(num % i == 0)
				return false;
		return true;
	}
	
	
	static void solve(int cur, int cnt) {
		if(cnt == 0) {
			if(isPrime(cur))
				sb.append(cur).append('\n');
			return;
		}
		
		for(int i = 0; i<=9;i++) {
			int nxt = cur * 10 + i;
			if(isPrime(nxt))
				solve(nxt, cnt  - 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		number = new int[n];
		
		solve(0,n);
		System.out.println(sb);
	}
}