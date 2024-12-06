import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Long.parseLong(st.nextToken());
    	k = Long.parseLong(st.nextToken());
    	long l = 0;
    	long r = n / 2;
    	
    	
    	while(l <= r) {
    		long h = (l + r) / 2;
    		long v = n - h;
    		
    		long total = calc(h,v);
    		if(total == k) {
    			System.out.println("YES");
    			return;
    		}else if(total > k) {
    			r = h - 1;
    		}else {
    			l = h + 1;
    		}
    	}
    	System.out.println("NO");
    	
    }
    
    static long calc(long h, long v) {
    	return (h + 1) * (v + 1);
    }
    static StringTokenizer st;
    static long n,k;
}