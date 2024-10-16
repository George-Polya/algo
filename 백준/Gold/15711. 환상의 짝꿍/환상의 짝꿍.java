import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	init();
    	T = Integer.parseInt(br.readLine());
    	
    	StringBuilder sb = new StringBuilder();
    	for(int tc=1;tc<=T;tc++) {
    		st = new StringTokenizer(br.readLine());
    		long a = Long.parseLong(st.nextToken());
    		long b = Long.parseLong(st.nextToken());
    		
    		sb.append(check(a,b) ? "YES" : "NO").append('\n');
    	}
    	System.out.println(sb);
    }
    
    static boolean check(long a, long b) {
    	long sum = a + b;
    	if(sum <= 2)
    		return false;
    	
    	if(sum % 2 == 1) {
    		if(isPrime(sum - 2))
    			return true;
    		return false;
    	}
    	
    	return true;
    }
    
    static boolean isPrime(long num) {
    	if(num <= 0)
    		return false;
    	if(num < MAX_N)
    		return sieves.contains(num);
    	
    	for(Long prime : sieves) {
    		if(num % prime == 0)
    			return false;
    	}
    	return true;
//    	return sieves.contains(num);
    }
    
    static StringTokenizer st;
    static int T;
    
    static void init() {
    	Arrays.fill(states, true);
    	states[0] = states[1] = false;
    	
    	for(int i = 2; i<=MAX_N; i++) {
    		if(!states[i])
    			continue;
    		
    		for(int j = 2*i; j<=MAX_N; j+=i)
    			states[j] = false;
    	}
    	
    	for(int i = 2; i<=MAX_N;i++) {
    		if(states[i])
    			sieves.add((long) i);
    	}
    	
    	
    }
    
    static TreeSet<Long> sieves = new TreeSet<>();
    static int MAX_N = (int)1e6 * 2;
    static boolean states[] = new boolean[MAX_N+1];
    
}