import java.io.*;
import java.util.*;

public class Main {
	static int n;
	
	static List<Integer> getPrimes(int n){
		List<Integer> ret = new ArrayList<>();
		
		boolean states[] = new boolean[n+1];
		Arrays.fill(states, true);
		
		for(int i = 2; i * i <=n;i++) {
			if(!states[i])
				continue;
			for(int j = i * i ; j<=n; j+=i)
				states[j] = false;
		}
		
		for(int i = 2; i<=n; i++) {
			if(states[i])
				ret.add(i);
		}
		
		return ret;
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        List<Integer> primes = getPrimes(n);
//        System.out.println(primes);
        
        int r = 0;
        int size = primes.size();
//        System.out.println(primes.get(size-1));
        int sum = 0;
        int cnt = 0;
        for(int l = 0; l < size;l++) {
        	while(r < size && sum < n) {
        		sum += primes.get(r);
        		r++;
        	}
        	
        	if(sum == n)
        		cnt++;
        	
        	sum -= primes.get(l);
        }
        System.out.println(cnt);
    }
}