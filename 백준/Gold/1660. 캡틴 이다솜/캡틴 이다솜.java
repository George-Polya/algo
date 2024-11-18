import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        init(n);
        
        dp = new int [n+1];
        Arrays.fill(dp, INT_MAX);
        
        dp[0] = 0;
        for(int t : tetras) {
        	for(int i = t; i<=n; i++) {
        		if(dp[i - t] != INT_MAX)
        			dp[i] = Math.min(dp[i], dp[i - t] + 1);
        	}
        }
        
        System.out.println(dp[n]);
    }
    
    
    static int dp[];
    static void init(int n) {
    	int num = 1;
    	int idx = 1;
    	
    	while(num <= n) {
    		tetras.add(num);
    		idx++;
    		num = idx * (idx + 1) * (idx + 2) / 6;
    	}
    		
    }
    static List<Integer> tetras = new ArrayList<>();
    
    static int INT_MAX = Integer.MAX_VALUE;
    static int ans = INT_MAX;
    static int n;
}