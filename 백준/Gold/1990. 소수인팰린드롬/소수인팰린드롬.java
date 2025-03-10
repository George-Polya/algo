import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	A = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	
    	List<String> primes = new ArrayList<>();
    	boolean states[] = new boolean[B+1];
    	Arrays.fill(states, true);
    	states[1] = false;
    	
    	for(int i = 2; i * i <= B; i++) {
    		if(!states[i])
    			continue;
    		
			for(int j = i*i; j<=B; j+=i)
				states[j] = false;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = A; i<=B; i++)
    		if(states[i]) {
    			String str = String.valueOf(i);
    			if(isPalindrome(str))
    				sb.append(str).append('\n');
    		}
    			
    	
    	sb.append(-1);
    	System.out.println(sb);
    			
    	
    }
    
    static boolean isPalindrome(String str) {
    	int size = str.length();
    	
    	for(int i = 0; i < size / 2; i++) {
    		if(str.charAt(i) != str.charAt(size - 1 - i))
    			return false;
    	}
    	return true;
    }
    
    static StringTokenizer st;
    static int A,B;
}