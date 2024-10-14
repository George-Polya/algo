import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	error = new boolean[n+1];
    	
    	int max = 0;
    	for(int i = 0; i < m; i++) {
    		int idx = Integer.parseInt(st.nextToken());
    		error[idx] = true;
    		
    	}
    	st = new StringTokenizer(br.readLine());
    	x = Integer.parseInt(st.nextToken());
    	y = Integer.parseInt(st.nextToken());

    	
    	int start = 1;
    	int end = 1;
    	
    	int errCnt = 0;
    	int successCnt = 0;
    	int ans = Integer.MAX_VALUE;
    	
    	if(n == 1) {
    		System.out.println(m-y);
    		return;
    	}
    	
    	while(true) {
    		if(start == n)
    			break;
    		
    		if((errCnt < y | successCnt < x) && end<=n) {
    			if(error[end++])
    				errCnt++;
    			successCnt++;
    		}else {
    			if(errCnt >= y && successCnt >=x)
    				ans = Math.min(ans,  errCnt);
    			if(error[start++])
    				errCnt--;
    			successCnt--;
    		}
    	}
    	
    	System.out.println(m-ans);
    }
    static int n,m, x,y;
    static StringTokenizer st;
    static boolean error[];
}