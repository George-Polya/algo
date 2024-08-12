import java.util.*;
import java.io.*;

public class Main {
	static int T;
	
	static boolean isPrime(long num) {
		if(num == 0 || num == 1)
			return false;
		
		for(int i = 2; i<=(int)Math.sqrt(num);i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	
	static long solve(long num) {
		if(num == 0 || num == 1)
			return 2;
		while(true) {
			if(isPrime(num))
				break;
			num++;
		}
		return num;
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=T;tc++) {
        	long num = Long.parseLong(br.readLine());
        	sb.append(solve(num)).append('\n');
        }
        System.out.println(sb);
    }
}