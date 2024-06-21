import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int n;
	
	static int calcSum(int copy) {
		if(copy < 10)
			return copy;
		return copy / 10 + copy % 10;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int copy = n;
		int cnt = 0;
		do {
			int sum = calcSum(copy);
			copy = (copy % 10) * 10 + sum % 10;
			cnt++;
		}while(n != copy);
		
		System.out.println(cnt);
	}
}