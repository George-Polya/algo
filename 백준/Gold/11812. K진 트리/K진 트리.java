import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int turn = 1; turn <= q; turn++) {
        	st = new StringTokenizer(br.readLine());
        	long a = Long.parseLong(st.nextToken());
        	long b = Long.parseLong(st.nextToken());
        	if(k == 1)
        		sb.append(Math.abs(a-b)).append('\n');
        	else
        		sb.append(LCA(a,b)).append('\n');
        }
        System.out.println(sb);
    }
    static long n;
    static int k,q;
    static StringTokenizer st;
    
    static long LCA(long a, long b) {
    	long depthA = findDepth(a);
    	long depthB = findDepth(b);
    	
    	
    	long ret = 0;
    	while(depthA != depthB) {
    		if(depthA > depthB) {
    			a = findParent(a);
    			depthA--;
    		}
    		else {
    			b = findParent(b);
    			depthB--;
    		}
    		ret++;
    	}
    	
    	while(a != b) {
    		a = findParent(a);
    		b = findParent(b);
    		ret += 2;
    	}
    	return ret;
    }
    
    static long findParent(long idx) {
    	if(idx == 1)
    		return 0;
    	return (idx - 2) / k + 1;
    }
    
    static long findDepth(long idx) {
    	if(idx == 1)
    		return 0;
    	
    	long depth = 1;
    	long end = 1;
    	
    	while(true){
    		end += (long)Math.pow(k, depth++);
    		if(idx <= end)
    			break;
    	}
    	return depth - 1;
    }
  
}