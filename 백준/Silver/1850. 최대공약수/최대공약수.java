import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	A = Long.parseLong(st.nextToken());
    	B = Long.parseLong(st.nextToken());
    	StringBuilder sb = new StringBuilder();
    	
    	for(long i = 0; i < gcd(A,B);i++)
    		sb.append('1');
    	System.out.println(sb);
    }
    static long A,B;
    static StringTokenizer st;
    static long gcd(long a, long b) {
    	if(b == 0)
    		return a;	
    	
    	return gcd(b, a % b);
    }
}