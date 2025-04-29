import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());
        
        System.out.println(solve(N));
    }
    
    static long solve(long cur) {
    	if(cur <= 0)
    		return 1;
    	
    	if(dp.containsKey(cur))
    		return dp.get(cur);
    	
    	long ret = solve((long)Math.floor(cur / P) - X) + solve((long)Math.floor(cur/Q) - Y);
    	dp.put(cur, ret);
    	return ret;
    	
    }
    static StringTokenizer st;
    static long N,P,Q,X,Y;
    static Map<Long, Long> dp = new HashMap<>();
}