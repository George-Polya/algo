import java.util.*;
import java.io.*;

public class Main {
	static boolean isPrime(long num) {
		for(int i = 2; i<=(int)Math.sqrt(num);i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	
	static boolean isPalindrome(long num) {
		String str = String.valueOf(num);
		
		int len = str.length();
		for(int i = 0; i < len;i++) {
			if(str.charAt(i) != str.charAt(len - 1 - i))
				return false;
		}
		return true;
	}
	
	static long solve(String str) {
		if(str.equals("1"))
			return 2;
		
		long num = Integer.valueOf(str);
		while(true) {
			if(isPrime(num) && isPalindrome(num))
				break;
			num++;
		}
		return num;
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solve(str));
    }
}