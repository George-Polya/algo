import java.io.*;
/**
 * 
 */
import java.util.*;
public class Main {
	static int n;
	static boolean solve(int num) {
		return (num % 4 == 0 && num % 100 != 0) || (num % 400 == 0);
	}
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	n = Integer.parseInt(br.readLine());
	 	System.out.println(solve(n) ? 1 : 0);
	}
}